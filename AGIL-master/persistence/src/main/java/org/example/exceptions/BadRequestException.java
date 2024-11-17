package org.example.exceptions;

public class BadRequestException extends ApplicationException {
    public BadRequestException(String message) {
        super(message);
    }
}
