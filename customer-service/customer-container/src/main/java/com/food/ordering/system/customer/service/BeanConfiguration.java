package com.food.ordering.system.customer.service;

import com.food.ordering.system.customer.service.domain.CustomerDomainService;
import com.food.ordering.system.customer.service.domain.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class BeanConfiguration {

    /**
     * As the CustomerDomainService class is not marked as a bean (component, service). Mainly because we don't use
     * Spring boot for the domain.
     * In order tobe able to inject it as a Spring bean, in the customer-application-service
     * We need to register it here as a bean here.
     * That way, well be able to inject it, although we haven't registered it in the pom xml
     *
     * @return CustomerDomainService as a bean
     */
    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
