package VIEW;

import MODEL.Preco;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import static config.DAO.precoRepository;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    @FXML
    private StackPane pane;
    @FXML
    private JFXButton btnCliente;
    @FXML
    private JFXSpinner spnLoad;
   
     @FXML
  public void btnSairClick(){
      
      System.exit(0);
      
  }
    @FXML
    private void btnClienteClick() throws IOException { 
        
           
       
    spnLoad.setVisible(true);
          Stage stage = new Stage();
          Parent root= null;
           root = FXMLLoader.load(getClass().getResource("/fxml/Cliente.fxml"));
       
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Cliente");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
      pane.getScene().getWindow().hide(); 

      
    }
     @FXML
    private void btnVeiculoClick() throws IOException {         
           
       
    spnLoad.setVisible(true);
          Stage stage = new Stage();
          Parent root= null;
           root = FXMLLoader.load(getClass().getResource("/fxml/Veiculo.fxml"));
       
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Veiculo");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
      pane.getScene().getWindow().hide(); 

      
    }
     @FXML
    private void btnMovimentoClick() throws IOException {         
           
       
    spnLoad.setVisible(true);
          Stage stage = new Stage();
          Parent root= null;
           root = FXMLLoader.load(getClass().getResource("/fxml/Movimento.fxml"));
       
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Movimento");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
      pane.getScene().getWindow().hide(); 

      
    }
    
       @FXML
    private void btnVagaClick() throws IOException {            
       
    spnLoad.setVisible(true);
          Stage stage = new Stage();
          Parent root= null;
           root = FXMLLoader.load(getClass().getResource("/fxml/Vaga.fxml"));
       
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Vaga");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
      pane.getScene().getWindow().hide(); 

      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        InputStream is;
        try {
            is = Files.newInputStream(Paths.get("/home/padilha/Área de Trabalho/estacionamento/estacionamento.jpg"));
            Image img = new Image(is);
            is.close();
            ImageView imgView = new ImageView(img);
            DropShadow dropShadow = new DropShadow();
           imgView.setFitHeight(510);
           imgView.setFitWidth(900);
           
           
           imgView.setEffect(dropShadow);
          //  imgView.autosize();
            pane.getChildren().addAll(imgView);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
