package com.ilp04.service;

import java.util.ArrayList;
import com.ilp04.entity.Customer;

public interface CustomerService {
    ArrayList<Customer> getAllCustomers();
    int insertCustomer(Customer customer);
    int updateCustomer(Customer customer);
}
