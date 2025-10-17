package com.bankapp.CustomerService.interceptor;

import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.bankapp.CustomerService.util.ICustomerServiceConstants;

import feign.RequestInterceptor;
import feign.RequestTemplate;
@Component
public class CustomerServiceFiegnInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		Optional.ofNullable(MDC.get(ICustomerServiceConstants.CorrelationId))
				.ifPresent(correlationId -> template.header(ICustomerServiceConstants.CorrelationId, correlationId));
	}

}
