package com.innowise.n1jel.observer.impl;

import com.innowise.n1jel.entity.ArrayStatistic;
import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.observer.CustomArrayObserver;
import com.innowise.n1jel.repository.CustomArrayRepository;
import com.innowise.n1jel.repository.impl.CustomArrayRepositoryImpl;
import com.innowise.n1jel.warehouse.CustomArrayWarehouse;
import com.innowise.n1jel.warehouse.impl.CustomArrayWarehouseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayObserverImplTest {
    private CustomArrayObserver observer;
    private CustomArrayWarehouse warehouse;
    private CustomArrayRepository repository;

    @BeforeEach
    void setUp() {
        observer = new CustomArrayObserverImpl();
        warehouse = CustomArrayWarehouseImpl.getInstance();
        repository = CustomArrayRepositoryImpl.getInstance();
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
        assertTrue(statistic.isPresent());
        assertEquals(1, statistic.get().min());
        assertEquals(9, statistic.get().max());
        assertEquals(25, statistic.get().sum());
        assertEquals(5.0, statistic.get().average(), 0.001);
    }

    @Test
    void shouldHandleEmptyArray() {
        // given
        CustomArray emptyArray = new CustomArray(new int[0]);

        // when
        observer.customArrayChanged(emptyArray);

        // then
        Optional<ArrayStatistic> statistic = warehouse.getStatistic(emptyArray.getId());
        assertTrue(statistic.isPresent());
        assertEquals(0, statistic.get().min());
        assertEquals(0, statistic.get().max());
        assertEquals(0, statistic.get().sum());
        assertEquals(0.0, statistic.get().average(), 0.001);
    }

    @Test
    void shouldHandleNullArray() {
        // when & then (no exception)
        observer.customArrayChanged(null);
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
        assertEquals(110, updatedStat.sum());
    }
}
