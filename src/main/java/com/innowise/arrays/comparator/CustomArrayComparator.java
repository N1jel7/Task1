package com.innowise.arrays.comparator;

import com.innowise.arrays.entity.CustomArray;

import java.util.Comparator;

public interface CustomArrayComparator {

    Comparator<CustomArray> byId();

    Comparator<CustomArray> byFirstElement();

    Comparator<CustomArray> byLength();
}
