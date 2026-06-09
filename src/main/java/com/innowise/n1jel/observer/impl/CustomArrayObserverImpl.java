package com.innowise.n1jel.observer.impl;

import com.innowise.n1jel.entity.ArrayStatistic;
import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;
import com.innowise.n1jel.observer.CustomArrayObserver;
import com.innowise.n1jel.service.AnalyticService;
import com.innowise.n1jel.service.impl.AnalyticServiceImpl;
import com.innowise.n1jel.warehouse.CustomArrayWarehouse;
import com.innowise.n1jel.warehouse.impl.CustomArrayWarehouseImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomArrayObserverImpl implements CustomArrayObserver {

    private static final Logger log = LogManager.getLogger(CustomArrayObserverImpl.class);

    private final AnalyticService analyticService;
    private final CustomArrayWarehouse warehouse;

    public CustomArrayObserverImpl() {
        this.analyticService = new AnalyticServiceImpl();
        this.warehouse = CustomArrayWarehouseImpl.getInstance();
    }

    @Override
    public void customArrayChanged(CustomArray array) {
        if (array == null) {
            log.debug("Array is null, cannot update warehouse");
            return;
        }

        if (array.isEmpty()) {
            log.debug("Array is empty, updating warehouse with zero values");
            ArrayStatistic emptyCalculation = new ArrayStatistic(0, 0, 0, 0.0);
            warehouse.put(array.getId(), emptyCalculation);
            return;
        }

        try {
            log.debug("Recalculating statistics for array id: {}", array.getId());

            int min = analyticService.findMinValue(array).orElseThrow(() ->
                    new CustomArrayException("Failed to calculate min value for array: " + array.getId()));

            int max = analyticService.findMaxValue(array).orElseThrow(() ->
                    new CustomArrayException("Failed to calculate max value for array: " + array.getId()));

            int sum = analyticService.calculateSumOfElements(array).orElseThrow(() ->
                    new CustomArrayException("Failed to calculate sum for array: " + array.getId()));

            double average = analyticService.calculateAverageOfElements(array).orElseThrow(() ->
                    new CustomArrayException("Failed to calculate average for array: " + array.getId()));

            ArrayStatistic calculation = new ArrayStatistic(min, max, sum, average);
            warehouse.put(array.getId(), calculation);

            log.debug("Statistics updated for array {}: min={}, max={}, sum={}, average={}",
                    array.getId(), min, max, sum, average);

        } catch (CustomArrayException e) {
            log.error("Failed to recalculate statistics for array id: {}", array.getId(), e);
        }
    }
}