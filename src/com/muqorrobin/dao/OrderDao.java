/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.dao;

import com.muqorrobin.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class OrderDao {

    ConnectionDao connectionDao = new ConnectionDao();
    Connection koneksi = connectionDao.connectToDatabase();
    
    public Order createOrder(String description, double cost, int qty){
        Order ord = new Order(description, cost, qty);
        return ord;
    }
    
    public List<Order> findOrders(int customerId) {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement pstm = koneksi.prepareStatement("select po.quantity, p.PURCHASE_COST, p.description from purchase_order po join product p on po.PRODUCT_ID = p.PRODUCT_ID where po.CUSTOMER_ID = ?");
            pstm.setInt(1, customerId);
            ResultSet results = pstm.executeQuery();
            while (results.next()) {
                System.out.println("Ada order");
                Order order = new Order();
                order.setDescription(results.getString("description"));
                order.setCost(results.getDouble("purchase_cost"));
                order.setQuantity(results.getInt("quantity"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}
