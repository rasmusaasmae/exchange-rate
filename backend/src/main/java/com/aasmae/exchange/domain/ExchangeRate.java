package com.aasmae.exchange.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "exchange_rate")
@IdClass(ExchangeRateId.class)
public class ExchangeRate {

    @Id
    private String currency;

    @Id
    private LocalDate date;
    private BigDecimal rate;

}
