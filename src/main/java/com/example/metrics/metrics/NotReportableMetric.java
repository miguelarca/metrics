package com.example.metrics.metrics;

import com.example.metrics.commons.exceptions.DomainException;

public class NotReportableMetric extends DomainException {
    public NotReportableMetric(String message) {
        super("Metric does not contain elements to report on. " + message);
    }
}
