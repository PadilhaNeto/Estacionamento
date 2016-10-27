package VIEW;

import com.jfoenix.controls.JFXTextField;
import static config.Config.ALTERAR;

import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.Config.i18n;
import static config.DAO.vagaRepository;

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
public class CRUDVagaController implements Initializable {

    private VagaController controllerVaga;

    @FXML
    private JFXTextField txtFldStatus;
    @FXML
    private JFXTextField txtFldTipo;
    @FXML
    private Button btnCadastrar;

    @FXML
    private JFXTextField txtFldTam;

    @FXML
    private JFXTextField txtFldObs;

    @FXML
    private AnchorPane anchorPane;

//    @FXML
//    private TextField txtFldBusca;
    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerVaga.tblViewVaga.requestFocus();  // passar de um controlador pro outro
    }

    @FXML
    private void btnCadastraClick() {
        if(controllerVaga.acao==INCLUIR){
        controllerVaga.vaga.setNrVaga((int) vagaRepository.count() + 1);
        }
        controllerVaga.vaga.setStatus(txtFldStatus.getText());
        controllerVaga.vaga.setTipoVaga(txtFldTipo.getText());
        controllerVaga.vaga.setTamanho(txtFldTam.getText());
        controllerVaga.vaga.setObservacao(txtFldObs.getText());

        try {
            switch (controllerVaga.acao) {
                case INCLUIR:
                    vagaRepository.insert(controllerVaga.vaga);
                    break;

                case ALTERAR:
                    vagaRepository.save(controllerVaga.vaga);
                    break;

                case EXCLUIR:
                    vagaRepository.delete(controllerVaga.vaga);
                    break;
            }

            controllerVaga.tblViewVaga.setItems(
                    FXCollections.observableList(vagaRepository.findAll(new Sort(new Sort.Order("nome")))));
            // ajustar interface:
            controllerVaga.tblViewVaga.refresh();
            controllerVaga.tblViewVaga.getSelectionModel().clearSelection();
            controllerVaga.tblViewVaga.getSelectionModel().select(controllerVaga.vaga);  // só vai funcionar se na classe for construído o método equals (campo ID no nosso projeto)
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

    void setCadastroControllerA(VagaController controllerVaga) {
        this.controllerVaga = controllerVaga;

        txtFldStatus.setText(controllerVaga.vaga.getStatus());
        txtFldTipo.setText(controllerVaga.vaga.getTipoVaga());
        txtFldTam.setText(controllerVaga.vaga.getTamanho());
        txtFldObs.setText(controllerVaga.vaga.getObservacao());

        //
        txtFldStatus.setDisable(controllerVaga.acao == EXCLUIR);
        txtFldTipo.setDisable(controllerVaga.acao == EXCLUIR);
        txtFldTam.setDisable(controllerVaga.acao == EXCLUIR);
        txtFldObs.setDisable(controllerVaga.acao == EXCLUIR);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
