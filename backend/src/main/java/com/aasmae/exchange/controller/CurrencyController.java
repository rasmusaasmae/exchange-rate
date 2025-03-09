package com.aasmae.exchange.controller;

import com.aasmae.exchange.domain.CurrencyGrowthDTO;
import com.aasmae.exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public List<String> getCurrencies() {
        return currencyService.getAllCurrenciesForConversion();
    }

    @GetMapping("/growth")
    public List<CurrencyGrowthDTO> getCurrenciesWithFastestGrowthFor10Days() {
        return currencyService.getFastestGrowingCurrencies(10, 5);
    }

}