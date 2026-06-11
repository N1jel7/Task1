package com.innowise.arrays.factory;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.exception.CustomArrayException;

public interface CustomArrayFactory {
    CustomArray createCustomArray(int[] data) throws CustomArrayException;
}
