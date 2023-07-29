package com.thejob.hexagonal.config;

import com.thejob.hexagonal.adapters.out.DeleteCustomerByIdAdapter;
import com.thejob.hexagonal.adapters.out.UpdateCustomerAdapter;
import com.thejob.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import com.thejob.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class DeleteCustomerByIdConfig {

    @Bean
    DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter
    ) {
        return new DeleteCustomerByIdUseCase(findCustomerByIdUseCase, deleteCustomerByIdAdapter);
    }
}
