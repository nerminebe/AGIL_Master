package org.example.exceptions;

public class UnauthorizedException extends ApplicationException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
