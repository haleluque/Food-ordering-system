package com.food.ordering.system.domain.entitiy;

// Marker class
// determines which class is an entity and which one is an aggregate root
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {
}
