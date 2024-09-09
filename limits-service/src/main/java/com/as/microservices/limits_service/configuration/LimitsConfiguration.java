package com.as.microservices.limits_service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-services")
@Data
public class LimitsConfiguration {

    private int minimum;
    private int maximum;
}
