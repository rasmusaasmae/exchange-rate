package com.aasmae.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyGrowthDTO {

    private String currency;
    private BigDecimal growthRate;

}