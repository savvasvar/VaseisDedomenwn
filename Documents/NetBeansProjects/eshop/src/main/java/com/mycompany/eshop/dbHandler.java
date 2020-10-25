/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

import static com.mycompany.eshop.login.dbConnection;
import static com.mycompany.eshop.login.driverClassName;
import static com.mycompany.eshop.login.passwd;
import static com.mycompany.eshop.login.rs;
import static com.mycompany.eshop.login.statement;
import static com.mycompany.eshop.login.url;
import static com.mycompany.eshop.login.username;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanasis
 */
public class dbHandler {
    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it154474" ;
    static Connection dbConnection = null;
    static String     username = "it154474";
    static String     passwd = "test123";
    static Statement statement=null;
    static ResultSet rs =null;
    
    //Creates database Connection
    public void connect() throws SQLException{
        try {
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement = dbConnection.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Closes db connection
    public void disconnect() throws SQLException{
         statement.close();
         dbConnection.close();
    }
    /* 
        -Checkes database for how many product id exist
        -Creates an array of Product objects and return it 
    */
    public Product[] getProducts() throws SQLException{
        connect();
        int numOfProducts=0;
        String selectString = "select count(*) from products";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            numOfProducts=rs.getInt("count");
        }
        Product[] data=new Product[numOfProducts];
        selectString="select * from products";
        rs=statement.executeQuery(selectString);
        int i=0;
        while(rs.next()){
            int id=rs.getInt("product_id");
            String name=rs.getString("product_name");
            String description=rs.getString("description");
            int amount=rs.getInt("amount");
            float price=rs.getFloat("price");
            int barcode=rs.getInt("barcode");
            
            Product pro=new Product();
            pro.setName(name);
            pro.setAmount(amount);
            pro.setBarcode(barcode);
            pro.setID(id);
            pro.setPrice(price);
            pro.setDescription(description);
            
            data[i]=pro;
            
            i++;  
        }
        disconnect();
        return data;
    }
    public Product[] getFilteredProducts(int filterNum) throws SQLException{
        connect();
        int numofelement=0;
        int numOfProducts=0;
        var proList = new ArrayList<Product>();
        switch(filterNum){
            case 1:
                numOfProducts=0;
                String selectString = "select count(*) from products where amount>0";
                rs=statement.executeQuery(selectString);
                while(rs.next()){
                    numOfProducts=rs.getInt("count");
                }
                numofelement=numOfProducts;
                selectString="select * from products where amount>0";
                rs=statement.executeQuery(selectString);
                int i=0;
                while(rs.next()){
                    int id=rs.getInt("product_id");
                    String name=rs.getString("product_name");
                    String description=rs.getString("description");
                    int amount=rs.getInt("amount");
                    float price=rs.getFloat("price");
                    int barcode=rs.getInt("barcode");

                    Product pro=new Product();
                    pro.setName(name);
                    pro.setAmount(amount);
                    pro.setBarcode(barcode);
                    pro.setID(id);
                    pro.setPrice(price);
                    pro.setDescription(description);

                    proList.add(pro);

                    i++;  
                }
                disconnect();
                break;                
            case 2:
                numOfProducts=0;
                String selectString2 = "select count(*) from products where amount>0";
                rs=statement.executeQuery(selectString2);
                while(rs.next()){
                    numOfProducts=rs.getInt("count");
                }
                numofelement=numOfProducts;
                selectString="select * from products where amount>0";
                rs=statement.executeQuery(selectString);
                int j=0;
                while(rs.next()){
                    int id=rs.getInt("product_id");
                    String name=rs.getString("product_name");
                    String description=rs.getString("description");
                    int amount=rs.getInt("amount");
                    float price=rs.getFloat("price");
                    int barcode=rs.getInt("barcode");

                    Product pro=new Product();
                    pro.setName(name);
                    pro.setAmount(amount);
                    pro.setBarcode(barcode);
                    pro.setID(id);
                    pro.setPrice(price);
                    pro.setDescription(description);

                    proList.add(pro);

                    j++;  
                }
                disconnect();
                break;                
                
        }
        Product[] ret=new Product[numofelement];
        for(int i=0;i<proList.size();i++){
            ret[i]=proList.get(i);
        }
        return ret;
    }
    public Orders[] getOrders() throws SQLException{
        connect();
        int numOfOrders=0;
        String selectString = "select max(order_id) from orders";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            numOfOrders=rs.getInt("max");
        }
        Orders[] orders=new Orders[numOfOrders];
        for(int i=numOfOrders;i>0;i--){
            String dateMOD=null,custom=null;
            var orderList=new ArrayList<Order>();
            Orders order=new Orders();
            order.setOrderID(i);
            String getOrders = "select * from orders where order_id="+i;
            rs=statement.executeQuery(getOrders);
            while(rs.next()){
                    Order ord=new Order();
                    int order_id=rs.getInt("order_id");
                    ord.setOrderID(order_id);
                    int product_amount=rs.getInt("amount");
                    ord.setProductAmount(product_amount);
                    int customer=rs.getInt("customer_id");
                    String cs=String.valueOf(customer);
                    custom=cs;
                    ord.setCustomer(cs);
                    Date date=rs.getDate("timestamp");
                    DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
                    String strDate=dateFormat.format(date);
                    dateMOD=strDate;
                    ord.setDate(strDate);
                    int product_name=rs.getInt("product_id");
                    String pname=String.valueOf(product_name);
                    ord.setProductName(pname);
                    orderList.add(ord);
            }
            order.setCustomer(custom);
            order.setDate(dateMOD);
            order.setProductList(orderList);
            orders[i-1]=order;
        }
        return orders;
    }
}
