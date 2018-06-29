package com.example.metrics.statistics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Implementation of a TotalizingSequence of Doubles backed by an ArrayList. The array list is kept in ascending order
 * by finding the insertion point using a binary search and letting the ArrayList do the shifting
 *
 * @author Miguel.Mendez
 */
final class DoubleArrayListSequence implements TotalizingSequence<Double> {
    private ArrayList<Double> arrayList = new ArrayList<>();
    private Double sum = 0.0;

    /**
     * @inheritDoc
     *
     * Inserts the value in order by first finding the insertion point using a binary search and letting the ArrayList
     * do the shifting
     *
     * @param value Double
     */
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

    /**
     * @inheritDoc
     */
    @Override
    public Double getFirst() {
        return this.getElement(0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Double getLast() {
        int lastIndex = this.size();

        return this.getElement(lastIndex -1 );
    }

    /**
     * @inheritDoc
     */
    @Override
    public Double getElement(int index) {
        return this.arrayList.get(index);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int size() {
        return this.arrayList.size();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Iterator<Double> iterator() {
        return this.arrayList.iterator();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void forEach(Consumer<? super Double> action) {
        this.arrayList.forEach(action);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Spliterator<Double> spliterator() {
        return this.arrayList.spliterator();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Double sum() {
        return this.sum;
    }
}
