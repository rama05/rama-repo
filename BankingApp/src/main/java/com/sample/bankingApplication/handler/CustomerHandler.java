package com.sample.bankingApplication.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sample.bankingApplication.dao.CustomerRepository;
import com.sample.bankingApplication.model.Customer;

import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

	@Autowired
	CustomerRepository customerRepository;

	static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

	/*
	 * Signup
	 * 
	 */
	public Mono<ServerResponse> signup(ServerRequest serverRequest) {

		Mono<Customer> newCustomer = serverRequest.bodyToMono(Customer.class);

		return newCustomer.flatMap(customer -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(customerRepository.save(customer), Customer.class));
	}

	/*
	 * Login
	 * 
	 */
	public Mono<ServerResponse> userLogin(ServerRequest serverRequest) {
		Mono<Customer> existCustomer = serverRequest.bodyToMono(Customer.class);
		return existCustomer.flatMap(userLogin -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(customerRepository.findByUserIdAndPassword(userLogin.getUserId(), userLogin.getPassword()),
						Customer.class)
				.switchIfEmpty(notFound));
	}

	/*
	 * Update
	 * 
	 */

	public Mono<ServerResponse> update(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("userId");

		Mono<Customer> update = serverRequest.bodyToMono(Customer.class).flatMap(updateCustomer -> {
			Mono<Customer> existingUser = customerRepository.findById(id).flatMap(currentCustomer -> {
				currentCustomer.setContactNumber(updateCustomer.getContactNumber());
				currentCustomer.setEmail(updateCustomer.getEmail());
				currentCustomer.setPanNumber(updateCustomer.getPanNumber());
				return customerRepository.save(currentCustomer);
			});
			return existingUser;
		});

		return update.flatMap(updated -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(fromObject(updated)).switchIfEmpty(notFound));

	}

}
