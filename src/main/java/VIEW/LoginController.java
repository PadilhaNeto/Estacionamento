/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author padilha
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     *
     *
     *
     */
    @FXML
    private JFXSpinner spnLogin;
    @FXML
    private AnchorPane paneLogin;
    @FXML
    private JFXTextField txtFldUser;
    @FXML
    private JFXPasswordField txtFldSenha;

    @FXML
    public void btnLoginClick() throws IOException {

        if (txtFldUser.getText().equals("admin") && txtFldSenha.getText().equals("admin")) {
            
            mudaCena();
            
//        
        } else {
            System.out.println("Login Errado");
        }
    }

    @FXML
    private void btnSairClick() {
        System.exit(0);

    }
  
    public void mudaCena() throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));
        Scene scene = new Scene(root);

        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Inicio - PdzPark");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        paneLogin.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
  
}
