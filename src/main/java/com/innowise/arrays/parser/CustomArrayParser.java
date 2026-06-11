package com.innowise.arrays.parser;

import java.util.Optional;

public interface CustomArrayParser {
    Optional<int[]> parseIntFromString(String line);
}
