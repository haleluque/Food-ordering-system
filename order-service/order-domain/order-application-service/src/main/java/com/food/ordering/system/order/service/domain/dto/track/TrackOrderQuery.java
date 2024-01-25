package com.food.ordering.system.order.service.domain.dto.track;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * The clients will use this to query the latest state of an order
 * using the trackingId
 */
@Builder
@AllArgsConstructor
@Getter
public class TrackOrderQuery {
    @NotNull
    private final UUID orderTrackingId;
}
