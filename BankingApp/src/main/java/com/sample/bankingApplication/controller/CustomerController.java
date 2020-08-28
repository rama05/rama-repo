package com.sample.bankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.bankingApplication.dao.CustomerRepository;
import com.sample.bankingApplication.model.Customer;

import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	/*
	 * Controller for registering/signup into bank application
	 * 
	 */
	@PostMapping("/api/controller/signup")
	public Mono<ResponseEntity<String>> signUp(@RequestBody Customer customer) {

		return customerRepository.save(customer)
				.map(customerNew -> new ResponseEntity<>("Registered successfully", HttpStatus.CREATED))
				.defaultIfEmpty(new ResponseEntity<>("User Notfound", HttpStatus.NOT_FOUND));
	}

	/*
	 * Controller for login to bank application
	 * 
	 */
	@PostMapping("/api/controller/login")
	public Mono<ResponseEntity<String>> findByUserIdAndPassword(@RequestBody Customer customer) {
		return customerRepository.findByUserIdAndPassword(customer.getUserId(), customer.getPassword())
				.map(regcustomer -> new ResponseEntity<>("Loggedin successfully", HttpStatus.FOUND))
				.defaultIfEmpty(new ResponseEntity<>("Customer Notfound", HttpStatus.NOT_FOUND));
	}

	/*
	 * Controller for updating the customer details of the banking application
	 * 
	 */

	@PutMapping("/api/controller/update/{userId}")
	public Mono<ResponseEntity<Customer>> updateUser(@PathVariable String userId, @RequestBody Customer customer) {

		return customerRepository.findById(userId).flatMap(regCustomer -> {
			regCustomer.setContactNumber(customer.getContactNumber());
			regCustomer.setEmail(customer.getEmail());
			regCustomer.setPanNumber(customer.getPanNumber());
			return customerRepository.save(regCustomer);
		}).map(updatedCustomer -> new ResponseEntity<>(updatedCustomer, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
