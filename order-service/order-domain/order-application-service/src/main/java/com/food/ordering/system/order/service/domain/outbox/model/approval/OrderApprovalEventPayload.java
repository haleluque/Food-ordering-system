package com.food.ordering.system.order.service.domain.outbox.model.approval;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * This class is the JSON representation of the event domains for restaurant approval
 * that will be saved in the local outbox table, within the 'OrderApprovalOutboxMessage' - payload field as a string
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderApprovalEventPayload {
    @JsonProperty
    private String orderId;
    @JsonProperty
    private String restaurantId;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private ZonedDateTime createdAt;
    @JsonProperty
    private String restaurantOrderStatus;
    //subclass for products
    @JsonProperty
    private List<OrderApprovalEventProduct> products;
}
