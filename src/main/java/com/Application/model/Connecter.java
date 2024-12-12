package com.Application.model;

import java.sql.DriverManager;
import java.sql.SQLException;
public class Connecter {
    // Method to establish a connection to the database
    public java.sql.Connection connx(){
        String url = "jdbc:mysql://localhost:3306/data" ;
        String user = "root";
        String password = "";
        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
