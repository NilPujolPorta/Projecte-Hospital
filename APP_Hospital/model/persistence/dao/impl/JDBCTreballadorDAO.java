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
import APP_Hospital.model.persistence.exceptions.DAOException;

/**
 *
 * @author nilp2
 */
public class JDBCTreballadorDAO implements TreballadorDAO {

    @Override
    public APP_Hospital.model.persistence.dao.contracts.Treballador get(long id) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<APP_Hospital.model.persistence.dao.contracts.Treballador> getAll() throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(APP_Hospital.model.persistence.dao.contracts.Treballador t) throws DAOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(APP_Hospital.model.persistence.dao.contracts.Treballador t) throws DAOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(APP_Hospital.model.persistence.dao.contracts.Treballador t) throws DAOException {
        // TODO Auto-generated method stub
        
    }




    
}
