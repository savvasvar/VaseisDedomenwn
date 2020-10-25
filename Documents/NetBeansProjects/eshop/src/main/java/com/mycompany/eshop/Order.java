/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

/**
 *
 * @author Thanasis
 */
public class Order {
    private int order_id,product_amount;
    private String customer,Date,product_name;
    
    
    
    
    public Order(){
        
    }
    public void setOrderID(int order_id){
        this.order_id=order_id;
    }
    public int getOrderID(){
        return this.order_id;
    }
     public void setProductAmount(int product_amount){
        this.product_amount=product_amount;
    }
    public int getProductAmount(){
        return this.product_amount;
    }
    public void setCustomer(String customer){
        this.customer=customer;
    }
    public String getCustomer(){
        return this.customer;
    }
    public void setDate(String Date){
        this.Date=Date;
    }
    public String getDate(){
        return this.Date;
    }
    public void setProductName(String product_name){
        this.product_name=product_name;
    }
    public String getProductName(){
        return this.product_name;
    }
}
