package com.innowise.n1jel.specification.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.service.StatisticService;
import com.innowise.n1jel.service.impl.StatisticServiceImpl;
import com.innowise.n1jel.specification.CustomArraySpecification;

public class SumGreaterThanSpecification implements CustomArraySpecification {

    private final int threshold;
    private final StatisticService statisticService;

    public SumGreaterThanSpecification(int threshold) {
        this.threshold = threshold;
        this.statisticService = new StatisticServiceImpl();
    }

    @Override
    public boolean specify(CustomArray array) {
        if (array == null || array.isEmpty()) {
            return false;
        }
        int sum = statisticService.calculateSumOfElements(array).orElse(0);
        return sum > threshold;
    }
}
