package com.reactive.app.loan.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.app.loan.domain.LoanDetails;

@Repository
public interface LoanRepository extends ReactiveMongoRepository<LoanDetails, String> {
}
