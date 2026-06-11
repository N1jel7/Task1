package com.innowise.arrays.specification.impl;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.service.StatisticService;
import com.innowise.arrays.service.impl.StatisticServiceImpl;
import com.innowise.arrays.specification.CustomArraySpecification;

public class AverageGreaterThanSpecification implements CustomArraySpecification {

    private final double threshold;
    private final StatisticService statisticService;

    public AverageGreaterThanSpecification(double threshold) {
        this.threshold = threshold;
        this.statisticService = new StatisticServiceImpl();
    }

    @Override
    public boolean specify(CustomArray array) {
        if (array == null || array.isEmpty()) {
            return false;
        }
        double average = statisticService.calculateAverageOfElements(array).orElse(0.0);
        return average > threshold;
    }
}
