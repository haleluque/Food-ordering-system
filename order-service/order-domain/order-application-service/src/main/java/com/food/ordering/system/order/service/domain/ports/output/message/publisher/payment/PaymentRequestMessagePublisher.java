package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.food.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 //Used to send a callback and know if the published messaged was done successfully
                 //The kafka producer is asynchronous and uses a callback method to be called later, see
                 // com.food.ordering.system.kafka.producer.KafkaMessageHelper - getKafkaCallback
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
