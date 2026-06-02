package com.innowise.n1jel.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.regex.Pattern;

public class IntCustomArrayParserImpl implements IntCustomArrayParser {
    private static final Logger log = LogManager.getLogger(IntCustomArrayParserImpl.class);

    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,;\\-\\s]+");

    @Override
    public Optional<int[]> parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            log.debug("Line is null or empty");
            return Optional.empty();
        }

        String trimmedLine = line.trim();
        String[] tokens = SEPARATOR_PATTERN.split(trimmedLine);

        if (tokens.length == 0) {
            log.debug("No tokens found in line: {}", trimmedLine);
            return Optional.empty();
        }

        int[] numbers = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();

            if (token.isEmpty()) {
                return Optional.empty();
            }

            try {
                numbers[i] = Integer.parseInt(token);
            } catch (NumberFormatException exception) {
                log.debug("Failed to parse token: {} as int", token);
                return Optional.empty();
            }
        }

        log.debug("Successfully parsed line: {} into {} integers", trimmedLine, numbers.length);
        return Optional.of(numbers);
    }
}
