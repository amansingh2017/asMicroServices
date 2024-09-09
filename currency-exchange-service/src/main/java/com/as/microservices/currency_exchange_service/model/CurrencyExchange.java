package com.as.microservices.currency_exchange_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CurrencyExchange {

    @Id
    private long id;

    @Column(name = "fromCurrency")
    private String from;
    @Column(name = "toCurrency")
    private String to;
    private float conversionMultiple;
    private String environment;
}
