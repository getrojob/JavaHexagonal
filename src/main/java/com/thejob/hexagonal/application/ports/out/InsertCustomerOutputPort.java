package com.thejob.hexagonal.application.ports.out;

import com.thejob.hexagonal.application.core.domain.Customer;

public interface InsertCustomerOutputPort {
    void insert(Customer customer);
}

