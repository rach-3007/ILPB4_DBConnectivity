package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;
import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerServiceImpl();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Insert new customer");
            System.out.println("2. Update customer");
            System.out.println("3. View all customers");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    insertCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    viewAllCustomers();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        } while (choice != 4);

    }

    private static void viewAllCustomers() {
        ArrayList<Customer> customerList = customerService.getAllCustomers();
        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customerList) {
                printCustomerDetails(customer);
            }
        }
    }

    private static void insertCustomer() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        long phoneNumber = scanner.nextLong();
        System.out.print("Enter Aadhar Number: ");
        long adharNumber = scanner.nextLong();
        
        Customer customer = new Customer();
        customer.setCustomerFirstname(firstName);
        customer.setCustomerLastname(lastName);
        customer.setAddress(address);
        customer.setPhNumber(phoneNumber);
        customer.setAdharNo(adharNumber);
        
        int rowsAffected = customerService.insertCustomer(customer);
        if (rowsAffected > 0) {
            System.out.println("Customer inserted successfully.");
        } else {
            System.out.println("Failed to insert customer.");
        }
    }

    private static void updateCustomer() {
        ArrayList<Customer> customerList = customerService.getAllCustomers();
        
        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        
        System.out.println("List of Customers:");
        for (Customer customer : customerList) {
            System.out.println("Customer Code: " + customer.getCustomerCode());
            System.out.println("First Name: " + customer.getCustomerFirstname());
            System.out.println("Last Name: " + customer.getCustomerLastname());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Phone Number: " + customer.getPhNumber());
            System.out.println("Aadhar No: " + customer.getAdharNo());
            System.out.println("------------------------------");
        }
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Customer Code to update: ");
        int customerCode = scanner.nextInt();
        scanner.nextLine(); 
        
        Customer selectedCustomer = null;
        for (Customer customer : customerList) {
            if (customer.getCustomerCode() == customerCode) {
                selectedCustomer = customer;
                break;
            }
        }
        
        if (selectedCustomer == null) {
            System.out.println("Customer with code " + customerCode + " not found.");
            
        }
        
        System.out.println("Selected Customer Details:");
        printCustomerDetails(selectedCustomer);
        
        System.out.println("Select the field to update:");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. Phone Number");
        System.out.println("5. Aadhar Number");
        System.out.print("Enter your choice: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine();
        
        switch (fieldChoice) {
            case 1:
                System.out.print("Enter New First Name: ");
                String newFirstName = scanner.nextLine();
                selectedCustomer.setCustomerFirstname(newFirstName);
                break;
            case 2:
                System.out.print("Enter New Last Name: ");
                String newLastName = scanner.nextLine();
                selectedCustomer.setCustomerLastname(newLastName);
                break;
            case 3:
                System.out.print("Enter New Address: ");
                String newAddress = scanner.nextLine();
                selectedCustomer.setAddress(newAddress);
                break;
            case 4:
                System.out.print("Enter New Phone Number: ");
                long newPhoneNumber = scanner.nextLong();
                selectedCustomer.setPhNumber(newPhoneNumber);
                break;
            case 5:
                System.out.print("Enter New Aadhar Number: ");
                long newAdharNumber = scanner.nextLong();
                selectedCustomer.setAdharNo(newAdharNumber);
                break;
            default:
                System.out.println("Invalid choice.");
                
        }
        
        int rowsAffected = customerService.updateCustomer(selectedCustomer);
        if (rowsAffected > 0) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
        
    }


    private static void printCustomerDetails(Customer customer) {
        System.out.println("Customer Code: " + customer.getCustomerCode());
        System.out.println("First Name: " + customer.getCustomerFirstname());
        System.out.println("Last Name: " + customer.getCustomerLastname());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Phone Number: " + customer.getPhNumber());
        System.out.println("Aadhar No: " + customer.getAdharNo());
        System.out.println("------------------------------");
    }
}
