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
import APP_Hospital.model.persistence.exceptions.DAOException;
import APP_Hospital.model.business.utils.utils;
import java.util.Properties;


public class JDBCTreballadorDAO implements TreballadorDAO {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    //method to set the connection in case it doesn't exist
    private void setConn(){
        conn=utils.DBconnection();
    }
    //method to set the connection as a parameter
    public void setConn(Connection n){
        conn=n;
    }

    @Override
    public Treballador get(long id) throws DAOException {
        //ensure the connection to DB exist
        if(conn==null){
            setConn();
        }
        //Posar a utils???
        //podriem posar una var amb cada valor del fitxer per facilitar l'obtenció
        Properties bd = utils.loadConfig();
        String database=bd.getProperty("app.db.databaseName");
        //Posar a utils???
        try{
            stmt = conn.createStatement();
            rs= stmt.executeQuery("SELECT * FROM "+database+" where id="+id);
            rs = stmt.getResultSet();

            if (rs.next())
            {
                Treballador T1 = new Treballador(rs.getShort(1),
                 rs.getString(2),
                  rs.getString(3),
                  rs.getShort(4));
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        
        return T1 ;
    }

    @Override
    public List<Treballador> getAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(Treballador t) throws DAOException {
        // TODO Auto-generated method stub
        
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
