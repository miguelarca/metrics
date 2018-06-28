package com.example.metrics.statistics;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ArrayListSequenceTest {
    private Sequence<Double> sequence;

    @Before
    public void setUp(){
        sequence = new ArrayListSequence<>();

        sequence.insert(3.0);
        sequence.insert(9.0);
        sequence.insert(1.0);
        sequence.insert(6.0);
        sequence.insert(5.0);
        sequence.insert(7.0);
        sequence.insert(8.0);
    }

    @Test
    public void itShouldInsertInOrder(){
        assertThat(sequence).containsSequence(1.0, 3.0, 5.0, 6.0, 7.0, 8.0, 9.0);
    }

    @Test
    public void itShouldReturnTheFirstElement(){
        assertThat(sequence.getFirst()).isEqualTo(1.0);
    }

    @Test
    public void itShouldReturnTheLastElement(){
        assertThat(sequence.getLast()).isEqualTo(9.0);
    }

    @Test
    public void itShouldReturnTheNElement(){
        assertThat(sequence.getElement(3)).isEqualTo(6.0);
    }

    @Test
    public void itShouldReturnTheIterator(){
        assertThat(sequence.iterator()).isNotEmpty();
    }

    @Test
    public void itShouldReturnTheForEach(){
        sequence.forEach(value -> assertThat(value).isInstanceOf(Double.class));
    }

}