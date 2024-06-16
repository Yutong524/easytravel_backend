package com.easytravel.easytravel.mdbspringboot.controller;

import com.easytravel.easytravel.mdbspringboot.model.Customer;
import com.easytravel.easytravel.mdbspringboot.model.RegisterBody;
import com.easytravel.easytravel.mdbspringboot.service.impl.CustomerServiceImpl;
import com.easytravel.easytravel.mdbspringboot.service.intf.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class LogInController {
    private static final Logger logger = Logger.getLogger(LogInController.class.getName());
    private final CustomerServiceImpl customerService;

    private static final String BANNED_WORDS_FILE = "src/main/resources/BANNED_WORDS.json";
    private Set<String> bannedWords;

    @PostConstruct
    public void init() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(BANNED_WORDS_FILE));
            ObjectMapper objectMapper = new ObjectMapper();
            String[] bannedWordsArray = objectMapper.readValue(jsonData, String[].class);
            bannedWords = new HashSet<>(Arrays.asList(bannedWordsArray));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load banned words", e);
        }
    }

    public LogInController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")

    public List<Customer> getAllCustomers() {

        logger.info("getAllCustomers method called");
        return customerService.getAllCustomers();
    }


    @PostMapping("/customers")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody RegisterBody body, BindingResult bindingResult) {
        logger.info(" createCustomer method called");
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new RuntimeException("Invalid data: " + errorMessages);
        }  else if (customerService.isUsernameTaken(body.username())){
            throw new RuntimeException("Username exists");
        }

        return customerService.createCustomer(body.username(), body.password());
    }

//    private void validateUsername(String username) {
//        if (username.length() < 4) {
//            throw new RuntimeException("Username must be at least four characters long.");
//        }
//        if (bannedWords.contains(username.toLowerCase())) {
//            throw new RuntimeException("Username contains banned words.");
//        }
////        if (customerService.isUsernameTaken(username)) {
////            throw new RuntimeException("Username is already taken.");
////        }
//    }
//
//    private void validatePassword(String password) {
//        if (password.length() < 8) {
//            throw new RuntimeException("Password must be at least eight characters long.");
//        }
//        if (!password.matches(".*[A-Z].*")) {
//            throw new RuntimeException("Password must contain at least one capital letter.");
//        }
//        if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")) {
//            throw new RuntimeException("Password must be a combination of letters and numbers.");
//        }
//    }
}

