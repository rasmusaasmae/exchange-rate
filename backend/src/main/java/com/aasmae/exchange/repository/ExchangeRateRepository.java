package com.aasmae.exchange.repository;

import com.aasmae.exchange.domain.CurrencyGrowthDTO;
import com.aasmae.exchange.domain.ExchangeRate;
import com.aasmae.exchange.domain.ExchangeRateId;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends ListCrudRepository<ExchangeRate, ExchangeRateId> {

    @Query("select distinct e.currency from ExchangeRate e")
    List<String> getAllCurrencies();

    Optional<ExchangeRate> findFirstByCurrencyOrderByDateDesc(String currency);

    @Query("SELECT MAX(e.date) FROM ExchangeRate e")
    Optional<LocalDate> findMaxDate();

    @NativeQuery(value = """
            SELECT currency, 
                   (MAX(CASE WHEN date = :endDate THEN rate END) - 
                    MAX(CASE WHEN date = :startDate THEN rate END)) / 
                    MAX(CASE WHEN date = :startDate THEN rate END) AS growthRate
            FROM exchange_rate
            WHERE date IN (:startDate, :endDate)
            GROUP BY currency
            ORDER BY growthRate DESC
            LIMIT :limit
            """)
    List<CurrencyGrowthDTO> findCurrencyGrowthByDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("limit") long limit
    );

}