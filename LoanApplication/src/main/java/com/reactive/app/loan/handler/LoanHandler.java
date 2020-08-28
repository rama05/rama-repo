package com.reactive.app.loan.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.app.loan.domain.LoanDetails;
import com.reactive.app.loan.repository.LoanRepository;

import reactor.core.publisher.Mono;

@Component
public class LoanHandler {
	@Autowired
	LoanRepository loanRepository;
	static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

	public Mono<ServerResponse> applyLoan(ServerRequest serverRequest) {

		Mono<LoanDetails> loanDetails = serverRequest.bodyToMono(LoanDetails.class);
		return loanDetails.flatMap(loan -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(loanRepository.save(loan), LoanDetails.class).switchIfEmpty(notFound));

	}

	public Mono<ServerResponse> viewLoanDetails(ServerRequest serverRequest) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(loanRepository.findAll(),
				LoanDetails.class);

	}
}
