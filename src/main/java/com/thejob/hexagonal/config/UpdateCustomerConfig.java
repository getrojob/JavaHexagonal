package com.thejob.hexagonal.config;

import com.thejob.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import com.thejob.hexagonal.adapters.out.UpdateCustomerAdapter;
import com.thejob.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import com.thejob.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class UpdateCustomerConfig {
    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            UpdateCustomerAdapter updateCustomerAdapter
    ) {
        return new UpdateCustomerUseCase(findCustomerByIdUseCase, findAddressByZipCodeAdapter, updateCustomerAdapter);
    }
}
