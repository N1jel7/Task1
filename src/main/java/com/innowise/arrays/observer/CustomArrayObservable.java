package com.innowise.arrays.observer;

public interface CustomArrayObservable {
    void attachObserver(CustomArrayObserver observer);
    void detachObserver(CustomArrayObserver observer);
    void notifyObservers();
}
