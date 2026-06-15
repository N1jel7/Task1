package com.innowise.arrays.validator;

import java.util.regex.Pattern;

public class CustomArrayLineValidatorImpl implements CustomArrayLineValidator{
    private static final String INVALID_CHARACTERS_REGEX = "[a-zA-Zа-яА-ЯёЁ!@#$%^&*()]+";

    private final Pattern pattern;

    public CustomArrayLineValidatorImpl() {
        this.pattern = Pattern.compile(INVALID_CHARACTERS_REGEX);
    }

    @Override
    public boolean isValidLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        return !pattern.matcher(line).find();
    }
}
