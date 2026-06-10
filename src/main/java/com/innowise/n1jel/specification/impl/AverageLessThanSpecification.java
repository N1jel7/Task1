package com.innowise.n1jel.specification.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.service.StatisticService;
import com.innowise.n1jel.service.impl.StatisticServiceImpl;
import com.innowise.n1jel.specification.CustomArraySpecification;

public class AverageLessThanSpecification implements CustomArraySpecification {

    private final double threshold;
    private final StatisticService statisticService;

    public AverageLessThanSpecification(double threshold) {
        this.threshold = threshold;
        this.statisticService = new StatisticServiceImpl();
    }

    @Override
    public boolean specify(CustomArray array) {
        if (array == null || array.isEmpty()) {
            return false;
        }
        double average = statisticService.calculateAverageOfElements(array).orElse(0.0);
        return average < threshold;
    }
}
