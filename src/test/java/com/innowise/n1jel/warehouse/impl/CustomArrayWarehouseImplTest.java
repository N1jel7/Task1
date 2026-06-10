package com.innowise.n1jel.warehouse.impl;

import com.innowise.n1jel.entity.ArrayStatistic;
import com.innowise.n1jel.warehouse.CustomArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayWarehouseImplTest {
    private final CustomArrayWarehouse warehouse = CustomArrayWarehouseImpl.getInstance();

    @BeforeEach
    void setUp() {
        warehouse.clear();
    }

    @Test
    void shouldPutStatisticAndGetStatistic() {
        // given
        UUID id = UUID.randomUUID();
        ArrayStatistic statistic = new ArrayStatistic(1, 10, 55, 5.5);

        // when
        warehouse.putStatistic(id, statistic);

        // then
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);

        assertAll("Put and get statistic validation",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(1, result.get().min()),
                () -> assertEquals(10, result.get().max()),
                () -> assertEquals(55, result.get().sum()),
                () -> assertEquals(5.5, result.get().average(), 0.001)
        );
    }

    @Test
    void shouldReturnEmptyWhenStatisticNotFound() {
        // given
        UUID id = UUID.randomUUID();

        // when
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);

        // then
        assertAll("Statistic not found validation",
                () -> assertFalse(result.isPresent()),
                () -> assertTrue(result.isEmpty()),
                () -> assertFalse(result.isPresent())
        );
    }

    @Test
    void shouldRemoveStatistic() {
        // given
        UUID id = UUID.randomUUID();
        ArrayStatistic statistic = new ArrayStatistic(1, 10, 55, 5.5);
        warehouse.putStatistic(id, statistic);

        // when
        warehouse.removeStatistic(id);

        // then
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);

        assertAll("Remove statistic validation",
                () -> assertFalse(result.isPresent()),
                () -> assertTrue(result.isEmpty()),
                () -> assertEquals(Optional.empty(), result)
        );
    }

    @Test
    void shouldUpdateExistingStatistic() {
        // given
        UUID id = UUID.randomUUID();
        ArrayStatistic oldStatistic = new ArrayStatistic(1, 10, 55, 5.5);
        ArrayStatistic newStatistic = new ArrayStatistic(2, 20, 110, 11.0);

        // when
        warehouse.putStatistic(id, oldStatistic);
        warehouse.putStatistic(id, newStatistic);

        // then
        Optional<ArrayStatistic> result = warehouse.getStatistic(id);

        assertAll("Update existing statistic validation",
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(2, result.get().min()),
                () -> assertEquals(20, result.get().max()),
                () -> assertEquals(110, result.get().sum()),
                () -> assertEquals(11.0, result.get().average(), 0.001),
                () -> assertNotEquals(oldStatistic.min(), result.get().min()),
                () -> assertNotEquals(oldStatistic.max(), result.get().max())
        );
    }

    @Test
    void shouldStoreMultipleStatistics() {
        // given
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        ArrayStatistic statistic1 = new ArrayStatistic(1, 5, 15, 3.0);
        ArrayStatistic statistic2 = new ArrayStatistic(2, 8, 20, 4.0);

        // when
        warehouse.putStatistic(id1, statistic1);
        warehouse.putStatistic(id2, statistic2);

        // then
        Optional<ArrayStatistic> result1 = warehouse.getStatistic(id1);
        Optional<ArrayStatistic> result2 = warehouse.getStatistic(id2);

        assertAll("Store multiple statistics validation",
                () -> assertTrue(result1.isPresent()),
                () -> assertTrue(result2.isPresent()),
                () -> assertEquals(1, result1.get().min()),
                () -> assertEquals(2, result2.get().min()),
                () -> assertEquals(5, result1.get().max()),
                () -> assertEquals(8, result2.get().max())
        );
    }

    @Test
    void shouldClearAllStatistics() {
        // given
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        ArrayStatistic statistic1 = new ArrayStatistic(1, 5, 15, 3.0);
        ArrayStatistic statistic2 = new ArrayStatistic(2, 8, 20, 4.0);

        warehouse.putStatistic(id1, statistic1);
        warehouse.putStatistic(id2, statistic2);

        // verify statistics were added
        assertTrue(warehouse.getStatistic(id1).isPresent());
        assertTrue(warehouse.getStatistic(id2).isPresent());

        // when
        warehouse.clear();

        // then
        assertAll("Clear all statistics validation",
                () -> assertFalse(warehouse.getStatistic(id1).isPresent()),
                () -> assertFalse(warehouse.getStatistic(id2).isPresent()),
                () -> assertTrue(warehouse.getStatistic(id1).isEmpty()),
                () -> assertTrue(warehouse.getStatistic(id2).isEmpty())
        );
    }

    @Test
    void shouldHandleRemoveNonExistentStatistic() {
        // given
        UUID id = UUID.randomUUID();

        // when & then - should not throw exception
        assertAll("Remove non-existent statistic validation",
                () -> assertDoesNotThrow(() -> warehouse.removeStatistic(id)),
                () -> assertFalse(warehouse.getStatistic(id).isPresent())
        );
    }
}