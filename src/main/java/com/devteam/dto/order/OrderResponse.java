package com.devteam.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private String orderTackingNumber;
    private String status;
    private String message;
}
