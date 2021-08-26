package com.payments.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "PAYMENT_PLANS")
public class Payment {

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE
    )
	private Long paymentId;
	
	@Column(name = "TOTAL_AMOUNT")
	private double totalAmount;
	
	@Column(name = "No_Of_PAYMENTS")
	private int numberOfPayments;
	
	@Column(name = "REGULAR_PAYMENT_AMOUNT")
	private double regularPaymentAmount;
	
	@JsonSerialize(include = Inclusion.NON_DEFAULT)
	@Column(name = "LAST_AMOUNT")
	private double lastAmount;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNumberOfPayments() {
		return numberOfPayments;
	}

	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public double getRegularPaymentAmount() {
		return regularPaymentAmount;
	}

	public void setRegularPaymentAmount(double regularPaymentAmount) {
		this.regularPaymentAmount = regularPaymentAmount;
	}

	public double getLastAmount() {
		return lastAmount;
	}

	public void setLastAmount(double lastAmount) {
		this.lastAmount = lastAmount;
	}
		
}

