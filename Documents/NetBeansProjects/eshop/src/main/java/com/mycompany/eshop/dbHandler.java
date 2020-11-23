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
        String selectUser="select username,email,user_id,role_id from users where username='"+username+"'";
        rs=statement.executeQuery(selectUser);
        while(rs.next()){
          String usrn=rs.getString("username");
          usr.setUsername(usrn);
          String email=rs.getString("email");
          usr.setEmail(email);
          int user_id=rs.getInt("user_id");
          usr.setUserId(user_id);
          int role_id=rs.getInt("role_id");
          usr.setRoleId(role_id);
        }
        return usr;
    }
    
    
    //Get product_id integer and returns product object if found
    public Product getProduct(int id) throws SQLException{
        connect();
        Product pr=new Product();
        String selectString = "select * from products where product_id="+id;
        rs=statement.executeQuery(selectString);
        while(rs.next()){
           pr.setName(rs.getString("product_name"));
           pr.setBarcode(rs.getInt("barcode"));
           pr.setAmount(rs.getInt("amount"));
           pr.setPrice(rs.getFloat("price"));
           pr.setDescription(rs.getString("description"));
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
        String selectString = "select username from users";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            String username=rs.getString("username");
            usenameList.add(username);
        }
        return usenameList;
    }
    
    //Get username string and returns the role_id of user so it is easier to match in role management combobox
    public int getRolePos(String username) throws SQLException{
        connect();
        int ret=0;
        String selectString = "select role_id from users where username='"+username+"'";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            ret=rs.getInt("role_id");
        }
        return ret-1;
    }
    
    
    //Returns an array of all products
    public Product[] getProducts() throws SQLException{
        connect();
        int numOfProducts=0;
        String selectString = "select count(*) from products";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            numOfProducts=rs.getInt("count");
        }
        Product[] data=new Product[numOfProducts];
        selectString="select * from products order by product_id ASC";
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
        String selectString2 = "select username from customers where customer_id="+id;
        rs=statement.executeQuery(selectString2);
        while(rs.next()){
            name=rs.getString("username");
        }
        return name;
    }
    
    //Gets product_id as an integer and returns product name string
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

    
    //Returns an array of orders where orders with the same order_id are unified in one order
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
    
    
    //Gets products name and return a float that corespons to products price
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
    
    //Gets a string of products name and returns the int price of barcode
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
    
    //Gets an integer and set the order_id with the same number to true that set the order as completed
    public int  OrderSetComplete(int id) throws SQLException{
        connect();
        String SQL = "UPDATE orders SET completed=true where order_id=?";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setInt(1, id);
        int affectedRows = statementIns.executeUpdate();
        
        return affectedRows;
    }
    
    //Gets an order object and updates the ammount of products when the order completes (subtraction)
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
    
    
    //Returns a String List of the suppliers
    public List<String> getSuppliers() throws SQLException{
        connect();
        List<String> ret=new ArrayList<String>();
        String selectString2 = "select sname from suppliers order by sid ASC";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ret.add(rs.getString("sname"));
        }
        return ret;
    }
    
    //Gets a string of supplier name and inserts it to the database
    public int insertSupplier(String name) throws SQLException{
        connect();
        String SQL = "INSERT INTO suppliers(sname) VALUES(?)";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setString(1, name);
        int affectedRows = statementIns.executeUpdate();
        return affectedRows;
    }
    
    
    //Gets product current amount,new amount,product id and supplier id and restocks the product (sets new product amount)
    public int reStock(int amount,int newAm,int pid,int sID) throws SQLException{
        connect();
        String SQL = "INSERT INTO restock(pid,amount,supplier) VALUES(?,?,?)";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setInt(1, pid);
        statementIns.setInt(2, amount);
        statementIns.setInt(3, sID);
        int affectedRows = statementIns.executeUpdate();
        String SQL1 = "UPDATE products SET amount=? where product_id=?";
        statementIns= dbConnection.prepareStatement(SQL1);
        statementIns.setInt(1, newAm);
        statementIns.setInt(2, pid);
        affectedRows = statementIns.executeUpdate();
        return affectedRows;
    }
    
    //Returns  a String List of user Roles
    public List<String> getRoles() throws SQLException{
        connect();
        List<String> ret=new ArrayList<String>();
        String selectString2 = "select role_name from roles order by role_id ASC";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ret.add(rs.getString("role_name"));
        }

        return ret;
    }
    
    //Gets strin name and inserts it to database
    public int addRole(String rname) throws SQLException{
        connect();
        String SQL = "INSERT INTO roles(role_name) VALUES(?)";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setString(1, rname);
        int affectedRows = statementIns.executeUpdate();

        return affectedRows;        
    }
    
    //Gets int integer of current posistion selected and copies the above's item name to current position and the name of curent item is set to the aboves value
    public int ReOrderUP(int inp,String nf,String ns) throws SQLException{
        int affectedRows=0;
        connect();
        String SQL = "UPDATE roles SET role_name=? where role_id=?";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setString(1, nf);
        statementIns.setInt(2, inp-1);
        affectedRows = statementIns.executeUpdate();
        System.out.println("\nRows "+affectedRows+" "+nf+" "+(inp-1));
        statementIns.setString(1, ns);
        statementIns.setInt(2, inp);
        affectedRows = statementIns.executeUpdate();
        System.out.println("\nRows "+affectedRows+" "+nf+" "+inp);

        return affectedRows;    
    }
    
    //Gets int integer of current posistion selected and copies the belows's item name to current position and the name of curent item is set to the belows value
    public int ReOrderDown(int inp,String nf,String ns) throws SQLException{
        int affectedRows=0;
        connect();
        String SQL = "UPDATE roles SET role_name=? where role_id=?";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setString(1, nf);
        statementIns.setInt(2, inp+1);
        affectedRows = statementIns.executeUpdate();
        System.out.println("\nRows "+affectedRows+" "+nf+" "+(inp+1));
        statementIns.setString(1, ns);
        statementIns.setInt(2, inp);
        affectedRows = statementIns.executeUpdate();
        System.out.println("\nRows "+affectedRows+" "+ns+" "+inp);

        return affectedRows;    
    }
    
    //Returns the array of roles 
    public String[] refresh() throws SQLException{
        connect();
        List<String> ord=new ArrayList<String>();
        String selectString2 = "select role_name from roles order by role_id ASC";
        rs=statement.executeQuery(selectString2);
        while(rs.next()){ 
            ord.add(rs.getString("role_name"));
//            System.out.println(rs.getString("role_name"));
        }
        String[] ret=new String[ord.size()];
        for(int i=0;i<ord.size();i++){
            ret[i]=ord.get(i);
        }
        return ret;
    }
    
    
    //Gets username and the desired role(role_id) and set it to the user
    public int setRoleToUser(String user,int rid) throws SQLException{
        connect();
        String SQL = "UPDATE users SET role_id=? where username=?";
        statementIns= dbConnection.prepareStatement(SQL);
        statementIns.setInt(1, rid);
        statementIns.setString(2, user);
        int affectedRows = statementIns.executeUpdate();

        return affectedRows;
    }
    
    
    //Retruns an array of LogPanel
    public LogPanel[] getLogPanel() throws SQLException{
        connect();
        int numOfLogPanel=0;
        String selectString = "select count(*) from products_log_table";
        rs=statement.executeQuery(selectString);
        while(rs.next()){
            numOfLogPanel=rs.getInt("count");
        }
        LogPanel[] data=new LogPanel[numOfLogPanel];
        selectString="select * from products_log_table";
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
            
            data[i]=log;
            
            i++;  
        }
        
        return data;
    }
}
