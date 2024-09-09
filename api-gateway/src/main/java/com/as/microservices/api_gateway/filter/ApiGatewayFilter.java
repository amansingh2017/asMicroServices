package com.as.microservices.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayFilter{

    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/get").uri("http://www.google.com"))
                .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange-service"))
                .route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion-service"))
                .route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service")).build();
    }
}
