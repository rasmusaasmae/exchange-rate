package com.aasmae.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ConversionDTO {

    private BigDecimal amount;
    private BigDecimal rate;
}