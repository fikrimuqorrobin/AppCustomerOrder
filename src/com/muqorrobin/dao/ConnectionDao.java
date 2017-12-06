/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConnectionDao {
    
    public Connection connectToDatabase() {
        Connection con = null;
        try {
            String url = "jdbc:derby://localhost:1527/sample";
            String username = "app";
            String password = "app";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

}
