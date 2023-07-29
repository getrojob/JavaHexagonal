package com.thejob.hexagonal.adapters.in.controller.response;

import com.thejob.hexagonal.application.core.domain.Address;
import lombok.Data;

@Data
public class CustomerResponse {
    private String nome;
    private Address address;
    private String cpf;
    private Boolean isValidCpf;
}
