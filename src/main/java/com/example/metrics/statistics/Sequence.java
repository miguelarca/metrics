package com.example.metrics.statistics;

public interface Sequence<T> extends Iterable<T>{
    void insert(T value);
    T getFirst();
    T getLast();
    T getElement(int index);
    int size();
}
