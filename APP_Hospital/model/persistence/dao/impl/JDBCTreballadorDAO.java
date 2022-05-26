/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.persistence.dao.impl;

import java.sql.SQLException;

import APP_Hospital.model.business.entities.Guardies;
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


    public static String getAll() throws DAOException, SQLException{
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM "+MySQLConnection.getDatabase()+".Treballador";
        String result ="";

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            rs = stmt.getResultSet();

            //millorar rendiment
            while (rs.next())
            {
                result=result + "Prioritat: "+rs.getShort(5)+" | Categoria: "+rs.getShort(4)+" | id: "+rs.getShort(1)+" | Usuari: "+rs.getString(2)+
                " "+rs.getString(3)+"\n";
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        return result;
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

     
    public static void update(Treballador t) throws DAOException, SQLException {
        Short id = t.getId();
        MySQLConnection.getConnection();
        delete(id);
        add(t);
        
    }

    //delete Treballador passing it as an object
    public static void delete(Treballador t) throws DAOException, SQLException {
        Short id = t.getId();
        Connection conn = MySQLConnection.getConnection();
        String sql = "DELETE FROM "+MySQLConnection.getDatabase()+".Treballador where idTorn="+id;

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
           
        }
    }
    //delete Treballador passing is as an ID
    public static void delete(short id) throws DAOException, SQLException {
        Connection conn = MySQLConnection.getConnection();
        String sql = "DELETE FROM "+MySQLConnection.getDatabase()+".Treballador where idTorn="+id;

        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
           
        }
    }

    public static void guaridesInscrites(Treballador t) throws DAOException, SQLException {
        Connection conn = MySQLConnection.getConnection();
        Short id = t.getId();
        String sql = "select t.idGuardia, idData from hospital.TreballadorsApuntats t left join hospital.Guardia g ON t.idGuardia = g.idGuardia where idTreballador="+id;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            rs = stmt.getResultSet();

            //millorar rendiment
            while (rs.next())
            {
                t.reservarGuardia(new Guardies(rs.getShort(1), rs.getString(7), rs.getShort(6), rs.getShort(4), rs.getShort(5), rs.getShort(3)));
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
    }

    public static void inscriureGuardia(Treballador t, Guardies g) throws DAOException, SQLException {
        Connection conn = MySQLConnection.getConnection();
        Short idTreballador = g.getId();
        Short idGuardia = g.getId();
        String sqlOrdre = "SELECT MAX(numInscripcio) FROM "+MySQLConnection.getDatabase()+".TreballadorsApuntats WHERE idGuardia="+idGuardia; 
        String sqlCancelada = "SELECT cancelada FROM "+MySQLConnection.getDatabase()+".TreballadorsApuntats WHERE idGuardia="+idGuardia+" and idTreballador="+idTreballador;
        short cancelat,ordre;

        //set Ordre    
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sqlOrdre);
            rs = stmt.getResultSet();
            
            if (rs.next())
            {
                ordre = rs.getShort(1);
                ordre++;   
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }

        //set cancelada
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sqlCancelada);
            rs = stmt.getResultSet();
            
            if (rs.next())
            {
                cancelat = rs.getShort(1);
                cancelat++;   
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());   
        }

        //add to TreballadorsApuntats
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
}
