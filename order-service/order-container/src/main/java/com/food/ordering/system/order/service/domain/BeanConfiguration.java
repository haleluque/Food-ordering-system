package com.food.ordering.system.order.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * As the OrderDomainService class is not marked as a bean (component, service)
     * we register it here to return it as one, and be able to inject it
     *
     * @return OrderDomainService as a bean
     */
    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }
}
