package com.easytravel.easytravel.mdbspringboot.repository;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {
    List<Customer> findCustomerById(Integer customerId);
    List<Customer> findByUsername(String username);
    Customer getCustomerByUsername(String username);
    List<Integer> getFavoritePoiById(Integer customerId);
}
