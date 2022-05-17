package APP_Hospital.model.persistence.dao.impl;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nilp2
 */
public class MySQLConnection {
    private final String FILE_CONFIG = "resources/config.properties";
    private static MySQLConnection instance;
    private Connection connection;
    
    private MySQLConnection(){
        Properties prop = new Properties();
        try{
            InputStream config = new FileInputStream(FILE_CONFIG);
            prop.load(config);
            String driver= prop.getProperty("driver");
            String url= prop.getProperty("url");
            String user= prop.getProperty("user");
            String password= prop.getProperty("password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);

        }catch(IOException ex){
            System.out.println(ex);
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static MySQLConnection getInstance(){
        if (instance == null){
            instance = new MySQLConnection();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
