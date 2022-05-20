package APP_Hospital.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLDocumentController {

    @FXML
    private TextField Input_passwd;

    @FXML
    private TextField Input_user;

    @FXML
    private Text TextOutError;

    @FXML
    private Button button_Login;

    @FXML
    void btnLoginAction(ActionEvent event) {
        //comprovar credencials
        if (true) {
            Stage stage = (Stage) button_Login.getScene().getWindow();
            stage.close();
            Platform.exit();
        }
    }

}
