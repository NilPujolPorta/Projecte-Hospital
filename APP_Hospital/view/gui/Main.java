package APP_Hospital.view.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import APP_Hospital.model.business.entities.Guardies;
import APP_Hospital.model.business.entities.Treballador;
import APP_Hospital.model.business.utils.utils;
import APP_Hospital.model.persistence.dao.impl.JDBCTreballadorDAO;
import APP_Hospital.model.persistence.exceptions.DAOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Treballador TreballadorLoggejat;
    public static boolean intencioQuit = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 500, 200));
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (intencioQuit) {
            return;
        }
        utils.loadConfig();

        boolean admin = true;

        // ** Cridar al menú per escollir una opció **
        Scanner lectura = new Scanner(System.in);
        Integer opcio=0;
        do {
            opcio = puntsMenu(admin, lectura);
            menuPrincipal(opcio, admin, lectura);
        } while (opcio != 0);
        

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void logOut(String[] args) {
        TreballadorLoggejat = null;
    }

    /**
     * 
     * @param admin
     * @param lectura
     * @return
     */
    private static Integer puntsMenu(boolean admin, Scanner lectura) {
        System.out.println("Escull una de les següents opcions:"
                + "\n 0 - Tancar Sessió"
                + "\n 1 - Veure les meves guàrdies"
                + "\n 2 - Apuntar-me a un dia de guàrdia");
        if (admin) {
            System.out.println(" 3 - Veure les guàrdies dels treballadors"
                    + "\n 4 - Editar fitxer de configuració");
        }
            
        Integer opcio = lectura.nextInt();
        return opcio;
            
        
    }

    /**
     * Crida les funcions necessàries segons la opció escollida
     * 
     * @param opcio short
     * @param lectura
     */
    public static void menuPrincipal(Integer opcio, boolean admin, Scanner lectura) {

        //logout
        if (opcio == 0) {
            logOut(null);
            // Veure les meves guardies
        } else if (opcio == 1) {
            try {
                TreballadorLoggejat.guardies = new ArrayList<Guardies>();;
                JDBCTreballadorDAO.guaridesInscrites(TreballadorLoggejat);
            } catch (DAOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Guardies> gs =TreballadorLoggejat.getGuardies();  
            for (int i = 0; i < gs.size(); i++){
                System.out.println(gs.get(i)+" ");
            }              
            // Apuntarme a un dia de guardia
        } else if (opcio == 2) {
            calendari.opcionsGuardia(lectura);
            // Veure les guardies dels treballadors
        } else if (opcio == 3 && admin) {
            System.out.println("under progress");
            // Editar fitxer de configuració
        } else if (opcio == 4 && admin) {
            System.out.println("under progress");
        }else{
            System.out.println("Codi erroni");
        }
    }
}
