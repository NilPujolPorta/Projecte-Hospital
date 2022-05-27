package APP_Hospital.view.gui;

import java.sql.SQLException;
import java.util.Scanner;

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
        short opcio = puntsMenu(admin);
        menuPrincipal(opcio, admin);

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void logOut(String[] args) {
        TreballadorLoggejat = null;
        launch(args);
    }

    /**
     * 
     * @param admin
     * @return
     */
    private Short puntsMenu(boolean admin) {
        System.out.println("Escull una de les següents opcions:"
                + "\n 0 - Tancar Sessió"
                + "\n 1 - Veure les meves guàrdies"
                + "\n 2 - Apuntar-me a un dia de guàrdia");
        if (admin) {
            System.out.println(" 3 - Veure les guàrdies dels treballadors"
                    + "\n 4 - Editar fitxer de configuració");
        }
        try (Scanner lectura = new Scanner(System.in)) {
            short opcio = lectura.nextShort();
            return opcio;
        }
    }

    /**
     * Crida les funcions necessàries segons la opció escollida
     * 
     * @param opcio short
     */
    public static void menuPrincipal(short opcio, boolean admin) {

        boolean correcte = true;
        while (!correcte) {
            //logout
            if (opcio == 0) {
                correcte = true;
                logOut(null);
                // Veure les meves guardies
            } else if (opcio == 1) {
                correcte = true;
                try {
                    JDBCTreballadorDAO.guaridesInscrites(TreballadorLoggejat);
                } catch (DAOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(TreballadorLoggejat.getGuardies());                
                // Apuntarme a un dia de guardia
            } else if (opcio == 2) {
                correcte = true;
                calendari.opcionsGuardia();
                // Veure les guardies dels treballadors
            } else if (opcio == 3 && admin) {
                correcte = true;
                System.out.println("under progress");
                // Editar fitxer de configuració
            } else if (opcio == 4 && admin) {
                correcte = true;
                System.out.println("under progress");
            }
        }

    }

}
