package com.sample.bankingApplication.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sample.bankingApplication.handler.CustomerHandler;

@Configuration
public class CustomerRouter {

	@Bean
	public RouterFunction<ServerResponse> bankingOperations(CustomerHandler customerHandler) {

		return RouterFunctions
				.route(POST("/api/controller/signup").and(accept(MediaType.APPLICATION_JSON)), customerHandler::signup)
				.andRoute(POST("/api/controller/login").and(accept(MediaType.APPLICATION_JSON)),
						customerHandler::userLogin)
				.andRoute(PUT("/api/controller/update/{userId}").and(accept(MediaType.APPLICATION_JSON)),
						customerHandler::update);

	}
}
