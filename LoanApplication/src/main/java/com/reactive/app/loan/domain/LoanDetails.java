package com.reactive.app.loan.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class LoanDetails {
	@Id
	private String userid;
	private String loanType;
	private Double loanAmount;
	private LocalDate loanDate;
	private Double rateOfInterest;
	private Double durationOfLoan;

	public LoanDetails(String userid, String loanType, Double loanAmount, LocalDate loanDate, Double rateOfInterest,
			Double durationOfLoan) {
		this.userid = userid;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.loanDate = loanDate;
		this.rateOfInterest = rateOfInterest;
		this.durationOfLoan = durationOfLoan;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public Double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public Double getDurationOfLoan() {
		return durationOfLoan;
	}

	public void setDurationOfLoan(Double durationOfLoan) {
		this.durationOfLoan = durationOfLoan;
	}

}
