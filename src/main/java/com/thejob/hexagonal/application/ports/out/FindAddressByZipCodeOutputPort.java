package com.thejob.hexagonal.application.ports.out;

import com.thejob.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {
    Address find(String zipCode);
}
