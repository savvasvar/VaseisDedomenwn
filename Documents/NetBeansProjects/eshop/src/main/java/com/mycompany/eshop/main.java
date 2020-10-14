/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

import java.sql.*;

/**
 *
 * @author Thanasis
 */
public class main {

    /**
     * @param args the command line arguments
     */
    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it154474" ;
    static Connection dbConnection = null;
    static String     username = "it154474";
    static String     passwd = "test123";
    static Statement statement=null;
    static ResultSet rs =null;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName (driverClassName);
        dbConnection = DriverManager.getConnection (url, username, passwd);
        statement = dbConnection.createStatement();
        
        String selectString = "select * from roles";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            String role_name=rs.getString("role_name");
            int role_id=rs.getInt("role_id");
            System.out.println(role_id+" "+role_name);
        }
        statement.close();
        
        
        dbConnection.close();
    }
    
}
