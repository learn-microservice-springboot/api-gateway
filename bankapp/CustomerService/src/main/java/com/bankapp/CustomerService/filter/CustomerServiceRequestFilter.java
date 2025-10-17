package com.bankapp.CustomerService.filter;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bankapp.CustomerService.util.ICustomerServiceConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class CustomerServiceRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			Optional.ofNullable(request.getHeader(ICustomerServiceConstants.CorrelationId))
					.ifPresent(correlationId -> MDC.put(ICustomerServiceConstants.CorrelationId, correlationId));
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove(ICustomerServiceConstants.CorrelationId);
		}
	}

}
