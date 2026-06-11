package com.innowise.arrays.specification.impl;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.service.StatisticService;
import com.innowise.arrays.service.impl.StatisticServiceImpl;
import com.innowise.arrays.specification.CustomArraySpecification;

public class SumLessThanSpecification implements CustomArraySpecification {

    private final int threshold;
    private final StatisticService statisticService;

    public SumLessThanSpecification(int threshold) {
        this.threshold = threshold;
        this.statisticService = new StatisticServiceImpl();
    }

    @Override
    public boolean specify(CustomArray array) {
        if (array == null || array.isEmpty()) {
            return false;
        }
        int sum = statisticService.calculateSumOfElements(array).orElse(0);
        return sum < threshold;
    }
}
