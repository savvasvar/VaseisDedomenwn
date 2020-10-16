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

    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
          login loginForm=new login();
          loginForm.setVisible(true);

    }
    
}
