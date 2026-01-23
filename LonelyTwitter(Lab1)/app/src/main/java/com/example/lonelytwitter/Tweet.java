package com.example.lonelytwitter;
import java.util.Date;

public abstract class Tweet implements  Tweetable {
    private Date date;
    private String message;

    public Tweet(String message){
        this.date = new Date();
        this.message = message;
    }

    public Tweet(String message, Date date){
        this.date = date;
        this.message = message;
    }

    public Date getDate(){
        return this.date;
    }

    public String getMessage(){
        return this.message;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public abstract Boolean isImportant();

}
