package APP_Hospital.model.business.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class utils{
    
    static Connection conn = null;
    static String url = null;

    public static Properties loadConfig(){
        Properties prop = new Properties();
        String fileName = System.getProperty("user.dir")+"\\config\\config.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("ERROR");            
        }
        return prop;
    }

    public static void setUrl() {
        Properties bd = loadConfig();
        url = "jdbc:mysql://"+bd.getProperty("app.db.Server")+
                "/"+bd.getProperty("app.db.databaseName")+"?" +
                "user="+bd.getProperty("app.db.User")+
                "&password="+bd.getProperty("app.db.Password");
        
    }
    
    public static Connection DBconnection(){
        if(url==null){
            setUrl();
        }
        try {
            conn =
               DriverManager.getConnection(url);

        
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
    public static void DBclose(){

        try {
            conn.close();
            System.out.println("s'ha tancat la connexió");
        } catch (SQLException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


