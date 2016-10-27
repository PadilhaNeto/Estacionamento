
package VIEW;


import com.jfoenix.controls.JFXTextField;
import static config.Config.ALTERAR;

import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.Config.i18n;
import static config.DAO.clienteRepository;


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
public class CRUDClienteController implements Initializable {

    private ClienteController controllerCliente;

    @FXML
    private JFXTextField txtFldCodCliente;

    @FXML
    private JFXTextField txtFldCpf;
    @FXML
    private JFXTextField txtFldRg;
    @FXML
    private Button btnCadastrar;

    @FXML
    private JFXTextField txtFldNome;

    @FXML
    private JFXTextField txtFldEstado;

    @FXML
    private JFXTextField txtFldCidade;

    @FXML
    private AnchorPane anchorPane;



//    @FXML
//    private TextField txtFldBusca;



    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerCliente.tblViewCliente.requestFocus();  // passar de um controlador pro outro
    }
   
     

    @FXML
    private void btnCadastraClick() {

        

            controllerCliente.cliente.setCodCliente((int) clienteRepository.count()+1);  
            controllerCliente.cliente.setNome(txtFldNome.getText());
            controllerCliente.cliente.setRg(txtFldRg.getText());
            controllerCliente.cliente.setCpf(txtFldCpf.getText());

            controllerCliente.cliente.setCidade(txtFldCidade.getText());
            controllerCliente.cliente.setEstado(txtFldEstado.getText());
            
           

            try {
                switch (controllerCliente.acao) {
                    case INCLUIR:
                        clienteRepository.insert(controllerCliente.cliente);
                        break;

                    case ALTERAR:
                        clienteRepository.save(controllerCliente.cliente);
                        break;

                    case EXCLUIR:
                        clienteRepository.delete(controllerCliente.cliente);
                        break;
                }

                controllerCliente.tblViewCliente.setItems(
                        FXCollections.observableList(clienteRepository.findAll(new Sort(new Sort.Order("nome")))));
                // ajustar interface:
                controllerCliente.tblViewCliente.refresh();
                controllerCliente.tblViewCliente.getSelectionModel().clearSelection();
                controllerCliente.tblViewCliente.getSelectionModel().select(controllerCliente.cliente);  // só vai funcionar se na classe for construído o método equals (campo ID no nosso projeto)
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

    void setCadastroControllerA(ClienteController controllerCliente) {
        this.controllerCliente = controllerCliente;

      
        txtFldNome.setText(controllerCliente.cliente.getNome());
        txtFldRg.setText(controllerCliente.cliente.getRg());
        txtFldCpf.setText(controllerCliente.cliente.getCpf());
        txtFldCidade.setText(controllerCliente.cliente.getCidade());
        txtFldEstado.setText(controllerCliente.cliente.getEstado());
        
       //

    
        txtFldNome.setDisable(controllerCliente.acao == EXCLUIR);
      txtFldRg.setDisable(controllerCliente.acao == EXCLUIR);
        txtFldCpf.setDisable(controllerCliente.acao == EXCLUIR);
        txtFldCidade.setDisable(controllerCliente.acao == EXCLUIR);
        txtFldEstado.setDisable(controllerCliente.acao == EXCLUIR);
     
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
