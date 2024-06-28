package com.easytravel.easytravel.mdbspringboot.service.intf;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



public interface CustomerService {
    Customer createCustomer(String username, String password);
    Object verifyCustomer(String username, String password);
    public List<Integer> getCustomerFavoritePOIList(Integer id);
    public List<Integer> getFavoriteRoute(Integer customerId);
    public void modifyFavoriteRoute(Integer customerId, List<Integer> routes);
}
