package com.easytravel.easytravel.mdbspringboot.service.impl;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.repository.CustomerRepository;
import com.easytravel.easytravel.mdbspringboot.service.intf.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    @Override
    public void getFavoritePOIById(Customer customer) {

    }
}
