package com.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payments.entities.Payment;

public interface PaymentRepository extends	JpaRepository<Payment, Long>{

}
