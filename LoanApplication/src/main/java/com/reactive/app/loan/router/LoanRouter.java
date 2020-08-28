package com.reactive.app.loan.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.app.loan.handler.LoanHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class LoanRouter {

	@Bean
	public RouterFunction<ServerResponse> loanRouters(LoanHandler loanHandler) {

		return RouterFunctions
				.route(POST("/api/loan/apply").and(accept(MediaType.APPLICATION_JSON)), loanHandler::applyLoan)
				.andRoute(GET("/api/loan/viewLoanDetails").and(accept(MediaType.APPLICATION_JSON)),
						loanHandler::viewLoanDetails);

	}
}
