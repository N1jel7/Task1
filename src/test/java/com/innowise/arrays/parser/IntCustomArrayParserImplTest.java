package com.innowise.arrays.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class IntCustomArrayParserImplTest {
    private CustomArrayParserImpl parser;

    @BeforeEach
    void setUp() {
        parser = new CustomArrayParserImpl();
    }

    @Test
    void shouldParseValidLineWithCommas() {
        // given
        String line = "1, 2, 3";

        // when
        Optional<int[]> result = parser.parseIntFromString(line);

        // then
        assertTrue(result.isPresent());
        assertArrayEquals(new int[]{1, 2, 3}, result.get());
    }

    @Test
    void shouldReturnEmptyForInvalidLineWithLetters() {
        // given
        String line = "1, x, 3";

        // when
        Optional<int[]> result = parser.parseIntFromString(line);

        // then
        assertFalse(result.isPresent());
    }
}
