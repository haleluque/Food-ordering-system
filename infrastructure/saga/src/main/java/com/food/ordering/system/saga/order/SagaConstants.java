package com.food.ordering.system.saga.order;

public final class SagaConstants {

    private SagaConstants() {  }

    //Indicates the name of the SAGA flow to be saved in the outbox table
    //In this project we only have one SAGA flow - The processing of an order
    public static final String ORDER_SAGA_NAME = "OrderProcessingSaga";
}

