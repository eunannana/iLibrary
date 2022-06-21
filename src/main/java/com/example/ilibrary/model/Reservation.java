package com.example.ilibrary.model;

public class Reservation {
    public String memberIDresv, resvDate, resvReturn;
    public Reservation(){

    }
    public Reservation(String memberIDresv, String resvDate, String resvReturn){
        this.memberIDresv = memberIDresv;
        this.resvDate = resvDate;
        this.resvReturn = resvReturn;
    }
    public String getMemberIDresv(){return memberIDresv;}
    public void setMemberIDresv(String memberIDresv){this.memberIDresv = memberIDresv;}

    public String getResvDate(){return resvDate;}
    public void setResvDate(String resvDate){this.resvDate = resvDate;}

    public String getResvReturn(){return resvReturn;}
    public void setResvReturn(String resvReturn){this.resvReturn = resvReturn;}
}
