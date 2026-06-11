package com.innowise.arrays.comparator.impl;

import com.innowise.arrays.comparator.CustomArrayComparator;
import com.innowise.arrays.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class CustomArrayComparatorImpl implements CustomArrayComparator {
    private static final Logger log = LogManager.getLogger();

    @Override
    public Comparator<CustomArray> byId() {
        log.debug("Sorting by ID");
        return Comparator.comparing(CustomArray::getId);
    }

    @Override
    public Comparator<CustomArray> byFirstElement() {
        log.debug("Sorting by first element");
        return Comparator.comparingDouble(array ->
                array.getLength() == 0 ? Integer.MIN_VALUE : array.getArray()[0]
        );
    }

    @Override
    public Comparator<CustomArray> byLength() {
        log.debug("Sorting by array length");
        return Comparator.comparingInt(CustomArray::getLength);
    }
}
