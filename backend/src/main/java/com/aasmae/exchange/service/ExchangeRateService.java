package com.aasmae.exchange.service;

import com.aasmae.exchange.domain.ConversionDTO;
import com.aasmae.exchange.domain.ExchangeRate;
import com.aasmae.exchange.domain.ExchangeRateDTO;
import com.aasmae.exchange.repository.ExchangeRateRepository;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final XmlMapper xmlMapper = new XmlMapper();
    private final WebClient webClient;
    private final ExchangeRateRepository exchangeRateRepository;

    public ConversionDTO convert(String from, String to, double amount) {
        BigDecimal conversionRate;
        if (from.equals("EUR") || to.equals("EUR")) {
            try {
                conversionRate = getConversionRateWithEur(from, to);
            } catch (RuntimeException e) {
                throw new RuntimeException("Failed to convert from " + from + " to " + to);
            }
        } else {
            ExchangeRate fromRate = exchangeRateRepository.findFirstByCurrencyOrderByDateDesc(from)
                    .orElseThrow(() -> new NullPointerException("Exchange rate not found: " + from));
            ExchangeRate toRate = exchangeRateRepository.findFirstByCurrencyOrderByDateDesc(to)
                    .orElseThrow(() -> new NullPointerException("Exchange rate not found: " + to));
            if (!fromRate.getDate().equals(toRate.getDate())) {
                throw new DateTimeException("Exchange rates dates do not match");
            }
            conversionRate = toRate.getRate().divide(fromRate.getRate(), 4, RoundingMode.CEILING);
        }
        BigDecimal convertedAmount = BigDecimal.valueOf(amount).multiply(conversionRate);
        return new ConversionDTO(convertedAmount, conversionRate);
    }

    private BigDecimal getConversionRateWithEur(String from, String to) {
        if (from.equals("EUR") && to.equals("EUR")) return BigDecimal.ONE;

        if (from.equals("EUR")) {
            ExchangeRate toRate = exchangeRateRepository.findFirstByCurrencyOrderByDateDesc(to)
                    .orElseThrow(() -> new NullPointerException("Exchange rate not found: " + to));
            return toRate.getRate();
        } else if (to.equals("EUR")) {
            ExchangeRate fromRate = exchangeRateRepository.findFirstByCurrencyOrderByDateDesc(from)
                    .orElseThrow(() -> new NullPointerException("Exchange rate not found: " + from));
            return BigDecimal.ONE.divide(fromRate.getRate(), 4, RoundingMode.CEILING);
        }
        throw new RuntimeException("At least one argument must be EUR");
    }

    public void populateExchangeRates() {
        String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";
        String xmlResponse = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        List<ExchangeRate> exchangeRates = parseResponse(xmlResponse);
        exchangeRateRepository.saveAll(exchangeRates);
    }

    public void saveLatestExchangeRates() {
        String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
        String xmlResponse = webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        List<ExchangeRate> exchangeRates = parseResponse(xmlResponse);
        exchangeRateRepository.saveAll(exchangeRates);
    }

    private List<ExchangeRate> parseResponse(String xmlResponse) {
        try {
            ExchangeRateDTO response = xmlMapper.readValue(xmlResponse, ExchangeRateDTO.class);
            List<ExchangeRate> exchangeRates = new ArrayList<>();

            if (response.getCubeWrapper() != null) {
                List<ExchangeRateDTO.CubeDate> cubeDates = response.getCubeWrapper().getCubeDates();
                for (ExchangeRateDTO.CubeDate cubeDate : cubeDates) {
                    LocalDate date = LocalDate.parse(cubeDate.getTime());
                    for (ExchangeRateDTO.CurrencyRate rate : cubeDate.getRates()) {
                        ExchangeRate exchangeRate = ExchangeRate.builder()
                                .date(date)
                                .currency(rate.getCurrency())
                                .rate(new BigDecimal(rate.getRate()))
                                .build();
                        exchangeRates.add(exchangeRate);
                    }
                }
            }
            return exchangeRates;
        } catch (Exception e) {
            throw new RuntimeException("Error parsing XML", e);
        }
    }

}