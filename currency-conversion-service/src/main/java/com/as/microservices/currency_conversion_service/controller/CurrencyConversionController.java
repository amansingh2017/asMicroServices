package com.as.microservices.currency_conversion_service.controller;

import com.as.microservices.currency_conversion_service.model.CurrencyConversion;
import com.as.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;
    private final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveCurrencyConversion(
            @PathVariable("from") String from, @PathVariable String to, @PathVariable float quantity){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> currencyConversionResponseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = currencyConversionResponseEntity.getBody();
        currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" rest template");
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity*currencyConversion.getConversionMultiple());
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveCurrencyConversionFeign(
            @PathVariable("from") String from, @PathVariable String to, @PathVariable float quantity){
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveCurrencyMapping(from, to);
        currencyConversion.setEnvironment(currencyConversion.getEnvironment()+" openfeign");
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity*currencyConversion.getConversionMultiple());
        logger.info("retrieveCurrencyConversionFeign from {} to {} for quantity {} is {}", from, to, quantity, currencyConversion.getTotalCalculatedAmount());
        return currencyConversion;
    }
}
