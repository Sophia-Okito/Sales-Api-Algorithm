package com.mintyn.test.salesapi.modules.messagebroker.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mintyn.test.salesapi.modules.order.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaPublisher implements MessagPublisher{

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.order.topic}")
    private String topic;


    @Override
    public void publish(OrderDto dto) {
        if (Objects.isNull(dto)) {
            return;
        }
        try {
            String message = new ObjectMapper().writeValueAsString(dto);
            kafkaTemplate.send(topic, message);
            log.info("message sent to queue successful: message - {} : topic - {} : class {}", message, topic, KafkaPublisher.class);
        } catch (Exception e) {
            log.error("message sent to queue unsuccessful : topic - {} : class {}", topic, KafkaPublisher.class, e);

        }

    }
}
