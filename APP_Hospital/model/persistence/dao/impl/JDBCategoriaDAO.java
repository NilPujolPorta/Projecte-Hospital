package APP_Hospital.model.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import APP_Hospital.model.business.entities.Categoria;
import APP_Hospital.model.persistence.exceptions.DAOException;

public class JDBCategoriaDAO {
    
    public static void add(Categoria cat) throws DAOException, SQLException{
        short id = 0;
        Connection conn = MySQLConnection.getConnection();
        String sqlMaxID = "Select max(idCategoria)+1 from "+MySQLConnection.getDatabase()+".categoria";
        try{
            
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sqlMaxID);
            rs = stmt.getResultSet();
            if(rs.next()){
                id=rs.getShort(1);
            }
            conn.createStatement();
            String query ="insert into "+ MySQLConnection.getDatabase() +".Categoria(idCategoria,nom) values(?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setShort (1, id);
            preparedStmt.setString (2, cat.getCat());
            preparedStmt.execute();
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        
    }

    public static Categoria get(short id) throws DAOException, SQLException {
        Categoria cat = null;
        String sql = "select * from "+MySQLConnection.getDatabase()+".Categoria where idCategoria="+id;
        Connection conn = MySQLConnection.getConnection();
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            rs = stmt.getResultSet();
            

            //if Categoria exists in the DB return it as an object
            if (rs.next())
            {
                cat = new Categoria(rs.getString(2)); 
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
        String sql = "SELECT * FROM "+MySQLConnection.getDatabase()+".Categoria";
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
        String sql = "DELETE FROM "+MySQLConnection.getDatabase()+".Categoria where idCategoria="+id;

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
           
        }
    }
}

