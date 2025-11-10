package com.food.ordering.system.order.service.domain.outbox.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * This class is the JSON representation of the event domains for payments
 * that will be saved in the local outbox table, within the 'OrderPaymentOutboxMessage' - payload field as a string
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderPaymentEventPayload {
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private String paymentOrderStatus;
}
