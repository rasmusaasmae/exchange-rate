package com.aasmae.exchange.repository;

import com.aasmae.exchange.domain.ExchangeRate;
import com.aasmae.exchange.domain.ExchangeRateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends ListCrudRepository<ExchangeRate, ExchangeRateId> {

    @Query("select distinct e.currency from ExchangeRate e")
    List<String> getAllCurrencies();

    Optional<ExchangeRate> findFirstByCurrencyOrderByDateDesc(String currency);
}