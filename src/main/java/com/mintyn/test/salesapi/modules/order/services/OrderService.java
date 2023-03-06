package com.mintyn.test.salesapi.modules.order.services;

import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.modules.order.dtos.OrderDto;

public interface OrderService {
    StandardResponse createOrder(OrderDto dto);
}
