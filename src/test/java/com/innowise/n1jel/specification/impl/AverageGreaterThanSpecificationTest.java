package com.innowise.n1jel.specification.impl;

import com.innowise.n1jel.entity.CustomArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AverageGreaterThanSpecificationTest {
    private AverageGreaterThanSpecification specification;

    @Test
    void shouldReturnTrueWhenAverageIsGreaterThanThreshold() {
        // given
        specification = new AverageGreaterThanSpecification(2.9);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenAverageIsEqualToThreshold() {
        // given
        specification = new AverageGreaterThanSpecification(3.0);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenAverageIsLessThanThreshold() {
        // given
        specification = new AverageGreaterThanSpecification(5.0);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullArray() {
        // given
        specification = new AverageGreaterThanSpecification(1.0);

        // when
        boolean result = specification.specify(null);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForEmptyArray() {
        // given
        specification = new AverageGreaterThanSpecification(1.0);
        CustomArray array = new CustomArray(new int[0]);

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldHandleDecimalThreshold() {
        // given
        specification = new AverageGreaterThanSpecification(3.5);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }
}