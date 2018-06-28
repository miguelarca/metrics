package com.example.metrics.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

final class ArrayListSequence<T> implements Sequence<T> {
    private ArrayList<T> arrayList = new ArrayList<>();

    @Override
    public void insert(T value) {
        int point = this.findInsertionPoint(value);
        this.arrayList.add(point, value);
    }

    @SuppressWarnings("unchecked")
    private int findInsertionPoint(T insertionValue) {
        int low = 0;
        int high = size() - 1;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            Comparable<? super T> middleValue = (Comparable<T>) this.arrayList.get(middle);
            int compareToResult = middleValue.compareTo(insertionValue);

            if (compareToResult < 0)
                low = middle + 1;
            else if (compareToResult > 0)
                high = middle - 1;
            else {
                return middle;
            }
        }

        return low;
    }

    @Override
    public T getFirst() {
        return this.getElement(0);
    }

    @Override
    public T getLast() {
        return this.getElement(this.size() - 1);
    }

    @Override
    public T getElement(int index) {
        return this.arrayList.get(index);
    }

    @Override
    public int size() {
        return this.arrayList.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.arrayList.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        this.arrayList.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.arrayList.spliterator();
    }
}
