/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubaya.model;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class Penyewa {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String notelp;

    Connection connect;
    Statement state;
    ResultSet result;

    public Penyewa() {
        getConnection();
    }

    public Penyewa(String username, String password, String fullname, String notelp) {
        
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
        setNotelp(notelp);
    }
    
    public Penyewa(int id,String username, String password, String fullname, String notelp) {
        setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setFullname(fullname);
        setNotelp(notelp);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/houserental", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connect;
    }

    public Boolean insert() {
        boolean regis = false;
        getConnection();
        try {
            state = (Statement) connect.createStatement();
            if (!connect.isClosed()) {

                String query = "Insert into penyewa (`username`, `password` ,`nama` , `nomor telepon`) VALUES (?, ?, ?, ?)";

                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                sql.setString(1, getUsername());
                sql.setString(2, getPassword());
                sql.setString(3, getFullname());
                sql.setString(4, getNotelp());
                int count = sql.executeUpdate();
                regis = (count == 1);
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return regis;
    }
    
    public Boolean login(String uname, String passwd){
        boolean login = false;
        getConnection();
        
        try {
            state = (Statement) connect.createStatement();
            if (!connect.isClosed()) {

                String query = "SELECT * FROM `penyewa` WHERE username = ? AND password = ?";

                PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);

                sql.setString(1, uname);
                sql.setString(2, passwd);
                sql.execute();
                
                result = sql.getResultSet();
                result.next();
                
                int rowCount = result.getRow();
                if(rowCount == 1) login = true;
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return login;
    }
    
    public Penyewa getPenyewa(String username){
        try {
            
            state = (Statement) connect.createStatement();
            
            String query = "Select * from penyewa where username = ?";
//            result = state.executeQuery();
            PreparedStatement sql = (PreparedStatement) connect.prepareStatement(query);
            sql.setString(1, username);
            sql.execute();
            result = sql.getResultSet();
            while(result.next())
            {
                        setId(result.getInt("id"));
                        setUsername(result.getString("username"));
                        setPassword(result.getString("password"));
                        setFullname(result.getString("nama"));
                        setNotelp(result.getString("nomor telepon"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Rumah.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    public ArrayList<String> displayToString() {
        
        ArrayList<String> temp = new ArrayList<String>();
        try {
            state = (Statement) connect.createStatement();
            result = state.executeQuery("SELECT * FROM penyewa");
            while(result.next() == true) {
                Penyewa penyewa = new Penyewa(
                        result.getString("username"), 
                        result.getString("password"), 
                        result.getString("nama"), 
                        result.getString("nomor telepon"));
                temp.add(penyewa.username + "-" + penyewa.password + "-" + penyewa.fullname + "-" + penyewa.notelp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return temp;
    }
}
