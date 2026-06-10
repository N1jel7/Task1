package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.CustomArray;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyticServiceImplTest {

    private final StatisticServiceImpl service = new StatisticServiceImpl();
    ;

    @Test
    void shouldFindMinValueInArray() {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        Optional<Integer> result = service.findMinValue(array);

        // then
        assertAll("Find min value validation",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(1, result.get())
        );
    }

    @Test
    void shouldReturnEmptyForNullArray() {
        // given
        CustomArray array = null;

        // when
        Optional<Integer> result = service.findMinValue(array);

        // then
        assertAll("Null array handling for min value",
                () -> assertFalse(result.isPresent()),
                () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    void shouldFindMaxValueInArray() {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        Optional<Integer> result = service.findMaxValue(array);

        // then
        assertAll("Find max value validation",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(9, result.get())
        );
    }

    @Test
    void shouldReturnEmptyForNullArrayWhenFindingMax() {
        // given
        CustomArray array = null;

        // when
        Optional<Integer> result = service.findMaxValue(array);

        // then
        assertAll("Null array handling for max value",
                () -> assertFalse(result.isPresent()),
                () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    void shouldCalculateSumOfElements() {
        // given
        int[] data = {1, 2, 3, 4, 5};
        CustomArray array = new CustomArray(data);

        // when
        Optional<Integer> result = service.calculateSumOfElements(array);

        // then
        assertAll("Calculate sum validation",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(15, result.get())
        );
    }

    @Test
    void shouldReturnEmptyForNullArrayWhenCalculatingSum() {
        // given
        CustomArray array = null;

        // when
        Optional<Integer> result = service.calculateSumOfElements(array);

        // then
        assertAll("Null array handling for sum calculation",
                () -> assertFalse(result.isPresent()),
                () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    void shouldCalculateAverageOfElements() {
        // given
        int[] data = {1, 2, 3, 4, 5};
        CustomArray array = new CustomArray(data);

        // when
        Optional<Double> result = service.calculateAverageOfElements(array);

        // then
        assertAll("Calculate average validation",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(3.0, result.get(), 0.001),
                () -> assertTrue(result.get() > 0)
        );
    }

    @Test
    void shouldReturnEmptyForNullArrayWhenCalculatingAverage() {
        // given
        CustomArray array = null;

        // when
        Optional<Double> result = service.calculateAverageOfElements(array);

        // then
        assertAll("Null array handling for average calculation",
                () -> assertFalse(result.isPresent()),
                () -> assertTrue(result.isEmpty())
        );
    }
}