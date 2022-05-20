/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.persistence.dao.impl;

import java.sql.SQLException;
import java.util.List;
import APP_Hospital.model.business.entities.Treballador;
import APP_Hospital.model.persistence.dao.contracts.TreballadorDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;

import APP_Hospital.model.persistence.exceptions.DAOException;
import APP_Hospital.model.business.utils.utils;
import java.util.Properties;


public class JDBCTreballadorDAO implements TreballadorDAO {

    Connection conn;
    Statement stmt;
    ResultSet rs;

    //return Treballador as an object from the database
    @Override
    public Treballador get(long id) throws DAOException {
        Treballador T1 = null;
        
        try{
            stmt = conn.createStatement();
            rs= stmt.executeQuery("SELECT * FROM "+ MySQLConnection.getDatabase() +" where id="+id);
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

    @Override
    public List<Treballador> getAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(Treballador t) throws DAOException {
        try{
            stmt = conn.createStatement();
            String query ="insert into"+ MySQLConnection.getDatabase() +".Treballador(idTreballador,nom,cognom,idCategoria) values(?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setShort (1, t.getId());
            preparedStmt.setString (2, t.getNom());
            preparedStmt.setString (3, t.getCognoms());
            preparedStmt.setShort (3, t.getCat());
            preparedStmt.execute();
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        
    }

    @Override
    public void update(Treballador t) throws DAOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Treballador t) throws DAOException {
        // TODO Auto-generated method stub
        
    }

  
}
