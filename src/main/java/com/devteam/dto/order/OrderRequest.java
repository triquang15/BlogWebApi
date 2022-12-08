package com.devteam.dto.order;

import com.devteam.entity.Order;
import com.devteam.entity.Payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
