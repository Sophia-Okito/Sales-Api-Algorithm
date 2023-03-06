package com.mintyn.test.salesapi.modules.messagebroker.publisher;

import com.mintyn.test.salesapi.modules.order.dtos.OrderDto;

public interface MessagPublisher {
    void publish(OrderDto dto);
}
