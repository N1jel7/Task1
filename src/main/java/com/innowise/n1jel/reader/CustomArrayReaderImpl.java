package com.innowise.n1jel.reader;

import com.innowise.n1jel.exception.CustomArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CustomArrayReaderImpl implements CustomArrayReader {

    private static final Logger log = LogManager.getLogger(CustomArrayReaderImpl.class);

    @Override
    public List<String> readAllLinesFromFile(String path) {
        try {
            URL resource = CustomArrayReaderImpl.class.getClassLoader().getResource(path);

            if (resource == null) {
                log.error("File not found in resources: {}", path);
                throw new CustomArrayException("File not found: " + path);
            }

            List<String> lines = Files.readAllLines(Paths.get(resource.toURI()));
            log.info("File read successfully: {}", path);
            return lines;

        } catch (IOException | URISyntaxException exception) {
            log.error("Failed to read file: {}", path, exception);
            throw new CustomArrayException("Failed to read file: " + path);
        }
    }
}
