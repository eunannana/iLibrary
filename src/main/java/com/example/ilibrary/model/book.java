package com.example.ilibrary.model;

import java.security.PublicKey;

public class book {
    public String bookID, bookCode, bookTitle, bookSubject, bookAuthor;
    public book(){

    }
    public book( String bookCode, String bookTitle, String bookSubject, String bookAuthor)
    {

        this.bookCode = bookCode;
        this.bookTitle = bookTitle;
        this.bookSubject = bookSubject;
        this.bookAuthor = bookAuthor;
        }

    public String getBookID(){
        return bookID;
    }
    public void setBookID(String bookID){
        this.bookID = bookID;
    }
    public String getBookTitle(){
        return bookTitle;
    }
    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }
    public String getBookSubject(){
        return bookSubject;
    }
    public void setBookSubject(String bookSubject){
        this.bookSubject = bookSubject;
    }
    public String getBookAuthor(){
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor){
        this.bookAuthor = bookAuthor;
    }

    public String getBookCode(){
        return bookCode;
    }
    public void setBookCode(String bookCode){
        this.bookCode = bookCode;
    }




}

