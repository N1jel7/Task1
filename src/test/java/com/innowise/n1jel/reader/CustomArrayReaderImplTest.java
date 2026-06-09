package com.innowise.n1jel.reader;

import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
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
    void shouldReadLinesFromExistingFile() throws CustomArrayException {
        // given
        String filePath = "data/test.txt";

        // when
        List<String> lines = reader.readAllLinesFromFile(filePath);

        // then
        assertNotNull(lines);
        assertTrue(lines.size() > 0);
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        // given
        String nonExistentFile = "non_existent_file.txt";

        // when & then
        assertThrows(CustomArrayException.class, () -> {
            reader.readAllLinesFromFile(nonExistentFile);
        });
    }
}
