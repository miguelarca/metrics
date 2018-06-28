package com.example.metrics.statistics;

public interface TotalizingSequence<T> extends Sequence<T> {
    T sum();
}
