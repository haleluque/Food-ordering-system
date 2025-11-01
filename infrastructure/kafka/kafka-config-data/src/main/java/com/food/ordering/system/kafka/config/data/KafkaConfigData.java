package com.food.ordering.system.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class designed to map Kafka configuration properties from the different application.properties
 * found on the following modules:
 * - customer-container
 * - order-container
 * - restaurant-container
 * - payment-container
 * It maps them into a structured java object, using the prefix 'kafka-config'
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private Integer numOfPartitions;
    private Short replicationFactor;
}
