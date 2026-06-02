package com.innowise.n1jel.reader;

import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayReaderImplTest {
    private CustomArrayReaderImpl reader;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        reader = new CustomArrayReaderImpl();
    }

    @Test
    void shouldReadAllLinesFromFile() throws Exception {
        // given
        String path = "data/input.txt";

        // when
        List<String> lines = reader.readAllLinesFromFile(path);

        // then
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        // when & then
        assertThrows(CustomArrayException.class, () -> {
            reader.readAllLinesFromFile("non_existent_file.txt");
        });
    }
}
