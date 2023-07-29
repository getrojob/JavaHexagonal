package com.thejob.hexagonal.application.ports.out;

import com.thejob.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {
    Optional<Customer> find(String id);
}
