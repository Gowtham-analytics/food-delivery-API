package com.fooddelivery.fooddeliveryapi.repositories;

import com.fooddelivery.fooddeliveryapi.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
