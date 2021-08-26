package com.payments.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.payments.entities.Payment;
import com.payments.repositories.PaymentRepository;
import com.payments.services.PaymentService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<?> getAllPayments()	{
		
		List<Payment> payments = paymentRepository.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<?> getPaymentById(@PathVariable Long paymentId)	{
		
		Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);

		if(paymentOptional.isPresent())	{
			
			return ResponseEntity.status(HttpStatus.OK).body(paymentOptional.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("No payment history found for payment id : " + paymentId);
	}
	
	@PostMapping
	public ResponseEntity<?> createPaymentPlan(@RequestBody Payment paymentReq) {
		DecimalFormat df = new DecimalFormat("0.00");
		Payment payment = new Payment();
		
		double totalPayment = paymentReq.getTotalAmount();
		int noOfPaymetts = paymentReq.getNumberOfPayments();
		payment.setTotalAmount(totalPayment);
		payment.setNumberOfPayments(noOfPaymetts);
		
		if(totalPayment <= 0 || noOfPaymetts <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body("Invalid inputs");
		}
		
		if(totalPayment > 0 && noOfPaymetts > 0 ) {
			double formatedTotal = Double.valueOf(df.format(totalPayment));
			double formatedNP = Double.valueOf(df.format(noOfPaymetts));
			
			double regularPaymentAmount = formatedTotal / formatedNP;
			double formatedRP = Double.valueOf(df.format(regularPaymentAmount));
			payment.setRegularPaymentAmount(formatedRP);
			
			double  formated_RP_and_NP = formatedRP * formatedNP;
				
			double difference = formatedTotal - formated_RP_and_NP;
			if(difference > 0) {
				double lastAmount = formatedRP + Double.valueOf(df.format(difference));
				double formatedLastAmount = Double.valueOf(df.format(lastAmount));
				payment.setLastAmount(formatedLastAmount);
			}
		}
		return new ResponseEntity<Payment>(paymentService.createPayment(payment),
				HttpStatus.OK);
	}
}
