package com.as.microservices.currency_conversion_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyConversion {

    private long id;
    private String from;
    private String to;
    private float quantity;
    private float conversionMultiple;
    private float totalCalculatedAmount;
    private String environment;
}
