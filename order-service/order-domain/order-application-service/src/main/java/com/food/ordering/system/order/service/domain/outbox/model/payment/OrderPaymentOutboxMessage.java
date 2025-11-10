package com.food.ordering.system.order.service.domain.outbox.model.payment;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * This class models the fields that are found in the outbox table for the order domain
 * regarding a payment request event
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderPaymentOutboxMessage {
    private UUID id;
    private UUID sagaId;
    private ZonedDateTime createdAt;
    @Setter
    private ZonedDateTime processedAt;
    private String type;
    //JSON representation of the event domain
    //it is represented in the 'OrderPaymentEventPayload' class
    private String payload;
    @Setter
    private SagaStatus sagaStatus;
    @Setter
    private OrderStatus orderStatus;
    @Setter
    private OutboxStatus outboxStatus;
    //Used for optimistic locking
    private int version;
}
