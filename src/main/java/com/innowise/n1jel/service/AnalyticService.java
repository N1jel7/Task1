package com.innowise.n1jel.service;

import com.innowise.n1jel.entity.IntCustomArray;

import java.util.Optional;

public interface AnalyticService {
    Optional<Integer> findMinValue(IntCustomArray intCustomArray);

    Optional<Integer> findMaxValue(IntCustomArray intCustomArray);

    Optional<Integer> calculateSumOfElements(IntCustomArray intCustomArray);

    Optional<Double> calculateAverageOfElements(IntCustomArray intCustomArray);
}
