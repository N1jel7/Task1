package com.innowise.arrays.repository;

import com.innowise.arrays.entity.CustomArray;
import com.innowise.arrays.specification.CustomArraySpecification;

import java.util.Comparator;
import java.util.List;

public interface CustomArrayRepository {
    boolean add(CustomArray intCustomArray);

    boolean remove(CustomArray intCustomArray);

    int size();

    void clear();

    List<CustomArray> findAll();

    List<CustomArray> query(CustomArraySpecification specification);

    List<CustomArray> queryStream(CustomArraySpecification specification);

    List<CustomArray> sort(Comparator<CustomArray> comparator);


}
