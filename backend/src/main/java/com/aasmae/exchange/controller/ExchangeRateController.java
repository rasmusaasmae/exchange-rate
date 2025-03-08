package com.aasmae.exchange.controller;

import com.aasmae.exchange.domain.ConversionDTO;
import com.aasmae.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping(value = "/currencies", produces = "application/json")
    public List<String> getCurrencies() {
        return exchangeRateService.getAllCurrencies();
    }

    @GetMapping("/convert/{from}/{to}")
    public ConversionDTO convertCurrency(@PathVariable String from, @PathVariable String to, @RequestParam double amount) {
        return exchangeRateService.convert(from, to, amount);
    }
}