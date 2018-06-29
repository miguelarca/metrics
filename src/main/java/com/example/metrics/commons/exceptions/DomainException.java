package com.example.metrics.commons.exceptions;

/**
 * Base exception to encapsulate all of the Domain Errors
 *
 * @author Miguel.Mendez
 */
public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
