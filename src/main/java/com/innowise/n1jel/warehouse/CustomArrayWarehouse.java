package com.innowise.n1jel.warehouse;

import com.innowise.n1jel.entity.ArrayStatistic;

import java.util.Optional;
import java.util.UUID;

public interface CustomArrayWarehouse {
    void put(UUID id, ArrayStatistic calculation);

    Optional<ArrayStatistic> getStatistic(UUID id);

    void removeStatistic(UUID uuid);

    void clear();
}
