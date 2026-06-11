package com.innowise.arrays.specification.impl;

import com.innowise.arrays.entity.CustomArray;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdSpecificationTest {
    private IdSpecification specification;

    @Test
    void shouldReturnTrueWhenIdsMatch() {
        // given
        CustomArray array = new CustomArray(new int[]{1, 2, 3});
        UUID targetId = array.getId();
        specification = new IdSpecification(targetId);

        // when
        boolean result = specification.specify(array);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenIdsDoNotMatch() {
        // given
        UUID targetId = UUID.randomUUID();
        specification = new IdSpecification(targetId);
        CustomArray array = new CustomArray(new int[]{1, 2, 3});

        // when
        boolean result = specification.specify(array);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullArray() {
        // given
        specification = new IdSpecification(UUID.randomUUID());

        // when
        boolean result = specification.specify(null);

        // then
        assertFalse(result);
    }
}