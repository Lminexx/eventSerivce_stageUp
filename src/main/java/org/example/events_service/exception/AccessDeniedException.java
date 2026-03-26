package org.example.events_service.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Это не ваше мероприятие");
    }
}
