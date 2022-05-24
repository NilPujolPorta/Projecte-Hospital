package APP_Hospital.model.persistence.dao.impl;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

import APP_Hospital.model.business.utils.utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class MySQLConnection {
    
    static String database;
    private static Connection conn;
    private static String url;
    private static MySQLConnection instance;
    
    public MySQLConnection(){
        if (url == null) {
            setUrl();
        }
        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public static MySQLConnection getInstance(){
        if (instance == null){
            instance = new MySQLConnection();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException{
        if (conn == null){
            instance = new MySQLConnection();
        }
        conn = DriverManager.getConnection(url);
        return conn;
    }
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void setUrl() {
        Properties bd = utils.loadConfig();
        database= bd.getProperty("app.db.databaseName");
        url = "jdbc:mysql://" + bd.getProperty("app.db.Server") +
                "/" + database + "?" +
                "user=" + bd.getProperty("app.db.User") +
                "&password=" + bd.getProperty("app.db.Password");
    }
    public static String getUrl() {
        if (url == null){setUrl();}
        return url;
    }
    public static String getDatabase() {
        return database;
    }
        
}
