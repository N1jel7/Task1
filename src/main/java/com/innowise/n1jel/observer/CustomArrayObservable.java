package com.innowise.n1jel.observer;

public interface CustomArrayObservable {
    void attachObserver(CustomArrayObserver observer);
    void detachObserver(CustomArrayObserver observer);
    void notifyObservers();
}
