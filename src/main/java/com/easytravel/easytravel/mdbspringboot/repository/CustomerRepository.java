package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {
    List<Customer> findCustomerById(Integer customerId);
}
