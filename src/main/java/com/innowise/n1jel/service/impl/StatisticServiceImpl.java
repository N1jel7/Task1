package com.innowise.n1jel.service.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.service.StatisticService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class StatisticServiceImpl implements StatisticService {

    private static final Logger log = LogManager.getLogger(StatisticServiceImpl.class);

    @Override
    public Optional<Integer> findMinValue(CustomArray intCustomArray) {
        if (intCustomArray == null || intCustomArray.isEmpty()) {
            log.debug("Array is null or empty, cannot find min value");
            return Optional.empty();
        }

        int[] array = intCustomArray.getArray();
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        log.debug("Min value found: {}", min);
        return Optional.of(min);
    }

    @Override
    public Optional<Integer> findMaxValue(CustomArray intCustomArray) {
        if (intCustomArray == null || intCustomArray.isEmpty()) {
            log.debug("Array is null or empty, cannot find max value");
            return Optional.empty();
        }

        int[] array = intCustomArray.getArray();
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        log.debug("Max value found: {}", max);
        return Optional.of(max);
    }

    @Override
    public Optional<Integer> calculateSumOfElements(CustomArray intCustomArray) {
        if (intCustomArray == null || intCustomArray.isEmpty()) {
            log.debug("Array is null or empty, cannot calculate sum");
            return Optional.empty();
        }

        int[] array = intCustomArray.getArray();
        int sum = 0;

        for (int value : array) {
            sum += value;
        }

        log.debug("Sum calculated: {}", sum);
        return Optional.of(sum);
    }

    @Override
    public Optional<Double> calculateAverageOfElements(CustomArray intCustomArray) {
        if (intCustomArray == null || intCustomArray.isEmpty()) {
            log.debug("Array is null or empty, cannot calculate average");
            return Optional.empty();
        }

        int[] array = intCustomArray.getArray();
        int sum = 0;

        for (int value : array) {
            sum += value;
        }

        double average = (double) sum / array.length;

        log.debug("Average calculated: {}", average);
        return Optional.of(average);
    }
}
