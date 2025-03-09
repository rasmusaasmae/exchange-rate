package com.aasmae.exchange.service;

import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyCurrencyUpdateJob implements Job {

    private final ExchangeRateService exchangeRateService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        exchangeRateService.saveLatestExchangeRates();
    }

}