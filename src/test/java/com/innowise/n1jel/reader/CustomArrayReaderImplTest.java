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
        Path testFile = tempDir.resolve("input.txt");
        List<String> expectedLines = List.of("1,2,3", "4,5,6", "7,8,9");
        Files.write(testFile, expectedLines);

        // when
        List<String> lines = reader.readAllLinesFromFile(testFile.toString());

        // then
        assertAll("Read all lines from file validation",
                () -> assertNotNull(lines),
                () -> assertFalse(lines.isEmpty()),
                () -> assertEquals(3, lines.size()),
                () -> assertEquals(expectedLines, lines)
        );
    }

    @Test
    void shouldReadLinesFromFileWithSingleLine() throws Exception {
        // given
        Path testFile = tempDir.resolve("single_line.txt");
        List<String> expectedLines = List.of("42");
        Files.write(testFile, expectedLines);

        // when
        List<String> lines = reader.readAllLinesFromFile(testFile.toString());

        // then
        assertAll("Read single line file validation",
                () -> assertNotNull(lines),
                () -> assertEquals(1, lines.size()),
                () -> assertEquals("42", lines.get(0))
        );
    }

    @Test
    void shouldReadLinesFromEmptyFile() throws Exception {
        // given
        Path emptyFile = tempDir.resolve("empty.txt");
        Files.createFile(emptyFile);

        // when
        List<String> lines = reader.readAllLinesFromFile(emptyFile.toString());

        // then
        assertAll("Read empty file validation",
                () -> assertNotNull(lines),
                () -> assertTrue(lines.isEmpty()),
                () -> assertEquals(0, lines.size())
        );
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        // given
        String nonExistentFile = tempDir.resolve("non_existent_file.txt").toString();

        // when & then
        assertThrows(CustomArrayException.class, () -> {
            reader.readAllLinesFromFile(nonExistentFile);
        });
    }

    @Test
    void shouldThrowExceptionWhenPathIsNull() {
        // when & then
        assertThrows(CustomArrayException.class, () -> {
            reader.readAllLinesFromFile(null);
        });
    }

    @Test
    void shouldThrowExceptionWhenPathIsDirectory() throws IOException {
        // given
        Path directory = tempDir.resolve("test_directory");
        Files.createDirectory(directory);

        // when & then
        assertThrows(CustomArrayException.class, () -> {
            reader.readAllLinesFromFile(directory.toString());
        });
    }

    @Test
    void shouldHandleFileWithEmptyLines() throws Exception {
        // given
        Path testFile = tempDir.resolve("with_empty_lines.txt");
        List<String> contentWithEmptyLines = List.of("1,2,3", "", "4,5,6", "", "7,8,9");
        Files.write(testFile, contentWithEmptyLines);

        // when
        List<String> lines = reader.readAllLinesFromFile(testFile.toString());

        // then
        assertAll("File with empty lines validation",
                () -> assertNotNull(lines),
                () -> assertEquals(5, lines.size()),
                () -> assertEquals("", lines.get(1)),
                () -> assertEquals("", lines.get(3))
        );
    }

    @Test
    void shouldReadLinesFromFileWithSpaces() throws Exception {
        // given
        Path testFile = tempDir.resolve("with_spaces.txt");
        List<String> expectedLines = List.of("1, 2, 3", "  4,5,6  ", "7, 8, 9");
        Files.write(testFile, expectedLines);

        // when
        List<String> lines = reader.readAllLinesFromFile(testFile.toString());

        // then
        assertAll("File with spaces validation",
                () -> assertNotNull(lines),
                () -> assertEquals(3, lines.size()),
                () -> assertEquals("1, 2, 3", lines.get(0)),
                () -> assertEquals("  4,5,6  ", lines.get(1)),
                () -> assertEquals("7, 8, 9", lines.get(2))
        );
    }
}