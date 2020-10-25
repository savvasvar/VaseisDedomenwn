/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanasis
 */
public class Orders {
    private int order_id;
    private String customer,date;
    private List<Order> productList=new ArrayList<Order>();         
    public Orders(){
        
    }
    public void setOrderID(int order_id){
        this.order_id=order_id;
    }
    public int getOrderID(){
        return this.order_id;
    }
    public void setCustomer(String customer){
        this.customer=customer;
    }
    public String getCustomer(){
        return this.customer;
    }
    public void setDate(String Date){
        this.date=Date;
    }
    public String getDate(){
        return this.date;
    }
    public List<Order> getProductList(){
         return this.productList;   
    }
    public void setProductList(List<Order> list){
        this.productList=list;
    }
}
