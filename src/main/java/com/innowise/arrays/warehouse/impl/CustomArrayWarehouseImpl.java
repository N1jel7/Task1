package com.innowise.arrays.warehouse.impl;

import com.innowise.arrays.entity.ArrayStatistic;
import com.innowise.arrays.warehouse.CustomArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CustomArrayWarehouseImpl implements CustomArrayWarehouse {

    private static final Logger log = LogManager.getLogger(CustomArrayWarehouseImpl.class);
    private static CustomArrayWarehouseImpl instance;
    private final Map<UUID, ArrayStatistic> statistics;

    private CustomArrayWarehouseImpl() {
        this.statistics = new HashMap<>();
        log.debug("Warehouse instance created");
    }

    public static CustomArrayWarehouseImpl getInstance() {
        if (instance == null) {
            instance = new CustomArrayWarehouseImpl();
            log.debug("Warehouse singleton instance initialized");
        }
        return instance;
    }

    @Override
    public void putStatistic(UUID id, ArrayStatistic calculation) {
        statistics.put(id, calculation);
        log.debug("Statistics stored for array id: {} -> min={}, max={}, sum={}, average={}",
                id, calculation.min(), calculation.max(), calculation.sum(), calculation.average());
    }

    @Override
    public Optional<ArrayStatistic> getStatistic(UUID id) {
        ArrayStatistic statistic = statistics.get(id);
        if (statistic != null) {
            log.debug("Statistics retrieved for array id: {}", id);
        } else {
            log.debug("No statistics found for array id: {}", id);
        }
        return Optional.ofNullable(statistic);
    }

    @Override
    public void removeStatistic(UUID uuid) {
        statistics.remove(uuid);
        log.debug("Statistics removed for array id: {}", uuid);
    }

    @Override
    public void clear() {
        statistics.clear();
        log.debug("Completely delete statistics");
    }
}