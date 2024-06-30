package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.model.RegisterBody;
import com.easytravel.easytravel.mdbspringboot.service.impl.BannedNamesService;
import com.easytravel.easytravel.mdbspringboot.service.impl.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins="http://localhost:3000")
public class LogInController {
    private static final Logger logger = Logger.getLogger(LogInController.class.getName());
    private final CustomerServiceImpl customerService;
    private final BannedNamesService bannedNamesService;


    public LogInController(CustomerServiceImpl customerService, BannedNamesService bannedNamesService) {
        this.customerService = customerService;
        this.bannedNamesService = bannedNamesService;
    }
    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> data) {
        String inputUsername = data.get("username");
        String inputPassword = data.get("password");
        Object response = customerService.verifyCustomer(inputUsername, inputPassword);
        if (response == null) {
            throw new RuntimeException("Invalid Credentials");
        } else {
            return response;
        }
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody RegisterBody body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Invalid data: " + errorMessages);
        }  else if (customerService.isUsernameTaken(body.username())){
            throw new RuntimeException("Username exists");
        } else if (bannedNamesService.existInBannedNames(body.username())){
            throw new RuntimeException("Restricted words used");
        }

        return customerService.createCustomer(body.username(), body.password());
    }
}

