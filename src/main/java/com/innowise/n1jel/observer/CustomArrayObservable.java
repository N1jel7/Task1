package com.innowise.n1jel.observer;

public interface CustomArrayObservable {
    void attach(CustomArrayObserver observer);
    void detach(CustomArrayObserver observer);
    void notifyObservers();
}
