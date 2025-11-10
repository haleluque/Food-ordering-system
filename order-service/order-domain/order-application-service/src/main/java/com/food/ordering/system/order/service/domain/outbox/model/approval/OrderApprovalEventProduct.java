package com.food.ordering.system.order.service.domain.outbox.model.approval;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * This subclass is the JSON representation of the event domains for restaurant approval
 * it is part of the 'OrderApprovalEventPayload' class
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderApprovalEventProduct {
    @JsonProperty
    private String id;
    @JsonProperty
    private Integer quantity;
}
