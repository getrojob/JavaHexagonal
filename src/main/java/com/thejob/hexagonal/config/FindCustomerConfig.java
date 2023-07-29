package com.thejob.hexagonal.config;

import com.thejob.hexagonal.adapters.out.FindCustomerByIdAdapter;
import com.thejob.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class FindCustomerConfig {

    @Bean
    public FindCustomerByIdUseCase findCustomerByIdUseCase(
            FindCustomerByIdAdapter findCustomerByIdAdapter
    ) {
        return new FindCustomerByIdUseCase(findCustomerByIdAdapter);
    }
}
