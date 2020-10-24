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
public class Product {
    
    private int id,amount,barcode;
    private float price;
    private String name,description;
    
    public Product(){
        
    }
    
    public void setID(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public int getAmount(){
        return amount;
    }
    public void setBarcode(int barcode){
        this.barcode=barcode;
    }
    public int getBarcode(){
        return barcode;
    }
    public void setPrice(float price){
        this.price=price;
    }
    public float getPrice(){
        return price;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return description;
    }
    
    public String[] getProductString(){
        String id=String.valueOf(this.id);
        String barcode=String.valueOf(this.barcode);
        String amount=String.valueOf(this.amount);
        String price=String.valueOf(this.price);
        String[] data={id,name,barcode,amount,price,description};
        return data;
    }
}
