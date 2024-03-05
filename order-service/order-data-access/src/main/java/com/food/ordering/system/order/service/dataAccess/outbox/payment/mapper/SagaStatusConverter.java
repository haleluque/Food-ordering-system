package com.food.ordering.system.order.service.dataAccess.outbox.payment.mapper;

import com.food.ordering.system.saga.SagaStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SagaStatusConverter implements AttributeConverter<SagaStatus, String> {
    @Override
    public String convertToDatabaseColumn(SagaStatus status) {
        return status.toString();
    }

    @Override
    public SagaStatus convertToEntityAttribute(String dbValue) {
        return SagaStatus.valueOf(dbValue);
    }
}