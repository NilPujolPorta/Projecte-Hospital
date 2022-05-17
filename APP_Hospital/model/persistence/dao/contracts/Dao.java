/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.model.persistence.dao.contracts;

import java.util.List;
import APP_Hospital.model.persistence.exceptions.DAOException;

/**
 *
 * @author nilp2
 * @param <T>
 */
public interface Dao<T> {
    //consultes
    T get(long id) throws DAOException;
    List<T> getAll() throws DAOException;
    
    //modificadors
    void add(T t) throws DAOException;
    void update(T t) throws DAOException;
    void delete(T t) throws DAOException;
}
