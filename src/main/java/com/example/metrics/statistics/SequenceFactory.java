package com.example.metrics.statistics;

public class SequenceFactory {
    public static Sequence<Double> ofDouble(){
        return new ArrayListSequence<>();
    }
}
