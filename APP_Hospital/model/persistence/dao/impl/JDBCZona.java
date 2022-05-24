package APP_Hospital.model.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import APP_Hospital.model.business.entities.Zona;
import APP_Hospital.model.persistence.exceptions.DAOException;

public class JDBCZona {
   
    public static void add(Zona z) throws DAOException, SQLException{
        short id = 0;
        Connection conn = MySQLConnection.getConnection();
        String sqlMaxID = "Select max(idZona)+1 from "+MySQLConnection.getDatabase()+".Zona";
        try{
            
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sqlMaxID);
            rs = stmt.getResultSet();
            if(rs.next()){
                id=rs.getShort(1);
            }
            conn.createStatement();
            String query ="insert into "+ MySQLConnection.getDatabase() +".Zona(idZona,nom) values(?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setShort (1, id);
            preparedStmt.setString (2, z.getZona());
            preparedStmt.execute();
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        
    }

    public static Zona get(short id) throws DAOException, SQLException {
        Zona cat = null;
        String sql = "select * from "+MySQLConnection.getDatabase()+".Zona where idZona="+id;
        Connection conn = MySQLConnection.getConnection();
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            rs = stmt.getResultSet();
            

            //if Zona exists in the DB return it as an object
            if (rs.next())
            {
                cat = new Zona(rs.getString(2)); 
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }

        return cat;
    }

    public static String getAll() throws DAOException, SQLException {
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM "+MySQLConnection.getDatabase()+".Zona";
        String result ="";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            rs = stmt.getResultSet();

            //millorar rendiment
            while (rs.next())
            {
                result=result + "id: "+rs.getShort(1)+" | nom: "+rs.getString(2)+"\n";
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        return result;
    }

    public static void delete(short id) throws DAOException, SQLException{
        Connection conn = MySQLConnection.getConnection();
        String sql = "DELETE FROM "+MySQLConnection.getDatabase()+".Zona where idZona="+id;

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
           
        }
    }  
}
