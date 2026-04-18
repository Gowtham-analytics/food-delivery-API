package com.fooddelivery.fooddeliveryapi.mappers;

import com.fooddelivery.fooddeliveryapi.domain.dto.create.CustomerCreateDto;
import com.fooddelivery.fooddeliveryapi.domain.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final OrderMapper orderMapper;

    public CustomerMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public CustomerCreateDto toCreateDto(Customer customer) {
        return new CustomerCreateDto(
                customer.getName(),
                customer.getAge(),
                customer.getAddress()
        );
    }

    public Customer fromCreateDto(CustomerCreateDto customerCreateDto) {
        return new Customer(
                null,
                customerCreateDto.name(),
                customerCreateDto.age(),
                customerCreateDto.address(),
                null
        );
    }
}
