package com.innowise.n1jel.repository.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.observer.CustomArrayObserver;
import com.innowise.n1jel.observer.impl.CustomArrayObserverImpl;
import com.innowise.n1jel.repository.CustomArrayRepository;
import com.innowise.n1jel.specification.CustomArraySpecification;
import com.innowise.n1jel.warehouse.impl.CustomArrayWarehouseImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomArrayRepositoryImpl implements CustomArrayRepository {

    private static final Logger log = LogManager.getLogger(CustomArrayRepositoryImpl.class);
    private static CustomArrayRepositoryImpl instance;
    private final List<CustomArray> arrays;
    private final CustomArrayObserver observer;

    private CustomArrayRepositoryImpl() {
        this.arrays = new ArrayList<>();
        this.observer = new CustomArrayObserverImpl();
    }

    public static CustomArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CustomArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public boolean add(CustomArray intCustomArray) {
        log.debug("Adding array to repository: id={}", intCustomArray.getId());

        // Observer registration
        intCustomArray.attachObserver(observer);

        observer.customArrayChanged(intCustomArray);

        return arrays.add(intCustomArray);
    }

    @Override
    public boolean remove(CustomArray intCustomArray) {
        log.debug("Removing array from repository: id={}", intCustomArray.getId());

        // Unsubscribe observer
        intCustomArray.detachObserver(observer);

        CustomArrayWarehouseImpl.getInstance().removeStatistic(intCustomArray.getId());

        return arrays.remove(intCustomArray);
    }

    @Override
    public int size() {
        log.debug("Returning repository size");
        return arrays.size();
    }

    @Override
    public void clear() {
        arrays.clear();
        log.debug("Complete repository cleanup");
    }

    @Override
    public List<CustomArray> findAll() {
        return new ArrayList<>(arrays);
    }

    @Override
    public List<CustomArray> query(CustomArraySpecification specification) {
        log.debug("Querying repository with specification");
        List<CustomArray> result = new ArrayList<>();

        for (CustomArray array : arrays) {
            if (specification.specify(array)) {
                result.add(array);
            }
        }

        log.debug("Query returned {} results", result.size());
        return result;
    }

    @Override
    public List<CustomArray> queryStream(CustomArraySpecification specification) {
        log.debug("Querying repository with specification (stream)");
        return arrays.stream()
                .filter(specification::specify)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomArray> sort(Comparator<CustomArray> comparator) {
        log.debug("Sorting repository with provided comparator");
        return arrays.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}