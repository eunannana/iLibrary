package com.example.ilibrary.model;

import android.content.Context;

import java.util.List;

public class User {
    private String l_ID, l_name, l_pass, m_ID, m_name, m_pass, m_gender, m_phone, m_email;
    public User(){

    }
    public User(String l_ID, String l_name, String l_pass, String m_ID, String m_name, String m_pass, String m_gender, String m_phone, String m_email)
    {
        this.l_ID = l_ID;
        this.l_name = l_name;
        this.l_pass = l_pass;
        this.m_ID = m_ID;
        this.m_name = m_name;
        this.m_pass = m_pass;
        this.m_gender = m_gender;
        this.m_phone = m_phone;
        this.m_email = m_email;
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

    public String getM_ID(){return m_ID;}
    public void setM_ID(String m_ID){this.m_ID = m_ID;}

    public String getM_name(){return m_name;}
    public void setM_name(String m_name){this.m_name = m_name;}

    public String getM_pass(){return m_pass;}
    public void setM_pass(String m_pass){this.m_pass = m_pass;}

    public String getM_gender(){return m_gender;}
    public void setM_gender(String m_gender){this.m_gender = m_gender;}

    public String getM_phone(){return m_phone;}
    public void setM_phone(String m_phone){this.m_phone = m_phone;}

    public String getM_email(){return m_email;}
    public void setM_email(String m_email){this.m_email = m_email;}
}

