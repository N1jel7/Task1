package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.IntCustomArray;
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
    void bubbleSortShouldSortArrayInAscendingOrder() {
        // given
        int[] data = {5, 2, 8, 1, 9};
        IntCustomArray array = new IntCustomArray(data);

        // when
        service.bubbleSort(array);

        // then
        assertArrayEquals(new int[]{1, 2, 5, 8, 9}, array.getArray());
    }

    @Test
    void quickSortShouldSortArrayInAscendingOrder() {
        // given
        int[] data = {5, 2, 8, 1, 9};
        IntCustomArray array = new IntCustomArray(data);

        // when
        service.quickSort(array);

        // then
        assertArrayEquals(new int[]{1, 2, 5, 8, 9}, array.getArray());
    }
}
