package com.bankapp.ApiGateway.config;

import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfig {

	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route("customer-service-route", r -> r.path("/api/bankapp/customer/**")
						.filters(f -> f.rewritePath("/api/bankapp/customer(?<segment>.*)", "/api/customer${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CUSTOMERSERVICE"))
				.route("loan-service-route",
						r -> r.path("/api/bankapp/loans/**")
								.filters(f -> f.rewritePath("/api/bankapp/loans(?<segment>.*)", "/api/loans${segment}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://LOANSERVICE"))
				.build();

	}

}
