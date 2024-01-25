package com.food.ordering.system.order.service.domain.dto.track;

import com.food.ordering.system.domain.valueObject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.cfg.defs.UUIDDef;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class TrackOrderResponse {
    @NotNull
    private final UUIDDef orderTrackingId;
    @NotNull
    private final OrderStatus orderStatus;
    private final List<String> failureMessage;
}
