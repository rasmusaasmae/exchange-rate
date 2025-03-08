package com.aasmae.exchange;

import com.aasmae.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@Configuration
public class ExchangeRateInitializer {

    private  final ExchangeRateService exchangeRateService;

    @EventListener(ApplicationReadyEvent.class)
    public void populateExchangeRatesFor90Days() {
        exchangeRateService.populateExchangeRates();
    }


}