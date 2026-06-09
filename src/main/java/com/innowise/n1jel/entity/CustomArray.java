package com.innowise.n1jel.entity;

import com.innowise.n1jel.exception.CustomArrayException;
import com.innowise.n1jel.observer.CustomArrayObservable;
import com.innowise.n1jel.observer.CustomArrayObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CustomArray implements CustomArrayObservable {

    private final UUID id;
    private final int[] array;
    private final int length;

    private final List<CustomArrayObserver> observers;

    public CustomArray(int[] array) {
        this.id = UUID.randomUUID();
        this.observers = new ArrayList<>();
        if (array == null) {
            this.array = new int[0];
            this.length = 0;
        } else {
            this.array = array.clone();
            this.length = array.length;
        }
    }

    public int[] getArray() {
        return array.clone();
    }

    public UUID getId() {
        return id;
    }

    public List<CustomArrayObserver> getObservers() {
        return observers;
    }

    public void setElement(int index, int value) throws CustomArrayException {
        if (index < 0 || index >= length) {
            throw new CustomArrayException("Index out of bounds: " + index);
        }
        array[index] = value;
        notifyObservers();
    }

    public int getElement(int index) throws CustomArrayException {
        if (index < 0 || index >= length) {
            throw new CustomArrayException("Index out of bounds: " + index);
        }
        return array[index];
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public void attach(CustomArrayObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(CustomArrayObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (CustomArrayObserver observer : observers) {
            observer.customArrayChanged(this);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CustomArray that = (CustomArray) object;

        if (length != that.length) return false;

        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(array);
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntCustomArray{");
        sb.append("array=").append(Arrays.toString(array));
        sb.append(", length=").append(length);
        sb.append('}');
        return sb.toString();
    }
}
