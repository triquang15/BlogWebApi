package com.devteam.service;

import com.devteam.dto.order.OrderRequest;
import com.devteam.dto.order.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
