package com.example.metrics.metrics;

import org.springframework.hateoas.Identifiable;

public interface NameableMetric extends Identifiable<String> {
    String getName();
}
