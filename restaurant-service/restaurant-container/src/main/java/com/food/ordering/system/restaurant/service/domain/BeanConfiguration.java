package com.food.ordering.system.restaurant.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * As the RestaurantDomainService class is not marked as a bean (component, service). Mainly because we don't use
     * Spring boot for the domain.
     * In order tobe able to inject it as a Spring bean, in the restaurant-application-service
     * We need to register it here as a bean here.
     * That way, well be able to inject it, although we haven't registered it in the pom xml
     *
     * @return RestaurantDomainService as a bean
     */
    @Bean
    public RestaurantDomainService restaurantDomainService() {
        return new RestaurantDomainServiceImpl();
    }
}
