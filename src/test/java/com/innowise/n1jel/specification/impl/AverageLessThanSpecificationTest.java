package com.innowise.n1jel.specification.impl;

import com.innowise.n1jel.entity.CustomArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AverageLessThanSpecificationTest {
    private AverageLessThanSpecification specification;

    @Test
    void shouldReturnTrueWhenAverageIsLessThanThreshold() {
        // given
        specification = new AverageLessThanSpecification(5.0);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenAverageIsEqualToThreshold() {
        // given
        specification = new AverageLessThanSpecification(3.0);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenAverageIsGreaterThanThreshold() {
        // given
        specification = new AverageLessThanSpecification(2.0);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullArray() {
        // given
        specification = new AverageLessThanSpecification(1.0);

        // when
        boolean result = specification.specify(null);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForEmptyArray() {
        // given
        specification = new AverageLessThanSpecification(1.0);
        CustomArray array = new CustomArray(new int[0]);

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldHandleDecimalThreshold() {
        // given
        specification = new AverageLessThanSpecification(2.5);
        CustomArray array = new CustomArray(new int[]{1, 2, 3, 4, 5}); // average = 3.0

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }
}