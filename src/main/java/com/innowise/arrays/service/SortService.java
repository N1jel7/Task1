package com.innowise.arrays.service;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.exception.CustomArrayException;

public interface SortService {

    void bubbleSort(CustomArray intCustomArray) throws CustomArrayException;

    void quickSort(CustomArray intCustomArray) throws CustomArrayException;
}
