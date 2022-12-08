package com.devteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
