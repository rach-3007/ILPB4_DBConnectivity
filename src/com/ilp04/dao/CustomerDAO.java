package com.ilp04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ilp04.entity.Customer;

public class CustomerDAO {

    public Connection getConnection() throws SQLException {
    	String connectionURL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";
		String userName = "root";
		String password = "experion@123";
		Connection connection = null;    try {

			connection = DriverManager.getConnection(connectionURL, userName, password);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return connection;
	}

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM customer");
            rs = statement.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerCode(rs.getInt("customer_code"));
                customer.setCustomerFirstname(rs.getString("customer_firstname"));
                customer.setCustomerLastname(rs.getString("customer_lastname"));
                customer.setAddress(rs.getString("address"));
                customer.setPhNumber(rs.getLong("phone_number"));
                customer.setAdharNo(rs.getLong("adhar_number"));
                
                customerList.add(customer);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
        return customerList;
    }

    public int insertCustomer(Customer customer) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO customer (customer_firstname, customer_lastname, address, phone_number, adhar_number) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, customer.getCustomerFirstname());
            statement.setString(2, customer.getCustomerLastname());
            statement.setString(3, customer.getAddress());
            statement.setLong(4, customer.getPhNumber());
            statement.setLong(5, customer.getAdharNo());
            
            rowsAffected = statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
        return rowsAffected;
    }

    public int updateCustomer(Customer customer) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = getConnection();
            statement = connection.prepareStatement("UPDATE customer SET customer_firstname = ?, customer_lastname = ?, address = ?, phone_number = ?, adhar_number = ? WHERE customer_code = ?");
            statement.setString(1, customer.getCustomerFirstname());
            statement.setString(2, customer.getCustomerLastname());
            statement.setString(3, customer.getAddress());
            statement.setLong(4, customer.getPhNumber());
            statement.setLong(5, customer.getAdharNo());
            statement.setInt(6, customer.getCustomerCode());
            
            rowsAffected = statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
        return rowsAffected;
    }
}
