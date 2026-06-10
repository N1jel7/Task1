package com.innowise.n1jel.comparator.impl;

import com.innowise.n1jel.comparator.CustomArrayComparator;
import com.innowise.n1jel.entity.CustomArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayComparatorImplTest {
    private final CustomArrayComparator comparator = new CustomArrayComparatorImpl();
    private List<CustomArray> arrays;

    @BeforeEach
    void setUp() {
        arrays = new ArrayList<>();
        arrays.add(new CustomArray(new int[]{5, 2, 3}));
        arrays.add(new CustomArray(new int[]{1, 4}));
        arrays.add(new CustomArray(new int[]{9, 0, 1, 2}));
    }

    @Test
    void shouldSortById() {
        // when
        List<CustomArray> sorted = arrays.stream()
                .sorted(comparator.byId())
                .toList();

        // then
        assertAll("Sorting by ID validation",
                () -> assertTrue(sorted.get(0).getId().compareTo(sorted.get(1).getId()) < 0),
                () -> assertTrue(sorted.get(1).getId().compareTo(sorted.get(2).getId()) < 0)
        );
    }

    @Test
    void shouldSortByLength() {
        // when
        List<CustomArray> sorted = arrays.stream()
                .sorted(comparator.byLength())
                .toList();

        // then
        assertAll("Sorting by length validation",
                () -> assertEquals(2, sorted.get(0).getLength()),
                () -> assertEquals(3, sorted.get(1).getLength()),
                () -> assertEquals(4, sorted.get(2).getLength())
        );
    }

    @Test
    void shouldSortByFirstElement() {
        // when
        List<CustomArray> sorted = arrays.stream()
                .sorted(comparator.byFirstElement())
                .toList();

        // then
        assertAll("Sorting by first element validation",
                () -> assertEquals(1, sorted.get(0).getArray()[0]),
                () -> assertEquals(5, sorted.get(1).getArray()[0]),
                () -> assertEquals(9, sorted.get(2).getArray()[0])
        );
    }

    @Test
    void shouldHandleEmptyArrayWhenSortingByFirstElement() {
        // given
        CustomArray emptyArray = new CustomArray(new int[0]);
        arrays.add(emptyArray);

        // when
        List<CustomArray> sorted = arrays.stream()
                .sorted(comparator.byFirstElement())
                .toList();

        // then
        assertAll("Empty array handling validation",
                () -> assertTrue(sorted.get(0).isEmpty())
        );
    }
}