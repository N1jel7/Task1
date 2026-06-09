package com.innowise.n1jel.comparator;

import com.innowise.n1jel.entity.CustomArray;

import java.util.Comparator;

public interface CustomArrayComparator {

    Comparator<CustomArray> byId();

    Comparator<CustomArray> byFirstElement();

    Comparator<CustomArray> byLength();
}
