package com.innowise.arrays.factory;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.exception.CustomArrayException;

public class CustomArrayFactoryImpl implements CustomArrayFactory {

    @Override
    public CustomArray createCustomArray(int[] data) throws CustomArrayException {
        if (data == null) {
            throw new CustomArrayException("Array data cannot be null");
        }

        return new CustomArray(data);
    }
}
