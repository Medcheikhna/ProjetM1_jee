/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modules;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author AdminCH
 */
public class Chat {
    private int id, from, to;
    private String content, sender, receiver;
    private Date date;
    private Time time;

    public Chat(int id, int from, int to, String content, String sender, String receiver, Date date, Time time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.time = time;
    }
    public Chat(  int from, int to, String content, Date date, Time time) {
         this.from = from;
        this.to = to;
        this.content = content; 
        this.date = date;
        this.time = time;
    }

    public Chat() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    
    
    
}
