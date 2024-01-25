package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueObject.CustomerId;
import com.food.ordering.system.domain.valueObject.Money;
import com.food.ordering.system.domain.valueObject.ProductId;
import com.food.ordering.system.domain.valueObject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entitiy.Order;
import com.food.ordering.system.order.service.domain.entitiy.OrderItem;
import com.food.ordering.system.order.service.domain.entitiy.Product;
import com.food.ordering.system.order.service.domain.entitiy.Restaurant;
import com.food.ordering.system.order.service.domain.valueObject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Factory class responsible for conversion and creation of domain objects
 * from the inputs dtos and vise versa
 */
@Component
public class OrderDataMapper {
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder()
                .id(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                                new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemsEntity(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemsEntity(List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> items) {
        return items.stream().map(orderItem ->
                OrderItem.Builder.builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .quantity(orderItem.getQuantity())
                        .price(new Money(orderItem.getPrice()))
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()).collect(Collectors.toList());
    }


    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
