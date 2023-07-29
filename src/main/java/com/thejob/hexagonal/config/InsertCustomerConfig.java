package com.thejob.hexagonal.config;

import com.thejob.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import com.thejob.hexagonal.adapters.out.InsertCustomerAdapter;
import com.thejob.hexagonal.adapters.out.sendCpfForValidationAdapter;
import com.thejob.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class InsertCustomerConfig {
    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            InsertCustomerAdapter insertCustomerAdapter,
            sendCpfForValidationAdapter sendCpfForValidationAdapter

    ) {
        return new InsertCustomerUseCase(findAddressByZipCodeAdapter, insertCustomerAdapter, sendCpfForValidationAdapter);
    }
}
