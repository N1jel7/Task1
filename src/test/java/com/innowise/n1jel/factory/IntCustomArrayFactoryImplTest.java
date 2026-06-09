package com.innowise.n1jel.factory;


import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntCustomArrayFactoryImplTest {

    private CustomArrayFactoryImpl factory;

    @BeforeEach
    void setUp() {
        factory = new CustomArrayFactoryImpl();
    }

    @Test
    void shouldCreateIntCustomArrayWithValidData() throws CustomArrayException {
        // given
        int[] data = {1, 2, 3, 4, 5};

        // when
        CustomArray result = factory.createCustomArray(data);

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

    @Test
    void shouldCreateIntCustomArrayWithEmptyArray() throws CustomArrayException {
        //given
        int[] data = {};

        //when
        CustomArray result = factory.createCustomArray(data);

        //then
        assertEquals(0, result.getLength());
    }

}
