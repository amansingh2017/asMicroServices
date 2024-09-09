package com.as.microservices.currency_exchange_service.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleApiController {

    Logger logger = LoggerFactory.getLogger(SampleApiController.class);

    @GetMapping("/currency-exchange/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
    public String greetings(){
        logger.info("sample api has been called");
        new RestTemplate().getForObject("http://localhost:8080/sample-api/test", String.class);
        return "Hello World";
    }

    public String fallbackMethod(Exception ex){
        return "fallBack method returned";
    }
}
