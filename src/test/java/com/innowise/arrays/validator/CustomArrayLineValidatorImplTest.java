package com.innowise.arrays.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayLineValidatorImplTest {
    private final CustomArrayLineValidatorImpl validator = new CustomArrayLineValidatorImpl();

    @Test
    void shouldReturnTrueForValidLineWithCommas() {
        // given
        String line = "1, 2, 3";

        // when
        boolean result = validator.isValidLine(line);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForLineWithLetters() {
        // given
        String line = "1, x, 3";

        // when
        boolean result = validator.isValidLine(line);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForEmptyLine() {
        // given
        String line = "";

        // when
        boolean result = validator.isValidLine(line);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseForNullLine() {
        // given
        String line = null;

        // when
        boolean result = validator.isValidLine(line);

        // then
        assertFalse(result);
    }
}
