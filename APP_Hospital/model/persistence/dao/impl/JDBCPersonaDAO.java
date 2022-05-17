/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.persistence.dao.impl;

import java.sql.SQLException;
import java.util.List;
import APP_Hospital.model.business.entities.Persona;
import APP_Hospital.model.persistence.dao.contracts.PersonaDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import APP_Hospital.model.persistence.exceptions.DAOException;

/**
 *
 * @author nilp2
 */
public class JDBCPersonaDAO implements PersonaDAO {

    @Override
    public Persona get(long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> getAll() throws DAOException {
        List<Persona> llista = new ArrayList<>();
        try {
            Statement query = MySQLConnection.getInstance().getConnection().createStatement();
            ResultSet result = query.executeQuery("Select * FROM persones");
            
            while(result.next()){
                llista.add(new Persona(result.getString("name"),
                        result.getString("dni"),
                        result.getShort("edat")));
            }
        } catch (SQLException ex) {
            throw new DAOException();
        }
        return llista;
    }

    @Override
    public void add(Persona t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Persona t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Persona t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
