package com.innowise.n1jel.entity;

import com.innowise.n1jel.exception.CustomArrayException;
import com.innowise.n1jel.observer.CustomArrayObserver;
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
        assertNotNull(result.getId());
        assertEquals(3, result.getLength());
        assertFalse(result.isEmpty());
        assertArrayEquals(data, result.getArray());
    }

    @Test
    void shouldCreateEmptyArrayWhenNullProvided() {
        // given
        int[] data = null;

        // when
        CustomArray result = new CustomArray(data);

        // then
        assertEquals(0, result.getLength());
        assertTrue(result.isEmpty());
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
        array.attach(observer);

        // when
        array.setElement(0, 100);

        // then
        assertEquals(100, array.getElement(0));
        assertTrue(observer.isNotified());
    }

    @Test
    void shouldAttachAndDetachObservers() {
        // given
        TestObserver observer = new TestObserver();

        // when
        array.attach(observer);
        array.detach(observer);

        // then
        assertFalse(array.getObservers().contains(observer));
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
