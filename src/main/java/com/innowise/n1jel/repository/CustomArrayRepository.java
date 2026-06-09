package com.innowise.n1jel.repository;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.specification.CustomArraySpecification;

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
