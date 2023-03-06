package com.mintyn.test.salesapi.modules.order.controllers;

import com.mintyn.test.salesapi.controllers.BaseController;
import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.modules.order.dtos.OrderDto;
import com.mintyn.test.salesapi.modules.order.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<StandardResponse> createOrder(@RequestBody @Valid OrderDto orderDto) {
        return updateResponseStatus(orderService.createOrder(orderDto));
    }
}
