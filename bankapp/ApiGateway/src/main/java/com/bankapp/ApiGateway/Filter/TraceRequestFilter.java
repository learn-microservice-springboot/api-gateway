package com.bankapp.ApiGateway.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import com.bankapp.ApiGateway.config.IApiGatwayConstants;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class TraceRequestFilter implements GlobalFilter {

	private static final Logger logger = LoggerFactory.getLogger(TraceRequestFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		HttpHeaders headers = exchange.getRequest().getHeaders();
		String correlationId = getCorrelationIdFromHeaders(headers);
		if (StringUtils.hasText(correlationId)) {
			logger.debug("[{}:{}]  found in incoming request header", IApiGatwayConstants.CorrelationId,
					correlationId);
		} else {
			correlationId = java.util.UUID.randomUUID().toString();
			logger.debug("[{}:{}] generated and placed in request header", IApiGatwayConstants.CorrelationId,
					correlationId);
			exchange = exchange.mutate()
					.request(exchange.getRequest().mutate().header(IApiGatwayConstants.CorrelationId, correlationId).build()).build();
		}
		return chain.filter(exchange);

	}

	private String getCorrelationIdFromHeaders(HttpHeaders headers) {
		String correlationId = null;
		if (headers.get(IApiGatwayConstants.CorrelationId) != null && !headers.get(IApiGatwayConstants.CorrelationId).isEmpty()) {
			correlationId = (String) headers.get(IApiGatwayConstants.CorrelationId).get(0);
		}
		return correlationId;
	}

}
