package com.innowise.n1jel.observer;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;

public interface CustomArrayObserver {
    void customArrayChanged(CustomArray intCustomArray);
}
