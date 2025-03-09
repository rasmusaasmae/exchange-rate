package com.aasmae.exchange.controller;

import com.aasmae.exchange.domain.ConversionDTO;
import com.aasmae.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/convert/{from}/{to}")
    public ConversionDTO convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @RequestParam double amount
    ) {
        return exchangeRateService.convert(from, to, amount);
    }

}