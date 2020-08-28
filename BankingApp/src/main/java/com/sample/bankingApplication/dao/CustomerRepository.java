package com.sample.bankingApplication.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.bankingApplication.model.Customer;

import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

	public Mono<Customer> findByUserIdAndPassword(String username, String password);
}
