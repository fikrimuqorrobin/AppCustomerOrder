/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqorrobin.app;

import com.muqorrobin.dao.ConnectionDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Tess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection koneksi = null;
            String url = "jdbc:derby://localhost:1527/sample";
            String username = "app";
            String password = "app";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            koneksi = DriverManager.getConnection(url, username, password);
            Statement st = koneksi.createStatement();
            ResultSet results = st.executeQuery("SELECT * FROM CUSTOMER");

            while (results.next()) {
                System.out.println("Nama" + results.getString("Name"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
