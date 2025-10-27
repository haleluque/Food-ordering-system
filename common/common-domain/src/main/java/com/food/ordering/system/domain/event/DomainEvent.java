package com.food.ordering.system.domain.event;

/**
 * This interface is used a marker interface, more specifically to
 * mark a class as a DomainEvent. And also to mark the entity class
 * using the generic type (Although is not used)
 *
 * @param <T> Marker Entity
 */
public interface DomainEvent<T> {
}
