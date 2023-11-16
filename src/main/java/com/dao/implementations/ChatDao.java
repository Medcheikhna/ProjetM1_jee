/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementations;

import com.modules.Chat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.connDatabase.DBconnection;
/**
 *
 * @author AdminCH
 */
public class ChatDao {
    public static Connection getConnection() {
        Connection con = null;
        try {
        	con = DBconnection.getConnection();
			 } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public static int savenew(Chat t) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "insert into chat(chat_id, chat_from, chat_to, chat_content, chat_date, chat_time) values(?,?,?,?,?,?)");
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getFrom());
            ps.setInt(3, t.getTo());
            ps.setString(4, t.getContent());
            ps.setDate(5,new java.sql.Date(t.getDate().getTime()));
            ps.setTime(6, t.getTime());
            status = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
     public static List<Chat> getAllChats(int receiver) {
        List<Chat> list = new ArrayList<Chat>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select chat_id, chat_from, chat_to, chat_content, chat_date, chat_time, nom, prenom from chat inner join utilisateur on chat_from=id_user where chat_to="+receiver+" order by chat_date, chat_time asc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chat t = new Chat();
                t.setId(rs.getInt("chat_id"));
                t.setFrom(rs.getInt("chat_from"));
                t.setTo(rs.getInt("chat_to"));
                t.setContent(rs.getString("chat_content"));
                t.setDate(rs.getDate("chat_date"));
                t.setTime(rs.getTime("chat_time"));
                t.setSender(rs.getString("nom")+" "+rs.getString("prenom"));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
 	public int getCountChatUsers(int id_user) {
		 int u = 0;
	        try {
	            Connection con = DBconnection.getConnection();
	            PreparedStatement ps = con.prepareStatement("select count(*)as total from chat where  chat_to="+id_user);
//	            PreparedStatement ps = con.prepareStatement("select count(*)as total from chat where CURRENT_DATE()=chat_date and chat_to="+id_user);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                u=rs.getInt("total");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return u;
	}
}
