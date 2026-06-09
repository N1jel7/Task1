package com.innowise.n1jel.factory;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;

public interface CustomArrayFactory {
    CustomArray createCustomArray(int[] data) throws CustomArrayException;
}
