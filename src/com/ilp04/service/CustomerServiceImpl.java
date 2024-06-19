package com.ilp04.service;

import java.util.ArrayList;
import com.ilp04.dao.CustomerDAO;
import com.ilp04.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDAO();
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            customerList = customerDAO.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public int insertCustomer(Customer customer) {
        int rowsAffected = 0;
        try {
            rowsAffected = customerDAO.insertCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int updateCustomer(Customer customer) {
        int rowsAffected = 0;
        try {
            rowsAffected = customerDAO.updateCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
    
}
