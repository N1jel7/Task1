package com.innowise.n1jel.service;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;

public interface SortService {

    void bubbleSort(CustomArray intCustomArray) throws CustomArrayException;

    void quickSort(CustomArray intCustomArray) throws CustomArrayException;
}
