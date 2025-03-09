package com.aasmae.exchange.service;

import com.aasmae.exchange.domain.CurrencyGrowthDTO;
import com.aasmae.exchange.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final ExchangeRateRepository exchangeRateRepository;

    public List<String> getAllCurrenciesForConversion() {
        List<String> currencies = exchangeRateRepository.getAllCurrencies();
        currencies.add("EUR");
        return currencies;
    }

    public List<CurrencyGrowthDTO> getFastestGrowingCurrencies(long days, long limit) {
        Optional<LocalDate> endDate = exchangeRateRepository.findMaxDate();
        if (endDate.isEmpty()) throw new RuntimeException("Max date not found");
        LocalDate startDate = endDate.get().minusDays(days);
        return exchangeRateRepository.findCurrencyGrowthByDates(startDate, endDate.get(), limit);

    }

}