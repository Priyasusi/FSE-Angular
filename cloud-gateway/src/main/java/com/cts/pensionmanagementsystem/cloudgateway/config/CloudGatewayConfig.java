package com.cts.pensionmanagementsystem.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin("*")
public class CloudGatewayConfig {

	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(r -> r.path("/Authenticate").uri("http://localhost:8010/"))
				.route(r -> r.path("/Authorize").uri("http://localhost:8010/"))
				.route(r -> r.path("/PensionerDetailByAadhaar/**").uri("http://localhost:8020/"))
				.route(r -> r.path("/ProcessPension/**").uri("http://localhost:8030/"))
				.build();
	}

}
