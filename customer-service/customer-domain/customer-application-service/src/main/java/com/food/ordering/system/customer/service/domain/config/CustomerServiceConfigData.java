package com.food.ordering.system.customer.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class designed to map Kafka configuration properties from application.yaml in the customer-container module
 * It maps them into a structured java object, using the prefix 'customer-service'
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "customer-service")
public class CustomerServiceConfigData {
    private String customerTopicName;
}
