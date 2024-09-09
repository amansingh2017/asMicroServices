package com.as.microservices.currency_exchange_service.controller;

import com.as.microservices.currency_exchange_service.dao.DaoService;
import com.as.microservices.currency_exchange_service.model.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;
    private final DaoService daoService;

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    public CurrencyExchangeController(Environment environment, DaoService daoService) {
        this.environment = environment;
        this.daoService = daoService;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveCurrencyMapping(@PathVariable String from, @PathVariable String to){
        CurrencyExchange currencyExchange = daoService.getCurrencyExchange(from, to);
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        logger.info("retrieveCurrencyMapping : {}" , currencyExchange);
        return currencyExchange;
    }
}


