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
public class Order_View {
    
    public int or_id;
    public String uname;
    public String dt;
    
    public Order_View(int id,String user,String date){
        this.or_id=id;
        this.uname=user;
        this.dt=date;
    }
    public Order_View(){}
    public String[] getOrderViewString(){
        String or_id=String.valueOf(this.or_id);
        String[] data={or_id,this.uname,this.dt};
        return data;
    }
}
