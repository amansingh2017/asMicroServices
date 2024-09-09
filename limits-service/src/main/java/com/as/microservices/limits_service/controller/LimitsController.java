package com.as.microservices.limits_service.controller;

import com.as.microservices.limits_service.configuration.LimitsConfiguration;
import com.as.microservices.limits_service.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private final LimitsConfiguration limitsConfiguration;

    @Autowired
    public LimitsController(LimitsConfiguration limitsConfiguration) {
        this.limitsConfiguration = limitsConfiguration;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return Limits.builder().minimum(limitsConfiguration.getMinimum())
                .maximum(limitsConfiguration.getMaximum()).build();
    }
}
