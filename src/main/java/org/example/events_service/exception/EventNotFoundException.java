package org.example.events_service.exception;

import java.util.UUID;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(UUID eventId) {
        super("Event with id " + eventId + " not found");
    }
}
