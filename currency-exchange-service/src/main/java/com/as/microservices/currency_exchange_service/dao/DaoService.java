package com.as.microservices.currency_exchange_service.dao;

import com.as.microservices.currency_exchange_service.model.CurrencyExchange;
import com.as.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoService {

    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public DaoService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public CurrencyExchange getCurrencyExchange(String fromCurrency, String toCurrency) {
        return currencyExchangeRepository.findByFromAndTo(fromCurrency, toCurrency);
    }
}
