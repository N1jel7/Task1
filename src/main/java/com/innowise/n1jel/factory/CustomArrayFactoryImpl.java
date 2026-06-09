package com.innowise.n1jel.factory;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;

public class CustomArrayFactoryImpl implements CustomArrayFactory {

    @Override
    public CustomArray createCustomArray(int[] data) throws CustomArrayException {
        if (data == null) {
            throw new CustomArrayException("Array data cannot be null");
        }

        return new CustomArray(data);
    }
}
