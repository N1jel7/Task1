package com.innowise.arrays.specification.impl;

import com.innowise.arrays.entity.CustomArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SumLessThanSpecificationTest {
    private SumLessThanSpecification specification;

    @Test
    void shouldReturnTrueWhenSumIsLessThanThreshold() {
        // given
        specification = new SumLessThanSpecification(20);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // sum = 15

        // when
        boolean result = specification.specify(array);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenSumIsEqualToThreshold() {
        // given
        specification = new SumLessThanSpecification(15);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // sum = 15

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSumIsGreaterThanThreshold() {
        // given
        specification = new SumLessThanSpecification(10);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // sum = 15

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullArray() {
        // given
        specification = new SumLessThanSpecification(1);

        // when
        boolean result = specification.specify(null);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForEmptyArray() {
        // given
        specification = new SumLessThanSpecification(1);
        CustomArray array = new CustomArray(new int[0]);

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldHandleNegativeThreshold() {
        // given
        specification = new SumLessThanSpecification(-5);
        CustomArray array = new CustomArray(new int[]{1, 2, 3}); // sum = 6

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }
}