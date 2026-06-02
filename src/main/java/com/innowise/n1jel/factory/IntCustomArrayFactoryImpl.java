package com.innowise.n1jel.factory;

import com.innowise.n1jel.entity.IntCustomArray;
import com.innowise.n1jel.exception.CustomArrayException;

public class IntCustomArrayFactoryImpl implements IntCustomArrayFactory {

    @Override
    public IntCustomArray createCustomArray(int[] data) {
        if (data == null) {
            throw new CustomArrayException("Array data cannot be null");
        }

        return new IntCustomArray(data);
    }
}
