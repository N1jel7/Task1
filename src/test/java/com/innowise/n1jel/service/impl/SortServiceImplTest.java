package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortServiceImplTest {
    private SortServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new SortServiceImpl();
    }

    @Test
    void bubbleSortShouldSortArrayInAscendingOrder() throws CustomArrayException {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        service.bubbleSort(array);

        // then
        assertArrayEquals(new int[]{1, 2, 5, 8, 9}, array.getArray());
    }

    @Test
    void bubbleSortShouldHandleAlreadySortedArray() throws CustomArrayException {
        // given
        int[] data = {1, 2, 3, 4, 5};
        CustomArray array = new CustomArray(data);

        // when
        service.bubbleSort(array);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array.getArray());
    }

    @Test
    void quickSortShouldSortArrayInAscendingOrder() throws CustomArrayException {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        service.quickSort(array);

        // then
        assertArrayEquals(new int[]{1, 2, 5, 8, 9}, array.getArray());
    }

    @Test
    void quickSortShouldHandleAlreadySortedArray() throws CustomArrayException {
        // given
        int[] data = {1, 2, 3, 4, 5};
        CustomArray array = new CustomArray(data);

        // when
        service.quickSort(array);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array.getArray());
    }
}
