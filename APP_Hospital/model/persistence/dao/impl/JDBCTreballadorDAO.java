/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.persistence.dao.impl;

import java.sql.SQLException;
import java.util.List;
import APP_Hospital.model.business.entities.Treballador;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import APP_Hospital.model.persistence.exceptions.DAOException;


public class JDBCTreballadorDAO{



    //return Treballador as an object from the database

    public static Treballador get(short id) throws DAOException, SQLException {
        Treballador T1 = null;
        Connection conn = MySQLConnection.getConnection();
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("SELECT * FROM "+ MySQLConnection.getDatabase() +".Treballador where idTreballador="+id);
            rs = stmt.getResultSet();
            

            //if Treballador exists in the DB return it as an object
            //si no el troba tornem un null???
            if (rs.next())
            {
                T1 = new Treballador(rs.getShort(1),
                rs.getString(2),
                rs.getString(3),
                rs.getShort(4));
                
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        return T1;
    }


    public static List<Treballador> getAll() throws DAOException {
        return null;
    }


    public static void add(Treballador t) throws DAOException, SQLException {
        Connection conn = MySQLConnection.getConnection();
        try{
            conn.createStatement();
            String query ="insert into "+ MySQLConnection.getDatabase() +".Treballador(idTreballador,nom,cognoms,idCategoria) values(?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setShort (1, t.getId());
            preparedStmt.setString (2, t.getNom());
            preparedStmt.setString (3, t.getCognoms());
            preparedStmt.setShort (4, t.getCat());
            preparedStmt.execute();
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        
    }

     
    public static void update(Treballador t) throws DAOException {
        
    }

     
    public static void delete(Treballador t) throws DAOException {
        
    }

  
}
