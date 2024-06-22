package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.repository.CustomerRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.CustomerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(String username, String password) {
        long count = customerRepository.count();
        Integer newId = (int) count + 1;
        Customer customer = new Customer();
        customer.setId(newId);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setFavorite_poi(new ArrayList<Integer>());
        customer.setFavorite_route(new ArrayList<Integer>());
        return customerRepository.save(customer);
    }

    @Override
    public void getFavoritePOIById(Customer customer) {
    }

    public List<Integer> getCustomerFavoritePOIList(Integer id){
     List<Customer> customer =  customerRepository.findCustomerById(id);
     return customer.get(0).getFavorite_poi();
    }


    public boolean isUsernameTaken(String username) {
        return customerRepository.findByUsername(username).size() > 0;
    }
}

