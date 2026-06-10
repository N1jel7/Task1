package com.innowise.n1jel.specification.impl;

import com.innowise.n1jel.entity.CustomArray;
import com.innowise.n1jel.specification.CustomArraySpecification;

import java.util.UUID;

public class IdSpecification implements CustomArraySpecification {

    private final UUID id;

    public IdSpecification(UUID id) {
        this.id = id;
    }

    @Override
    public boolean specify(CustomArray intCustomArray) {
        if (intCustomArray == null) {
            return false;
        }
        UUID entityId = intCustomArray.getId();
        return id.equals(entityId);
    }
}
