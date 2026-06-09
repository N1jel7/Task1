package com.innowise.n1jel.service;

import com.innowise.n1jel.entity.CustomArray;

import java.util.Optional;

public interface AnalyticService {
    Optional<Integer> findMinValue(CustomArray intCustomArray);

    Optional<Integer> findMaxValue(CustomArray intCustomArray);

    Optional<Integer> calculateSumOfElements(CustomArray intCustomArray);

    Optional<Double> calculateAverageOfElements(CustomArray intCustomArray);
}
