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

public class user {
    private String username="",email="";
    private int role_id,user_id;
    
    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
     public void setEmail(String email){
        this.email=email;
    }
    public String getemail(){
        return email;
    }
     public void setRoleId(int role_id){
        this.role_id=role_id;
    }
    public int getRoleId(){
        return role_id;
    }
     public void setUserId(int user_id){
        this.user_id=user_id;
    }
    public int getUserId(){
        return user_id;
    }
    
    public user(){
        
    }
}
