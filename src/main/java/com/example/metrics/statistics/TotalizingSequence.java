package com.example.metrics.statistics;

/**
 * A totalizing sequence keeps the sum of all of its elements
 *
 * @param <T>
 * @author Miguel.Mendez
 */
public interface TotalizingSequence<T> extends Sequence<T> {
    /**
     * Provides the sum of all of the elements of the sequence when possible.
     * Implementing this method is optional since the underlying sequence must contain numerical elements
     *
     * @return the sum of all of the elements
     */
    T sum();
}
