package com.innowise.arrays.observer.impl;

import com.innowise.arrays.entity.ArrayStatistic;
import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.observer.CustomArrayObserver;
import com.innowise.arrays.repository.CustomArrayRepository;
import com.innowise.arrays.repository.impl.CustomArrayRepositoryImpl;
import com.innowise.arrays.warehouse.CustomArrayWarehouse;
import com.innowise.arrays.warehouse.impl.CustomArrayWarehouseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayObserverImplTest {
    private final CustomArrayObserver observer = new CustomArrayObserverImpl();
    private final CustomArrayWarehouse warehouse = CustomArrayWarehouseImpl.getInstance();
    private final CustomArrayRepository repository = CustomArrayRepositoryImpl.getInstance();

    @BeforeEach
    void setUp() {
        warehouse.clear(); // Clear warehouse before each test
    }

    @Test
    void shouldCalculateAndStoreStatisticsWhenArrayChanges() {
        // given
        int[] data = {5, 2, 8, 1, 9};
        CustomArray array = new CustomArray(data);

        // when
        observer.customArrayChanged(array);

        // then
        Optional<ArrayStatistic> statistic = warehouse.getStatistic(array.getId());

        assertAll("Statistics calculation validation",
                () -> assertTrue(statistic.isPresent()),
                () -> assertEquals(1, statistic.get().min()),
                () -> assertEquals(9, statistic.get().max()),
                () -> assertEquals(25, statistic.get().sum()),
                () -> assertEquals(5.0, statistic.get().average(), 0.001)
        );
    }

    @Test
    void shouldHandleEmptyArray() {
        // given
        CustomArray emptyArray = new CustomArray(new int[0]);

        // when
        observer.customArrayChanged(emptyArray);

        // then
        Optional<ArrayStatistic> statistic = warehouse.getStatistic(emptyArray.getId());

        assertAll("Empty array statistics validation",
                () -> assertTrue(statistic.isPresent()),
                () -> assertEquals(0, statistic.get().min()),
                () -> assertEquals(0, statistic.get().max()),
                () -> assertEquals(0, statistic.get().sum()),
                () -> assertEquals(0.0, statistic.get().average(), 0.001)
        );
    }

    @Test
    void shouldHandleNullArray() {
        // when & then (no exception)
        assertAll("Null array handling",
                () -> assertDoesNotThrow(() -> observer.customArrayChanged(null))
        );
    }

    @Test
    void shouldUpdateStatisticsWhenArrayChanges() throws Exception {
        // given
        int[] data = {5, 2, 8};
        CustomArray array = new CustomArray(data);

        // Add to repository
        repository.add(array);

        // then - verify initial stats (already calculated by repository)
        ArrayStatistic initialStat = warehouse.getStatistic(array.getId()).get();
        assertEquals(15, initialStat.sum());

        // when - change array element
        array.setElement(0, 100);

        // then - verify updated stats (auto-updated via observer)
        ArrayStatistic updatedStat = warehouse.getStatistic(array.getId()).get();

        assertAll("Statistics update validation",
                () -> assertEquals(110, updatedStat.sum()),
                () -> assertNotEquals(initialStat.sum(), updatedStat.sum())
        );
    }
}