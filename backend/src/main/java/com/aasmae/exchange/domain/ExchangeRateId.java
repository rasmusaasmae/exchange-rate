package com.aasmae.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRateId implements Serializable {

    private String currency;
    private LocalDate date;
}
