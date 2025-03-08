package com.aasmae.exchange.repository;

import com.aasmae.exchange.domain.ExchangeRate;
import com.aasmae.exchange.domain.ExchangeRateId;
import org.springframework.data.repository.ListCrudRepository;

public interface ExchangeRateRepository extends ListCrudRepository<ExchangeRate, ExchangeRateId> {
}