package com.example.metrics.statistics;

/**
 * Interface with methods that add the behaviour of an ordered sequence, with pointers to its head and tails.
 * It also keeps track of the size of the collection plus the ability to get elements randomly
 *
 * @param <T>
 *
 * @author Miguel.Mendez
 */
public interface Sequence<T> extends Iterable<T>{

    /**
     * It provides the first element of the sequence
     *
     * @return first element of the sequence
     */
    T getFirst();

    /**
     * It provides the last element of the sequence
     *
     * @return last element of the sequence
     */
    T getLast();

    /**
     * It provides the element of the sequence at the particular index
     *
     * @return element of the sequence
     */
    T getElement(int index);

    /**
     * It provides the size of the sequence
     *
     * @return size of the sequence
     */
    int size();
}
