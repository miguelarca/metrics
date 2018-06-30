package com.example.metrics.statistics;

/**
 * Provides methods to mutate the sequence
 *
 * @param <T>
 * @author Miguel.Mendez
 */
public interface MutableSequence<T> extends Sequence<T>{
    /**
     * Inserts an element to the sequence in its natural order. keeping the sequence ordered
     *
     * @param value
     */
    void insert(T value);
}
