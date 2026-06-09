package com.innowise.n1jel.warehouse.impl;

import com.innowise.n1jel.entity.ArrayStatistic;
import com.innowise.n1jel.warehouse.CustomArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayWarehouseImplTest {
    private CustomArrayWarehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = CustomArrayWarehouseImpl.getInstance();
        warehouse.clear();
    }

    @Test
    void shouldPutAndGetStatistic() {
        // given
        UUID id = UUID.randomUUID();
        ArrayStatistic statistic = new ArrayStatistic(1, 10, 55, 5.5);

        // when
        warehouse.put(id, statistic);

        // then
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().min());
        assertEquals(10, result.get().max());
        assertEquals(55, result.get().sum());
        assertEquals(5.5, result.get().average(), 0.001);
    }

    @Test
    void shouldReturnEmptyWhenStatisticNotFound() {
        // given
        UUID id = UUID.randomUUID();

        // when
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);

        // then
        assertFalse(result.isPresent());
    }

    @Test
    void shouldRemoveStatistic() {
        // given
        UUID id = UUID.randomUUID();
        ArrayStatistic statistic = new ArrayStatistic(1, 10, 55, 5.5);
        warehouse.put(id, statistic);

        // when
        warehouse.removeStatistic(id);

        // then
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldUpdateExistingStatistic() {
        // given
        UUID id = UUID.randomUUID();
        ArrayStatistic oldStatistic = new ArrayStatistic(1, 10, 55, 5.5);
        ArrayStatistic newStatistic = new ArrayStatistic(2, 20, 110, 11.0);

        // when
        warehouse.put(id, oldStatistic);
        warehouse.put(id, newStatistic);

        // then
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);
        assertTrue(result.isPresent());
        assertEquals(2, result.get().min());
        assertEquals(20, result.get().max());
    }

    @Test
    void shouldStoreMultipleStatistics() {
        // given
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        ArrayStatistic statistic1 = new ArrayStatistic(1, 5, 15, 3.0);
        ArrayStatistic statistic2 = new ArrayStatistic(2, 8, 20, 4.0);

        // when
        warehouse.put(id1, statistic1);
        warehouse.put(id2, statistic2);

        // then
        assertTrue(warehouse.getStatistic(id1).isPresent());
        assertTrue(warehouse.getStatistic(id2).isPresent());
    }
}
