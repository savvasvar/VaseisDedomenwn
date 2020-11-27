/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

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
//         dbConnection.close();
    }
    
    //Login function gets usernmae and password String and excecutes postgress database function login()
    //returns true if login credentials are right
    public boolean login(String username,String password) throws SQLException{
        connect();
        boolean ret=false;
        String selectString = "select login('"+username+"','"+password+"')";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            ret=rs.getBoolean("login");
        }
        return ret;
    }
    
    //Gets username string and returns user object if user was found
    public user getUser(String username) throws SQLException{
        connect();
        user usr=new user();
        String selectUser="select * from getuser('"+username+"')";
        rs=statement.executeQuery(selectUser);
        while(rs.next()){
          String usrn=rs.getString("usrname");
          usr.setUsername(usrn);
          String email=rs.getString("mail");
          usr.setEmail(email);
          int user_id=rs.getInt("userid");
          usr.setUserId(user_id);
          int role_id=rs.getInt("roleid");
          usr.setRoleId(role_id);
        }
        return usr;
    }
    
    
    //Get product_id integer and returns product object if found
    public Product getProduct(int id) throws SQLException{
        connect();
        Product pr=new Product();
        String selectString = "select * from getProduct("+id+")";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
           pr.setName(rs.getString("pname"));
           pr.setBarcode(rs.getInt("code"));
           pr.setAmount(rs.getInt("am"));
           pr.setPrice(rs.getFloat("pri"));
           pr.setDescription(rs.getString("descript"));
        }
        pr.setID(id);
        return pr;
    }
    /* 
        -Checkes database for how many product id exist
        -Creates an array of Product objects and return it 
    */
    public List<String> getUsers()throws SQLException{
        connect();
        var usenameList=new ArrayList<String>();
        String selectString = "select * from getUsers()";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            String username=rs.getString("uname");
            usenameList.add(username);
        }
        return usenameList;
    }
    
    //Get username string and returns the role_id of user so it is easier to match in role management combobox
    public int getRolePos(String username) throws SQLException{
        connect();
        int ret=0;
        String selectString = "select getRolePos('"+username+"')";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            ret=rs.getInt("getrolepos");
        }
        return ret-1;
    }
    
    
    //Returns an array of all products
    public Product[] getProducts() throws SQLException{
        connect();
        int numOfProducts=0;
        String selectString = "select countProducts()";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            numOfProducts=rs.getInt("countproducts");
        }
        Product[] data=new Product[numOfProducts];
        selectString="select * from getProducts()";
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
        
        return data;
    }
    
    public List<Product> getProductsByOrderID(int order_id) throws SQLException{
        connect();
        
        List<Product> ret=new ArrayList<Product>();
        String selectString="select * from getProductsByOrder(" + order_id + ")";
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
            
            ret.add(pro);
            System.out.println("Product testing " +pro.getName());
            
            i++;  
        }
        
        return ret;
    }
    
    //Gets an integer which coresponds to filter type
    //Returns a filtered array of products
    public Product[] getFilteredProducts(int filterNum) throws SQLException{
        connect();
        int numofelement=0;
        int numOfProducts=0;
        var proList = new ArrayList<Product>();
        switch(filterNum){
            case 1:
                numOfProducts=0;
                String selectString = "select * from countProductsOnStock()";
                rs=statement.executeQuery(selectString);
                while(rs.next()){
                    numOfProducts=rs.getInt("countproductsonstock");
                }
                numofelement=numOfProducts;
                selectString="select * from getProductsOnStock()";
                rs=statement.executeQuery(selectString);
                int i=0;
                while(rs.next()){
                    int id=rs.getInt("product_id");
                    String name=rs.getString("product_name");
                    String description=rs.getString("description");
                    int amount=rs.getInt("mnt");
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
                
                break;                
            case 2:
                //placeHolder for 2nd filter
                System.out.println("Sup");
                
                break;                
                
        }
        Product[] ret=new Product[numofelement];
        for(int i=0;i<proList.size();i++){
            ret[i]=proList.get(i);
        }
        return ret;
    }
    
    
    //Gets customer_id as an integer and returns Customer name string
    public String getCustomerName(int id) throws SQLException{
        connect();
        String name="";
        String selectString2 = "select * from getCustomerName("+id+")";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            name=rs.getString("getCustomername");
        }
        return name;
    }
    
    //Gets product_id as an integer and returns product name string
    public String getProductName(int id) throws SQLException{
        connect();
        String name="";
        String selectString2 = "select * from getProductName("+id+")";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            name=rs.getString("getproductname");
        }
        
        return name;
    }

    
    //Returns an array of orders where orders with the same order_id are unified in one order
    public List<Order_View> getOrders() throws SQLException{
        connect();
        List<Order_View> kappa=new ArrayList<Order_View>();
        String getOrders = "select * from getNotCompletedOrders();";
        rs=statement.executeQuery(getOrders);
         while(rs.next()){
             Order_View ord=new Order_View(rs.getInt("or_id"),rs.getString("u_name"),rs.getString("dt"));
             kappa.add(ord);
         }  
        return kappa;
    }
    
    
    //Gets products name and return a float that corespons to products price
    public float getProductsPrice(String name) throws SQLException{
        connect();
        float price=0;
        String selectString2 = "select getPrice('"+name+"')";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            price=rs.getFloat("getprice");
        }
        
        return price;
    }
    
    //Gets a string of products name and returns the int price of barcode
    public int getBarcode(String name) throws SQLException{
        connect();
        int barcode=0;
        String selectString2 = "select getBarcode('"+name+"')";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            barcode=rs.getInt("getbarcode");
        }
        
        return barcode;
    }
    
    //Gets an integer and set the order_id with the same number to true that set the order as completed
    public int  OrderSetComplete(int id) throws SQLException{
        connect();
        int affectedRows=0;
        String SQL = "select * from completeorder("+id+")";
        rs=statement.executeQuery(SQL);
        while(rs.next()){
            if(rs.getBoolean("completeorder")){
                affectedRows=1;
            }
        }
        
        return affectedRows;
    }
    
    //Gets an order object and updates the ammount of products when the order completes (subtraction)
    public void UpdateProductsAfterOrderComplete(List<Product> ord) throws SQLException{
        connect();
        int affectedRows=0;
        for(int i=0;i<ord.size();i++){ 
            String SQL = "select * from completeOrderUpdateStock("+ord.get(i).getID()+","+ord.get(i).getAmount()+")";
            rs=statement.executeQuery(SQL);
            while(rs.next()){
//                if(rs.getBoolean("completeorderupdatestock")){
//                    affectedRows=1;
//                }
            }
            if(affectedRows>0){ 
                 System.out.println("Stock update done rows affected"+affectedRows+"  In iteration "+i);
            }
           }
    }
    
    
    //Returns a String List of the suppliers
    public List<String> getSuppliers() throws SQLException{
        connect();
        List<String> ret=new ArrayList<String>();
        String selectString2 = "select * from getSuppliers()";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ret.add(rs.getString("suppliers_name"));
        }
        return ret;
    }
    
    //Gets a string of supplier name and inserts it to the database
    public void insertSupplier(String name) throws SQLException{
        connect();
        String SQL = "select * from insertSupplier('"+name+"')";
        rs=statement.executeQuery(SQL);
    }
    
    
    //Gets product current amount,new amount,product id and supplier id and restocks the product (sets new product amount)
    public void reStock(int amount,int newAm,int pid,int sID) throws SQLException{
        connect();
        String SQL = "select * from reStock("+amount+","+newAm+","+pid+","+sID+")";
        rs=statement.executeQuery(SQL);
    }
    
    //Returns  a String List of user Roles
    public List<String> getRoles() throws SQLException{
        connect();
        List<String> ret=new ArrayList<String>();
        String selectString2 = "select * from getRoles()";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ret.add(rs.getString("rname"));
        }

        return ret;
    }
    
    //Gets strin name and inserts it to database
    public void addRole(String rname) throws SQLException{
        connect();
        String SQL = "select * from addRole('"+rname+"')";
        rs=statement.executeQuery(SQL);     
    }
    
    //Gets int integer of current posistion selected and copies the above's item name to current position and the name of curent item is set to the aboves value
    public void ReOrder(int choice,int inp,String nf,String ns) throws SQLException{
        connect();
        String SQL = "select * from reOrder("+choice+","+inp+",'"+nf+"','"+ns+"')";
        rs=statement.executeQuery(SQL);    
        
    }
    
  
    
    //Returns the array of roles 
    public String[] refresh() throws SQLException{
        connect();
        List<String> ord=new ArrayList<String>();
        String selectString2 = "select * from getRoles()";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ord.add(rs.getString("rname"));
//            System.out.println(rs.getString("role_name"));
        }
        String[] ret=new String[ord.size()];
        for(int i=0;i<ord.size();i++){
            ret[i]=ord.get(i);
        }
        return ret;
    }
    
    
    //Gets username and the desired role(role_id) and set it to the user
    public void setRoleToUser(String user,int rid) throws SQLException{
        connect();
        String SQL = "select * from setRoleToUser("+rid+",'"+user+"')";
        rs=statement.executeQuery(SQL);
    }
    
    
    //Retruns an array of LogPanel
    public List<LogPanel> getLogPanel() throws SQLException{
        connect();
        List<LogPanel> ret=new ArrayList<LogPanel>();
//        int numOfLogPanel=0;
//        String selectString = "select count(*) from products_log_table";
//        rs=statement.executeQuery(selectString);
//        while(rs.next()){
//            numOfLogPanel=rs.getInt("count");
//        }
//        LogPanel[] data=new LogPanel[numOfLogPanel];
        String selectString="select * from getLogs()";
        rs=statement.executeQuery(selectString);
        int i=0;
        while(rs.next()){
            String op=rs.getString("operation");
            Date date=rs.getDate("stamp");
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String strDate=dateFormat.format(date);
            int id=rs.getInt("product_id");
            String name=rs.getString("product_name");
            String description=rs.getString("description");
            int amount=rs.getInt("amount");
            float price=rs.getFloat("price");
            
            
            LogPanel log=new LogPanel();
            log.setOP(op);
            log.setDate(strDate);
            log.setPname(name);//////////
            log.setAmount(amount);
            log.setPID(id);
            log.setPrice(price);
            log.setDescription(description);
            
            ret.add(log);
            
            i++;  
        }
        
        return ret;
    }
    public void deleteSupplier(String name) throws SQLException{
        connect();
        String SQL = "select * from deleteSupplier('"+name+"')";
        rs=statement.executeQuery(SQL);
    }
}
