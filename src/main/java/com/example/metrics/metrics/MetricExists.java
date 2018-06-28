package com.example.metrics.metrics;

import com.example.metrics.commons.exceptions.DomainException;

public class MetricExists extends DomainException {
    public MetricExists(String message) {
        super(message);
    }

    public MetricExists(String message, Throwable cause) {
        super(message, cause);
    }
}
