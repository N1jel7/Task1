package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.IntCustomArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyticServiceImplTest {
    private AnalyticServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new AnalyticServiceImpl();
    }

    @Test
    void shouldFindMinValue() {
        // given
        int[] data = {5, 2, 8, 1, 9};
        IntCustomArray array = new IntCustomArray(data);

        // when
        Optional<Integer> result = service.findMinValue(array);

        // then
        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void shouldReturnEmptyForNullArray() {
        // when
        Optional<Integer> result = service.findMinValue(null);

        // then
        assertFalse(result.isPresent());
    }
}
