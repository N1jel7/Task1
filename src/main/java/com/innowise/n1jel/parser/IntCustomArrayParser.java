package com.innowise.n1jel.parser;

import java.util.Optional;

public interface IntCustomArrayParser {
    Optional<int[]> parseIntFromString(String line);
}
