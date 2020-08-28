package com.sample.bankingApplication.controller;

import static org.mockito.Mockito.times;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.sample.bankingApplication.dao.CustomerRepository;
import com.sample.bankingApplication.handler.CustomerHandler;
import com.sample.bankingApplication.model.Customer;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
@Import(CustomerHandler.class)
public class CustomerControllerTest {

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	private WebTestClient webClient;

	@Test
	void testCreateEmployee() {
		Customer customer = new Customer("123456", "user50", "user50", "abc", "cde", "12saa", (long) 1234456778,
				"Chennai", "TN", "India", "cde@gmail.com", LocalDate.of(2017, 11, 6), "Savings");
		customer.setUserName("user50");
		customer.setPassword("user50");

		Mockito.when(customerRepository.save(customer)).thenReturn(Mono.just(customer));

		webClient.post().uri("/api/controller/signup").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(customer)).exchange().expectStatus().isCreated();

		Mockito.verify(customerRepository, times(1)).save(customer);
	}

}
