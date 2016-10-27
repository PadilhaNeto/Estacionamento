
package VIEW;


import com.jfoenix.controls.JFXTextField;
import static config.Config.ALTERAR;

import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.Config.i18n;
import static config.DAO.veiculoRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import org.springframework.data.domain.Sort;

/**
 *
 * @author User
 */
public class CRUDVeiculoController implements Initializable {

    private VeiculoController controllerVeiculo;

    @FXML
    private JFXTextField txtFldPlaca;

    @FXML
    private JFXTextField txtFldMarca;
    @FXML
    private JFXTextField txtFldModelo;
    @FXML
    private Button btnCadastrar;

    @FXML
    private JFXTextField txtFldCor;

    @FXML
    private JFXTextField txtFldObservacao;

  

    @FXML
    private AnchorPane anchorPane;



//    @FXML
//    private TextField txtFldBusca;



    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerVeiculo.tblViewVeiculo.requestFocus();  // passar de um controlador pro outro
    }
   
     

    @FXML
    private void btnCadastraClick() {

        

            
            controllerVeiculo.veiculo.setPlaca(txtFldPlaca.getText());
            controllerVeiculo.veiculo.setMarca(txtFldMarca.getText());
            controllerVeiculo.veiculo.setModelo(txtFldModelo.getText());

            controllerVeiculo.veiculo.setCor(txtFldCor.getText());
            controllerVeiculo.veiculo.setObservacao(txtFldObservacao.getText());
            
           

            try {
                switch (controllerVeiculo.acao) {
                    case INCLUIR:
                        veiculoRepository.insert(controllerVeiculo.veiculo);
                        break;

                    case ALTERAR:
                        veiculoRepository.save(controllerVeiculo.veiculo);
                        break;

                    case EXCLUIR:
                        veiculoRepository.delete(controllerVeiculo.veiculo);
                        break;
                }

                controllerVeiculo.tblViewVeiculo.setItems(
                        FXCollections.observableList(veiculoRepository.findAll(new Sort(new Sort.Order("nome")))));
                // ajustar interface:
                controllerVeiculo.tblViewVeiculo.refresh();
                controllerVeiculo.tblViewVeiculo.getSelectionModel().clearSelection();
                controllerVeiculo.tblViewVeiculo.getSelectionModel().select(controllerVeiculo.veiculo);  // só vai funcionar se na classe for construído o método equals (campo ID no nosso projeto)
                anchorPane.getScene().getWindow().hide();

            } catch (Exception e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" ");
                alert.setHeaderText(" ");
                if (e.getMessage().contains("duplicate key")) {
                    alert.setContentText("");
                } else {
                    alert.setContentText(e.getMessage());
                }
                alert.showAndWait();
                System.out.println("Nosso erro: " + e.getMessage());
            }

        
    }

    void setCadastroControllerA(VeiculoController controllerVeiculo) {
        this.controllerVeiculo = controllerVeiculo;

      
        txtFldPlaca.setText(controllerVeiculo.veiculo.getPlaca());
        txtFldMarca.setText(controllerVeiculo.veiculo.getMarca());
        txtFldModelo.setText(controllerVeiculo.veiculo.getModelo());
        txtFldCor.setText(controllerVeiculo.veiculo.getCor());
        txtFldObservacao.setText(controllerVeiculo.veiculo.getObservacao());
        
       //

    
        txtFldPlaca.setDisable(controllerVeiculo.acao == EXCLUIR);
      txtFldMarca.setDisable(controllerVeiculo.acao == EXCLUIR);
        txtFldModelo.setDisable(controllerVeiculo.acao == EXCLUIR);
        txtFldCor.setDisable(controllerVeiculo.acao == EXCLUIR);
        txtFldObservacao.setDisable(controllerVeiculo.acao == EXCLUIR);
     
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
