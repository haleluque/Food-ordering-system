package com.food.ordering.system.domain.event;

/**
 * This class represents the final process when no other events will be triggered
 * at the end of a transaction
 */
public final class EmptyEvent implements DomainEvent<Void>{
    public static final EmptyEvent INSTANCE = new EmptyEvent();

    private EmptyEvent() {
    }

    @Override
    public void fire() {

    }
}
