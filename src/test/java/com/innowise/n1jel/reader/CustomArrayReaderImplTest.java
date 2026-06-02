package com.innowise.n1jel.reader;

import com.innowise.n1jel.exception.CustomArrayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    void shouldReadLinesFromResourceFile() throws Exception {
        // given
        String path = "data/input.txt";

        // when
        List<String> lines = reader.readLines(path);

        // then
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        // when & then
        assertThrows(CustomArrayException.class, () -> {
            reader.readLines("non_existent_file.txt");
        });
    }
}
