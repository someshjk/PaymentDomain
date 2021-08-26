package com.payments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments.entities.Payment;
import com.payments.repositories.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public Payment createPayment(Payment payment) {
		Payment output = paymentRepository.save(payment);
		return output;	
	}

}
