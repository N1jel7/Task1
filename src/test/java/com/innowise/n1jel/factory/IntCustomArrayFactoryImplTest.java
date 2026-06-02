package com.innowise.n1jel.factory;


import com.innowise.n1jel.entity.IntCustomArray;
import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntCustomArrayFactoryImplTest {

    private IntCustomArrayFactoryImpl factory;

    @BeforeEach
    void setUp() {
        factory = new IntCustomArrayFactoryImpl();
    }

    @Test
    void shouldCreateIntCustomArrayWithValidData() {
        // given
        int[] data = {1, 2, 3, 4, 5};

        // when
        IntCustomArray result = factory.createCustomArray(data);

        // then
        assertNotNull(result);
        assertEquals(5, result.getLength());
        assertArrayEquals(data, result.getArray());
    }

    @Test
    void shouldThrowExceptionWhenDataIsNull() {
        // when & then
        assertThrows(CustomArrayException.class, () -> {
            factory.createCustomArray(null);
        });
    }
}
