package com.food.ordering.system.restaurant.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class designed to map Kafka configuration properties from application.yaml in the restaurant-container module
 * It maps them into a structured java object, using the prefix 'restaurant-service'
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "restaurant-service")
public class RestaurantServiceConfigData {
    private String restaurantApprovalRequestTopicName;
    private String restaurantApprovalResponseTopicName;
}
