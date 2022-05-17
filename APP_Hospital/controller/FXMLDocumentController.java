/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APP_Hospital.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import APP_Hospital.model.business.entities.Persona;

/**
 *
 * @author nilp2
 */
public class FXMLDocumentController implements Initializable {
     private Persona persona;
    @FXML
    private Button button;
    @FXML
    private Button btn_desar;
    @FXML
    private Label nomlable;
    @FXML
    private TextField nominput;
    @FXML
    private Label dniLable;
    @FXML
    private TextField dniinput;
    @FXML
    private Label edatLable;
    @FXML
    private TextField edatInput;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        persona = new Persona("hey","patata", 20);
    }    

    @FXML
    private void btnDesarAction(ActionEvent event) {
        persona.setDni(dniinput.getText());
        persona.setNom(nominput.getText());
        persona.setEdat(Short.parseShort(edatInput.getText()));
    }

    @FXML
    private void btnSaludarAction(ActionEvent event) {
        mostrarDialog(persona);
    }
    private void mostrarDialog(Persona persona) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);

    }
}
