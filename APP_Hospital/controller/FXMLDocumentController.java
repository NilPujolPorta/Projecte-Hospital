package APP_Hospital.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
        String user = Input_user.getText();
        String password = Input_passwd.getText();
        Boolean Login = false;
        
        try{
            Login = checkCredencial(user, password);
        } catch (Exception e){
            TextOutError.setFill(Color.rgb(255, 0, 0));
            TextOutError.setText("Error de DB:\n"+e);
            return;
        } 
        
        if (Login) {
            Stage stage = (Stage) button_Login.getScene().getWindow();
            stage.close();
            Platform.exit();
        } else {
            TextOutError.setFill(Color.rgb(255, 0, 0));
            TextOutError.setText("Credencials erronies");
        }
    }

    private Boolean checkCredencial(String user, String password) {
        //comprobacions
        return false;
    }

}
