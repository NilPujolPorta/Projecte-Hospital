package APP_Hospital.model.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import APP_Hospital.model.business.entities.Guardies;
import java.sql.SQLException;
import java.sql.Statement;

import APP_Hospital.model.persistence.exceptions.DAOException;

public class JDBCGuardiaDAO {


    public static void add(Guardies g) throws DAOException, SQLException {
        Connection conn = MySQLConnection.getConnection();
        Short id = null;
        String sqlMaxID = "Select max(idGuardia)+1 from "+MySQLConnection.getDatabase()+".Guardia";
        try{
            
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sqlMaxID);
            rs = stmt.getResultSet();
            if(rs.next()){
                id=rs.getShort(1);
            }
            conn.createStatement();
            String query ="insert into "+ MySQLConnection.getDatabase() +".Guardia(idGuardia,idData,idCategoria,idTorn,idZona,places) values(?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setShort (1, id);
            preparedStmt.setString (2, g.getDia());
            preparedStmt.setShort (3, g.getCat());
            preparedStmt.setShort (4, g.getTorn());
            preparedStmt.setShort (5, g.getZona());
            preparedStmt.execute();
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }      
    }
}
