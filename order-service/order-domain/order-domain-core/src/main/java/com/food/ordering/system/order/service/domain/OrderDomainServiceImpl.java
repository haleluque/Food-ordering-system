package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.food.ordering.system.domain.DomainConstants.UTC;

/**
 * This class works as an Application service class
 */
@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    //Use multiple aggregates to check business requirements
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} has been paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} has been approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelled for id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive()) {
            throw new OrderDomainException("Restaurant with id: " + restaurant.getId().getValue() +
                    " is not active");
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        //the product is the key and the value
        //In order to avoid a IllegalStateException, we provide a merge function
        Map<Product, Product> productLookup = restaurant.getProducts().stream()
                .collect(Collectors.toMap(
                        p -> p,
                        p -> p,
                        (existing, replacement) -> replacement
                ));

        order.getItems().forEach(orderItem -> {
            Product currentProduct = orderItem.getProduct();
            //easy to find as long as hashmap and equals are correctly implemented
            Product confirmedProduct = productLookup.get(currentProduct);
            if (confirmedProduct != null) {
                currentProduct.updateWithConfirmedNameAndPrice(
                        confirmedProduct.getName(),
                        confirmedProduct.getPrice()
                );
            }
        });
    }
}
