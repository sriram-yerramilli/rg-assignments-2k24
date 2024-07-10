package com.jpa.springbatch.config;

import com.jpa.springbatch.entity.Customer;
import lombok.NonNull;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(@NonNull Customer customer)  {
        System.out.println(customer);
        return customer;
    }
}
