package com.example.metrics.commons.exceptions;

/**
 * Exception to be used when a particular aggregate is not found in any repository
 *
 * @author Miguel.Mendez
 */
public class ObjectNotFound extends RuntimeException {
    public ObjectNotFound(String message) {
        super(message);
    }

    public ObjectNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
