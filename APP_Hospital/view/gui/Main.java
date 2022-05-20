package APP_Hospital.view.gui;

import APP_Hospital.model.business.utils.utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 500, 200));
        primaryStage.show();
    }
    @Override
    public void stop(){

        utils.loadConfig();
        calendari.menuOpcions();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
