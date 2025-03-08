package com.aasmae.exchange.service;

import com.aasmae.exchange.domain.ExchangeRate;
import com.aasmae.exchange.domain.ExchangeRateDTO;
import com.aasmae.exchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.reactive.function.client.WebClient;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final XmlMapper xmlMapper = new XmlMapper();
    private final ExchangeRateRepository exchangeRateRepository;
    private final WebClient webClient;

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