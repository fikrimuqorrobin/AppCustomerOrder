/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.dao;

import com.muqorrobin.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CustomerDao {

    private Customer customer;
    private ConnectionDao connectionDao = new ConnectionDao();
    Connection koneksi = connectionDao.connectToDatabase();

    public CustomerDao() {
    }

    public CustomerDao(Customer customer) {
        this.customer = customer;
    }

    public Customer insertCustomer(int customerId, String name, String addressLine1, String addressLine2, String city, String discountCode, double creditLimit, String email, String fax, String phone, String state, String postZip) {
        Customer c = new Customer(customerId, name, addressLine1, addressLine2, city, discountCode, creditLimit, email, fax, phone, state, postZip);
        return c;
    }

    public Customer findCustomer(int customerId) {
        System.out.println("Customer ID " + customerId);
        Customer c = new Customer();
        try {
            PreparedStatement pstm = koneksi.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?");
            pstm.setInt(1, customerId);
            ResultSet results = pstm.executeQuery();
            while (results.next()) {
                c.setName(results.getString("name"));
                c.setAddressLine1(results.getString("addressline1"));
                c.setAddressLine2(results.getString("addressline2"));
                c.setCity(results.getString("city"));
                c.setCustomerId(results.getInt("customer_id"));
                c.setDiscountCode(results.getString("discount_code"));
                c.setCreditLimit(results.getDouble("credit_limit"));
                c.setEmail(results.getString("email"));
                c.setFax(results.getString("fax"));
                c.setPhone(results.getString("phone"));
                c.setState(results.getString("state"));
                c.setPostZip(results.getString("zip"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Statement st = koneksi.createStatement();
        ResultSet results = st.executeQuery("SELECT * FROM CUSTOMER");

        while (results.next()) {
            Customer c = new Customer();
            c.setName(results.getString("name"));
            c.setAddressLine1(results.getString("addressline1"));
            c.setAddressLine2(results.getString("addressline2"));
            c.setCity(results.getString("city"));
            c.setCustomerId(results.getInt("customer_id"));
            c.setDiscountCode(results.getString("discount_code"));
            c.setCreditLimit(results.getDouble("credit_limit"));
            c.setEmail(results.getString("email"));
            c.setFax(results.getString("fax"));
            c.setPhone(results.getString("phone"));
            c.setState(results.getString("state"));
            c.setPostZip(results.getString("zip"));
            customers.add(c);
        }
        return customers;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
