package com.innowise.n1jel.parser;

import java.util.Optional;

public interface CustomArrayParser {
    Optional<int[]> parseIntFromString(String line);
}
