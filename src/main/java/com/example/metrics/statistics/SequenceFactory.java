package com.example.metrics.statistics;

public class SequenceFactory {
    public static TotalizingSequence<Double> ofDouble(){
        return new ArrayListSequence();
    }
}
