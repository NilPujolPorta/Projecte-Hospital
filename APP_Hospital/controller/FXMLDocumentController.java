package APP_Hospital.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Statement;
import APP_Hospital.model.persistence.dao.impl.JDBCTreballadorDAO;
import APP_Hospital.model.persistence.dao.impl.MySQLConnection;
import APP_Hospital.view.gui.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLDocumentController {

    @FXML
    private PasswordField Input_passwd;

    @FXML
    private TextField Input_user;

    @FXML
    private Text TextOutError;

    @FXML
    private Button btn_quit;

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

    private Boolean checkCredencial(String user, String password) throws SQLException{
        boolean result = false;
        Connection conn = MySQLConnection.getConnection();
        //comprobacions
         try{
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("SELECT * FROM "+ MySQLConnection.getDatabase() +".Login where usuari='"+user+"' and password='"+password+"'");
            rs = stmt.getResultSet();
            if (rs.next())
            {
             result= true;
             Short idTreballador = rs.getShort(1);
             Main.TreballadorLoggejat = JDBCTreballadorDAO.get(idTreballador);
            }
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
            System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
        return result;
    }
    
    @FXML
    void quit(ActionEvent event) {
        Main.intencioQuit = true;
        Stage stage = (Stage) button_Login.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}
