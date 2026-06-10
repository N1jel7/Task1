package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortServiceImplTest {
    private final SortServiceImpl service = new SortServiceImpl();

    @Test
    void bubbleSortShouldSortArrayInAscendingOrder() throws CustomArrayException {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        service.bubbleSort(array);

        // then
        int[] expected = {1, 2, 5, 8, 9};
        int[] actual = array.getArray();

        assertAll("Bubble sort ascending order validation",
                () -> assertArrayEquals(expected, actual),
                () -> assertEquals(expected.length, actual.length),
                () -> assertTrue(actual[0] < actual[actual.length - 1]),
                () -> assertEquals(1, actual[0]),
                () -> assertEquals(9, actual[actual.length - 1])
        );
    }

    @Test
    void bubbleSortShouldHandleAlreadySortedArray() throws CustomArrayException {
        // given
        int[] data = {1, 2, 3, 4, 5};
        CustomArray array = new CustomArray(data);

        // when
        service.bubbleSort(array);

        // then
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = array.getArray();

        assertAll("Bubble sort with already sorted array validation",
                () -> assertArrayEquals(expected, actual),
                () -> assertEquals(expected.length, actual.length),
                () -> assertArrayEquals(data, actual) // Should remain unchanged
        );
    }

    @Test
    void quickSortShouldSortArrayInAscendingOrder() throws CustomArrayException {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        service.quickSort(array);

        // then
        int[] expected = {1, 2, 5, 8, 9};
        int[] actual = array.getArray();

        assertAll("Quick sort ascending order validation",
                () -> assertArrayEquals(expected, actual),
                () -> assertEquals(expected.length, actual.length),
                () -> assertTrue(actual[0] < actual[actual.length - 1]),
                () -> assertEquals(1, actual[0]),
                () -> assertEquals(9, actual[actual.length - 1])
        );
    }

    @Test
    void quickSortShouldHandleAlreadySortedArray() throws CustomArrayException {
        // given
        int[] data = {1, 2, 3, 4, 5};
        CustomArray array = new CustomArray(data);

        // when
        service.quickSort(array);

        // then
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = array.getArray();

        assertAll("Quick sort with already sorted array validation",
                () -> assertArrayEquals(expected, actual),
                () -> assertEquals(expected.length, actual.length),
                () -> assertArrayEquals(data, actual) // Should remain unchanged
        );
    }
}