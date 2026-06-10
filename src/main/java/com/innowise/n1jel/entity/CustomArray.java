package com.innowise.n1jel.entity;

import com.innowise.n1jel.exception.CustomArrayException;
import com.innowise.n1jel.observer.CustomArrayObservable;
import com.innowise.n1jel.observer.CustomArrayObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class CustomArray implements CustomArrayObservable {

    private static final Logger log = LogManager.getLogger(CustomArray.class);

    private final UUID id;
    private int[] array;
    private CustomArrayObserver observer;

    public CustomArray(int[] array) {
        this.id = UUID.randomUUID();
        this.observer = null;
        if (array == null) {
            this.array = new int[0];
        } else {
            this.array = array.clone();
        }
    }

    public UUID getId() {
        return id;
    }

    public int getElement(int index) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            throw new CustomArrayException("Index out of bounds: " + index);
        }
        return array[index];
    }

    public int[] getArray() {
        return array.clone();
    }

    public int getLength() {
        return array.length;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }


    public Optional<CustomArrayObserver> getObserver() {
        if (observer == null) {
            log.debug("No observer attached to array id: {}", id);
        }
        return Optional.ofNullable(observer);
    }

    public void setElement(int index, int value) throws CustomArrayException {
        if (index < 0 || index >= array.length) {
            throw new CustomArrayException("Index out of bounds: " + index);
        }
        array[index] = value;
        notifyObservers();
    }

    public void setArray(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        notifyObservers();
    }

    @Override
    public void attachObserver(CustomArrayObserver observer) {
        this.observer = observer;
        log.debug("Observer attached to array id: {}", id);
    }

    @Override
    public void detachObserver(CustomArrayObserver observer) {
        if (this.observer == observer) {
            this.observer = null;
            log.debug("Observer detached from array id: {}", id);
        }
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            observer.customArrayChanged(this);
        } else {
            log.debug("Cannot notify: no observer attached to array id: {}", id);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        CustomArray array = (CustomArray) object;
        return id.equals(array.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomArray{");
        sb.append("id=").append(id);
        sb.append(", array=").append(Arrays.toString(array));
        sb.append('}');
        return sb.toString();
    }
}