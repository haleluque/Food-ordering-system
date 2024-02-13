package com.food.ordering.system.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String responseTopicName,
                                                                             T requestAvroModel, String orderId,
                                                                             String requestAvroModelName) {
        return (result, ex) -> {
            if (ex == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received new metadata for order id: {}, Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            } else {
                log.error("Error while sending " + requestAvroModelName + " message {} to topic {}", requestAvroModel.toString(), responseTopicName, ex);
            }
        };
    }

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
