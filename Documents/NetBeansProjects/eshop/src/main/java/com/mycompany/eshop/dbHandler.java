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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    PreparedStatement statementIns =null;
    
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
    public String getCustomerName(int id) throws SQLException{
        connect();
        String name="";
        String selectString2 = "select username from customers where customer_id="+id;
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            name=rs.getString("username");
        }
        return name;
    }
    public String getProductName(int id) throws SQLException{
        connect();
        String name="";
        String selectString2 = "select product_name from products where product_id="+id;
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            name=rs.getString("product_name");
        }
        return name;
    }

    public Orders[] getOrders() throws SQLException{
        connect();
        int numOfOrders=0;
        List<Orders> fret=new ArrayList();
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
                        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        String strDate=dateFormat.format(date);
                        dateMOD=strDate;
                        ord.setDate(strDate);
                        int product_name=rs.getInt("product_id");
                        String pname=String.valueOf(product_name);
                        ord.setProductName(pname);
                        orderList.add(ord); 
                }
                int cid=Integer.parseInt(custom);
                order.setCustomer(getCustomerName(cid));
                order.setDate(dateMOD);
                for(int c=0;c<orderList.size();c++){
                    int cID=Integer.parseInt(orderList.get(c).getCustomer());
                    orderList.get(c).setCustomer(getCustomerName(cID));
    //                System.out.println(getCustomerName(cID));
                    int pID=Integer.parseInt(orderList.get(c).getProductName());
                    orderList.get(c).setProductName(getProductName(pID));
    //                System.out.println(getProductName(pID));
                }
                order.setProductList(orderList);
                orders[i-1]=order;
            }
            for(int i=0;i<orders.length;i++){
                boolean flag=false;
                String filter = "select completed from orders where order_id="+orders[i].getOrderID();
                rs=statement.executeQuery(filter);
                while(rs.next()){
                   flag=rs.getBoolean("completed");
                }
                if(!flag){
                    fret.add(orders[i]);
                }
            }
            Orders[] retArray=new Orders[fret.size()];
            for(int i=0;i<fret.size();i++){
                retArray[i]=fret.get(i);
            }
        return retArray;
    }
    public float getProductsPrice(String name) throws SQLException{
        connect();
        float price=0;
        String selectString2 = "select price from products where product_name='"+name+"'";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            price=rs.getFloat("price");
        }
        return price;
    }
    public int getBarcode(String name) throws SQLException{
        connect();
        int barcode=0;
        String selectString2 = "select barcode from products where product_name='"+name+"'";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            barcode=rs.getInt("barcode");
        }
        return barcode;
    }
    public int  OrderSetComplete(int id) throws SQLException{
        connect();
        String SQL = "UPDATE orders SET completed=true where order_id=?";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setInt(1, id);
        int affectedRows = statementIns.executeUpdate();
        return affectedRows;
    }
    public void UpdateProductsAfterOrderComplete(Orders ord) throws SQLException{
        connect();
        String SQL = "update products set amount=(select amount from products where product_name=?)-? where product_name=?";
        statementIns= dbConnection.prepareStatement(SQL);
        for(int i=0;i<ord.getProductList().size();i++){ 
            statementIns.setString(1, ord.getProductList().get(i).getProductName());
            statementIns.setInt(2, ord.getProductList().get(i).getProductAmount());
            statementIns.setString(3, ord.getProductList().get(i).getProductName());
            int affectedRows = statementIns.executeUpdate();
            if(affectedRows>0){ 
                 System.out.println("Stock update done rows affected"+affectedRows+"  In iteration "+i);
            }
           }
    }
    
    public List<String> getRoles() throws SQLException{
        connect();
        List<String> ret=new ArrayList<String>();
        String selectString2 = "select role_name from roles";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ret.add(rs.getString("role_name"));
        }
        return ret;
    }
    public int setRoleToUser(String user,int rid) throws SQLException{
        connect();
        String SQL = "UPDATE users SET role_id=? where username=?";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setInt(1, rid);
        statementIns.setString(2, user);
        int affectedRows = statementIns.executeUpdate();
        return affectedRows;
    }
}
