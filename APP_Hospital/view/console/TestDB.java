/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.view.console;

import java.util.logging.Level;
import java.util.logging.Logger;
import APP_Hospital.model.persistence.dao.impl.JDBCPersonaDAO;
import APP_Hospital.model.persistence.dao.impl.MySQLConnection;
import APP_Hospital.model.persistence.exceptions.DAOException;

/**
 *
 * @author nilp2
 */
public class TestDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MySQLConnection conn = MySQLConnection.getInstance();
        var data = new JDBCPersonaDAO();
        try {
            System.out.println(data.getAll());
        } catch (DAOException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
