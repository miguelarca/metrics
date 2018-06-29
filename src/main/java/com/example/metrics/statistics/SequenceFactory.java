package com.example.metrics.statistics;

/**
 * Main factory to create sequences
 *
 * @author Miguel.Mendez
 */
public class SequenceFactory {
    public static TotalizingSequence<Double> ofDouble(){
        return new DoubleArrayListSequence();
    }
}
