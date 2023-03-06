package com.mintyn.test.salesapi.modules.order.services;

import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.enums.Status;
import com.mintyn.test.salesapi.exceptions.BadRequestException;
import com.mintyn.test.salesapi.modules.messagebroker.publisher.MessagPublisher;
import com.mintyn.test.salesapi.modules.order.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MessagPublisher messagPublisher;


    @Override
    public StandardResponse createOrder(OrderDto dto) {
        if (Objects.isNull(dto.getItems()) || dto.getItems().isEmpty()) {
            throw new BadRequestException("order item is required");
        }

        messagPublisher.publish(dto);
        return new StandardResponse(Status.PROCESSING, "order processing");
    }

}
