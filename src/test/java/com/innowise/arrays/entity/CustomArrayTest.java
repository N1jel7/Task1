package com.innowise.arrays.entity;

import com.innowise.arrays.exception.CustomArrayException;
import com.innowise.arrays.observer.CustomArrayObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayTest {
    private CustomArray array;

    @BeforeEach
    void setUp() {
        int[] data = {5, 2, 8, 1, 9};
        array = new CustomArray(data);
    }

    @Test
    void shouldCreateArrayWithValidData() {
        // given
        int[] data = {1, 2, 3};

        // when
        CustomArray result = new CustomArray(data);

        // then
        assertAll("Create array with valid data validation",
                () -> assertNotNull(result.getId()),
                () -> assertEquals(3, result.getLength()),
                () -> assertFalse(result.isEmpty()),
                () -> assertArrayEquals(data, result.getArray())
        );
    }

    @Test
    void shouldCreateEmptyArrayWhenNullProvided() {
        // given
        int[] data = null;

        // when
        CustomArray result = new CustomArray(data);

        // then
        assertAll("Create empty array when null provided validation",
                () -> assertEquals(0, result.getLength()),
                () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    void shouldGetElementByIndex() throws CustomArrayException {
        // when
        int element = array.getElement(0);

        // then
        assertEquals(5, element);
    }

    @Test
    void shouldThrowExceptionWhenIndexOutOfBounds() {
        // when & then
        assertThrows(CustomArrayException.class, () -> {
            array.getElement(100);
        });
    }

    @Test
    void shouldSetElementAndNotifyObservers() throws CustomArrayException {
        // given
        TestObserver observer = new TestObserver();
        array.attachObserver(observer);

        // when
        array.setElement(0, 100);

        // then
        assertAll("Set element and notify observers validation",
                () -> assertEquals(100, array.getElement(0)),
                () -> assertTrue(observer.isNotified())
        );
    }

    @Test
    void shouldAttachObserverAndDetachObserverObservers() {
        // given
        TestObserver observer = new TestObserver();

        // when
        array.attachObserver(observer);
        array.detachObserver(observer);

        // then
        assertFalse(array.getObserver().isPresent());
    }

    @Test
    void shouldReturnCorrectLength() {
        // then
        assertEquals(5, array.getLength());
    }

    @Test
    void shouldReturnTrueWhenArrayIsEmpty() {
        // given
        CustomArray emptyArray = new CustomArray(new int[0]);

        // then
        assertTrue(emptyArray.isEmpty());
    }

    @Test
    void shouldReturnFalseWhenArrayIsNotEmpty() {
        // then
        assertFalse(array.isEmpty());
    }

    @Test
    void shouldReturnCopyOfArrayNotReference() throws CustomArrayException {
        // given
        int[] original = array.getArray();

        // when
        original[0] = 999;

        // then
        assertNotEquals(999, array.getElement(0));
    }

    // Test observer implementation
    private static class TestObserver implements CustomArrayObserver {
        private boolean notified = false;

        @Override
        public void customArrayChanged(CustomArray array) {
            notified = true;
        }

        public boolean isNotified() {
            return notified;
        }
    }
}