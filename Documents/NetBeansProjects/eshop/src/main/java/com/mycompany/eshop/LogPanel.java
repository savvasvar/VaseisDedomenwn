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
public class LogPanel {
    
    private int pid, amount;
    private float price;
    private String op,pname, description, Date;
    
    public LogPanel(){
        
    }
    
     public void setPID(int id){
        this.pid = id;
    }
    public int getPID(){
        return this.pid;
    }
    
    public void setAmount(int id){
        this.amount = id;
    }
    public int getAmount(){
        return this.amount;
    }
    
    public void setPrice(float price){
        this.price = price;
    }
    public float getAprice(){
        return this.price;
    }
    
    public void setOP(String op){
        this.op = op;
    }
    public String getOP(){
        return this.op;
    }
    
    public void setPname(String pname){
        this.pname = pname;
    }
    public String getPname(){
        return this.pname;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    
    public void setDate(String Date){
        this.Date = Date;
    }
    public String getDate(){
        return this.Date;
    }
    
    public String[] getLogPanelString(){
        String id=String.valueOf(this.pid);
        String amount=String.valueOf(this.amount);
        String date = String.valueOf(this.Date);
        String price=String.valueOf(this.price);
        String[] data={op, date, id, pname, description, amount, price};
        return data;
    }
}
