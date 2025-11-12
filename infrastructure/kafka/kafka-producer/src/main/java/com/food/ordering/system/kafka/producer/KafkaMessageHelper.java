package com.food.ordering.system.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import com.food.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Generic method to extract and cast the payload field from the outbox table's field (JSON)
     * It could receive either 'OrderApprovalEventPayload' or 'OrderPaymentEventPayload' classes
     */
    public <T> T getOrderEventPayload(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            log.error("Could not read {} object!", outputType.getName(), e);
            throw new OrderDomainException("Could not read " + outputType.getName() + " object!", e);
        }
    }

    /**
     * Generic Callback method that is executed when kafka responses after publishing an event,
     * It is responsible as well for sending the end state for the Outbox transaction to its implementation,
     * depending on the kafka response
     * ('e.g' RestaurantApprovalOutboxScheduler.updateOutboxStatus impl method)
     */
    public <T, U> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String responseTopicName, T avroModel, U outboxMessage,
                                                                                BiConsumer<U, OutboxStatus> outboxCallback,
                                                                                String orderId, String avroModelName) {
        return (result, ex) -> {
            if (ex == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            } else {
                log.error("Error while sending {} with message: {} and outbox type: {} to topic {}",
                        avroModelName, avroModel.toString(), outboxMessage.getClass().getName(), responseTopicName, ex);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }

//DEPRECATED
//    public <T> ListenableFutureCallback<SendResult<String, T>>
//    getKafkaCallback(String responseTopicName, T avroModel, String orderId, String avroModelName) {
//        return new ListenableFutureCallback<SendResult<String, T>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                log.error("Error while sending " + avroModelName +
//                        " message {} to topic {}", avroModel.toString(), responseTopicName, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, T> result) {
//                RecordMetadata metadata = result.getRecordMetadata();
//                log.info("Received successful response from Kafka for order id: {}" +
//                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
//                        orderId,
//                        metadata.topic(),
//                        metadata.partition(),
//                        metadata.offset(),
//                        metadata.timestamp());
//            }
//        };
//    }
}
