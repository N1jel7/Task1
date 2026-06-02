package com.innowise.n1jel.entity;

import com.innowise.n1jel.exception.CustomArrayException;

import java.util.Arrays;
import java.util.Objects;

public class IntCustomArray {
    private final int[] array;
    private final int length;

    public IntCustomArray(int[] array) {
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

    public void setElement(int index, int value) {
        if (index < 0 || index >= length) {
            throw new CustomArrayException("Index out of bounds: " + index);
        }
        array[index] = value;
    }

    public int getElement(int index) {
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
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        IntCustomArray that = (IntCustomArray) object;
        return length == that.length && Objects.deepEquals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), length);
    }

    @Override
    public String toString() {
        return "IntCustomArray{" +
                "array =" + Arrays.toString(array) +
                ", length=" + length +
                '}';
    }
}
