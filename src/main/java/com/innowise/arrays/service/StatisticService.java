package com.innowise.arrays.service;

import com.innowise.arrays.entity.CustomArray;

import java.util.Optional;

public interface StatisticService {
    Optional<Integer> findMinValue(CustomArray intCustomArray);

    Optional<Integer> findMaxValue(CustomArray intCustomArray);

    Optional<Integer> calculateSumOfElements(CustomArray intCustomArray);

    Optional<Double> calculateAverageOfElements(CustomArray intCustomArray);
}
