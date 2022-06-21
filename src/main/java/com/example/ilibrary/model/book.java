package com.example.ilibrary.model;

public class book {
    public String book_ID, book_title, book_ISBN, book_year_publish,book_stock, book_subject, book_shelf, book_author, book_publisher, book_physical, book_summary;
    public book(){

    }
    public book( String book_ID, String book_title, String book_ISBN, String book_subject, String book_shelf, String book_author, String book_publisher, String book_year_publish, String book_stock, String book_physical, String book_summary ){
        this.book_ID = book_ID;
        this.book_title = book_title;
        this.book_ISBN = book_ISBN;
        this.book_subject = book_subject;
        this.book_shelf = book_shelf;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_year_publish = book_year_publish;
        this.book_stock = book_stock;
        this.book_physical = book_physical;
        this.book_summary = book_summary;
    }


    public String getBook_ID(){
        return book_ID;
    }
    public void setBook_ID(String book_ID){
        this.book_ID = book_ID;
    }

    public String getBook_title(){return book_title;}
    public void setBook_title(String book_title){this.book_title = book_title;}

    public String getBook_ISBN(){
        return book_ISBN;
    }
    public void setBook_ISBN(String book_ISBN){
        this.book_ISBN = book_ISBN;
    }

    public String getBook_subject()
    {
        return book_subject;
    }
    public void setBook_subject(String book_subject){
        this.book_subject = book_subject;
    }

    public String getBook_shelf(){
        return book_shelf;
    }
    public void setBook_shelf(String book_shelf){
        this.book_shelf = book_shelf;
    }

    public String getBook_author(){
        return book_author;
    }
    public void setBook_author(String book_author){
        this.book_author = book_author;
    }

    public String getBook_publisher(){ return book_publisher; }
    public void setBook_publisher(String book_publisher){
        this.book_publisher = book_publisher;
    }

    public String getBook_year_publish(){
        return book_year_publish;
    }
    public void setBook_year_publish(String book_year_publish){ this.book_year_publish = book_year_publish; }

    public String getBook_stock(){
        return book_stock;
    }
    public void setBook_stock(String book_stock){
        this.book_stock = book_stock;
    }

    public String getBook_physical(){
        return book_physical;
    }
    public void setBook_physical(String book_physical){
        this.book_physical = book_physical;
    }

    public String getBook_summary(){
        return book_summary;
    }
    public void setBook_summary(String book_summary){
        this.book_summary = book_summary;
    }


}
