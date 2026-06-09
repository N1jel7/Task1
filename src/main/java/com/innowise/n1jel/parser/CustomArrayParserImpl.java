package com.innowise.n1jel.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.regex.Pattern;

public class CustomArrayParserImpl implements CustomArrayParser {
    private static final Logger log = LogManager.getLogger(CustomArrayParserImpl.class);

    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,;\\-\\s]+");

    @Override
    public Optional<int[]> parseIntFromString(String line) {
        if (line == null || line.trim().isEmpty()) {
            log.debug("Line is null or empty");
            return Optional.empty();
        }

        String trimmedLine = line.trim();
        String[] elements = SEPARATOR_PATTERN.split(trimmedLine);

        if (elements.length == 0) {
            log.debug("No elements found in line: {}", trimmedLine);
            return Optional.empty();
        }

        int[] numbers = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            String element = elements[i].trim();

            if (element.isEmpty()) {
                return Optional.empty();
            }

            try {
                numbers[i] = Integer.parseInt(element);
            } catch (NumberFormatException exception) {
                log.debug("Failed to parse element: {} as int", element);
                return Optional.empty();
            }
        }

        log.debug("Successfully parsed line: {} into {} integers", trimmedLine, numbers.length);
        return Optional.of(numbers);
    }
}
