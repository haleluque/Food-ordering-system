package com.food.ordering.system.saga;

/**
 * Generic SAGA step interface than handles:
 * - processing transaction
 * - compensating transaction in case of failure
 * @param <T> Generic Object that represents the response consumer objects from either restaurant or payment service
 *           found in both kafka listeners
 */
public interface SagaStep<T> {
    void process(T data);

    void rollback(T data);
}
