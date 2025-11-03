package com.food.ordering.system.payment.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    /**
     * As the PaymentDomainService class is not marked as a bean (component, service). Mainly because we don't use
     * Spring boot for the domain.
     * In order tobe able to inject it as a Spring bean, in the payment-application-service
     * We need to register it here as a bean here.
     * That way, well be able to inject it, although we haven't registered it in the pom xml
     *
     * @return PaymentDomainService as a bean
     */
    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
