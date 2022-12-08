package com.devteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
