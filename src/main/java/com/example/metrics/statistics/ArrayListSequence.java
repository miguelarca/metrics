package com.example.metrics.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

final class ArrayListSequence implements TotalizingSequence<Double> {
    private ArrayList<Double> arrayList = new ArrayList<>();
    private Double sum = 0.0;

    @Override
    public void insert(Double value) {
        int point = this.findInsertionPoint(value);
        this.arrayList.add(point, value);
        this.sum += value;
    }

    @SuppressWarnings("unchecked")
    private int findInsertionPoint(Double insertionValue) {
        int low = 0;
        int high = size() - 1;

        while (low <= high) {
            int middle = (low + high) >>> 1;
            Comparable<? super Double> middleValue = this.arrayList.get(middle);
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
    public Double getFirst() {
        return this.getElement(0);
    }

    @Override
    public Double getLast() {
        int lastIndex = this.size() > 0 ? this.size() - 1 : 0;

        return this.getElement(lastIndex);
    }

    @Override
    public Double getElement(int index) {
        return this.arrayList.get(index);
    }

    @Override
    public int size() {
        return this.arrayList.size();
    }

    @Override
    public Iterator<Double> iterator() {
        return this.arrayList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Double> action) {
        this.arrayList.forEach(action);
    }

    @Override
    public Spliterator<Double> spliterator() {
        return this.arrayList.spliterator();
    }

    @Override
    public Double sum() {
        return this.sum;
    }
}
