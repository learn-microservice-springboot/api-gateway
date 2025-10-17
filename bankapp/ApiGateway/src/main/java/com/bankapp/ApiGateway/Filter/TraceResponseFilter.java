package com.bankapp.ApiGateway.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.bankapp.ApiGateway.config.IApiGatwayConstants;

import reactor.core.publisher.Mono;

@Component
public class TraceResponseFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			String correlationId = getCorrelationIdFromHeaders(exchange.getRequest().getHeaders());
			exchange.getResponse().getHeaders().add(IApiGatwayConstants.CorrelationId, correlationId);
		}));
	}

	private String getCorrelationIdFromHeaders(HttpHeaders headers) {
		String correlationId = null;
		if (headers.get(IApiGatwayConstants.CorrelationId) != null
				&& !headers.get(IApiGatwayConstants.CorrelationId).isEmpty()) {
			correlationId = (String) headers.get(IApiGatwayConstants.CorrelationId).get(0);
		}
		return correlationId;
	}
}
