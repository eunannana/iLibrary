package com.example.ilibrary.model;

import android.content.Context;

import java.util.List;

public class User {
    private String l_ID, l_name, l_pass, ID, name, address, phone, email;
    public User(){

    }
    public User(String l_ID, String l_name, String l_pass, String ID, String name, String address, String phone, String email)
    {
        this.l_ID = l_ID;
        this.l_name = l_name;
        this.l_pass = l_pass;
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public String getL_ID(){
        return l_ID;
    }
    public void setL_ID(String l_ID)
    {
        this.l_ID = l_ID;
    }

    public String getL_name(){
        return l_name;
    }
    public void setL_name(String l_name)
    {
        this.l_name = l_name;
    }

    public String getL_pass(){
        return l_pass;
    }
    public void setL_pass(String l_pass)
    {
        this.l_pass = l_pass;
    }

    public String getID(){return ID;}
    public void setID(String ID){this.ID = ID;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
}

