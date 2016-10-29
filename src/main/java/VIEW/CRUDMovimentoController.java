package VIEW;

import MODEL.Vaga;
import MODEL.Veiculo;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static config.Config.ALTERAR;
import static config.Config.df;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.Config.i18n;
import static config.DAO.movimentoRepository;
import static config.DAO.vagaRepository;
import static config.DAO.veiculoRepository;
import java.net.URL;
import java.text.SimpleDateFormat;
import static java.time.LocalDate.now;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import org.springframework.data.domain.Sort;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CRUDMovimentoController implements Initializable {

    private MovimentoController controllerMain;

    @FXML
    private JFXTextField txtFldHoraSaida;
    @FXML
    private Button btnConfirma;

    @FXML
    private JFXTextField txtFldHoraEntrada;

    @FXML
    private JFXTextField txtFldObs;

    @FXML
    private JFXComboBox cmbVeiculo;
    @FXML
    private JFXComboBox cmbVaga;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerMain.tblViewMovimento.requestFocus();
    }

    @FXML
    private void btnConfirmaClick() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Date hora = new Date();
        hora.getTime();

        System.out.println(sdf.format(hora));

        if (controllerMain.acao == INCLUIR) {
            controllerMain.movimento.setCodMovimento((int) (movimentoRepository.count() + 1));
            controllerMain.movimento.setHoraEntrada(hora);

        }

        controllerMain.movimento.setVaga((Vaga) cmbVaga.getSelectionModel().getSelectedItem());
        controllerMain.movimento.setVeiculo((Veiculo) cmbVeiculo.getSelectionModel().getSelectedItem());        
        controllerMain.movimento.setObservacao(txtFldObs.getText());
        

        try {
            switch (controllerMain.acao) {
                case INCLUIR:
                    movimentoRepository.insert(controllerMain.movimento);
                    break;

                case ALTERAR:
                    movimentoRepository.save(controllerMain.movimento);
                    break;

                case EXCLUIR:
                    movimentoRepository.delete(controllerMain.movimento);
                    break;
            }

            controllerMain.tblViewMovimento.setItems(
                    FXCollections.observableList(movimentoRepository.findAll()));
            // ajustar interface:
            controllerMain.tblViewMovimento.refresh();
            controllerMain.tblViewMovimento.getSelectionModel().clearSelection();
            controllerMain.tblViewMovimento.getSelectionModel().select(controllerMain.movimento);  // só vai funcionar se na classe for construído o método equals (campo ID no nosso projeto)
            anchorPane.getScene().getWindow().hide();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("alertTitle.text");
            alert.setHeaderText("alertHMovimento.text");
            if (e.getMessage().contains("duplicate key")) {
                alert.setContentText("alertCMovimento.text");
            } else {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();
            System.out.println("Nosso erro: " + e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbVeiculo.setItems(
                FXCollections.observableList(veiculoRepository.findAll()));
        cmbVaga.setItems(
                FXCollections.observableList(vagaRepository.findByStatusLikeIgnoreCase("Livre")));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        Date hora = new Date();
        hora.getTime();

        txtFldHoraEntrada.setText(sdf.format(hora));

        txtFldHoraSaida.setDisable(true);

    }

    void setCadastroController(MovimentoController controllerMain) {
        this.controllerMain = controllerMain;

        if (controllerMain.acao == ALTERAR) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date hora = new Date();
            hora.getTime();

            txtFldHoraSaida.setText(sdf.format(hora));
            txtFldHoraEntrada.setText(controllerMain.movimento.getFormatHoraEntrada());
            txtFldHoraEntrada.setDisable(true);
            controllerMain.movimento.setHoraSaida(hora);
        }
        txtFldObs.setText(controllerMain.movimento.getObservacao());

        if (controllerMain.acao != INCLUIR) {
            cmbVeiculo.getSelectionModel().select(controllerMain.movimento.getVeiculo());
            cmbVaga.getSelectionModel().select(controllerMain.movimento.getVaga());
        }
        if (controllerMain.acao == EXCLUIR) {
            txtFldHoraEntrada.setText(controllerMain.movimento.getFormatHoraEntrada());
            txtFldHoraSaida.setText(controllerMain.movimento.getFormatHoraSaida());
        }

        if (controllerMain.acao == ALTERAR) {

            double hora = 4.00, horaAdicional = 3.00, meiaHra = 2.00, valor = 0.00;
            SimpleDateFormat formatHora = new SimpleDateFormat("HH");
            String hraEntrada = formatHora.format(controllerMain.movimento.getHoraEntrada());

            SimpleDateFormat formatMin = new SimpleDateFormat("mm");

            String minEntrada = formatMin.format(controllerMain.movimento.getHoraEntrada());

            System.out.println("Minutos da entrada [ " + minEntrada);
            System.out.println("Hora da entrada [ " + hraEntrada);

            String horaSaida = formatHora.format(new Date());
            String minSaida = formatMin.format(new Date());

            int horaFinal = Integer.parseInt(horaSaida) - Integer.parseInt(hraEntrada);
            int aux = horaFinal;
            System.out.println("diferença de horas  [ " + horaFinal);

            int minFinal = Integer.parseInt(minSaida) - Integer.parseInt(minEntrada);
            System.out.println(" diferença de minutos [ " + minFinal);
            if (minFinal < 0) {
                double hra = minFinal + 60;
                System.out.println("minutos para calculo [ " + hra);
                if (hra <= 30) {
                    valor = meiaHra;
                    controllerMain.movimento.setValor(valor);
                } else {
                    valor = hora * aux;
                    controllerMain.movimento.setValor(valor);
                }
                System.out.println("Valor a pagar " + valor);

            } else {

                horaFinal = horaFinal * 60;
                System.out.println(horaFinal);
                double hraTotal = horaFinal + minFinal;
                System.out.println(hraTotal);
                if (hraTotal <= 30) {
                    valor = meiaHra;
                    controllerMain.movimento.setValor(valor);
                } else if (hraTotal > 30 && hraTotal <= 1) {
                    valor = hora;
                    controllerMain.movimento.setValor(valor);
                } else if (horaFinal > 1) {
                    valor = hora + horaAdicional * (aux - 1);
                    controllerMain.movimento.setValor(valor);
                }
                System.out.println("Valor com Adicional   " + valor);

            }

        }

        txtFldHoraEntrada.setDisable(controllerMain.acao == INCLUIR);
        txtFldHoraSaida.setDisable(controllerMain.acao == INCLUIR);

        txtFldHoraEntrada.setDisable(controllerMain.acao == EXCLUIR);
        txtFldHoraSaida.setDisable(controllerMain.acao == EXCLUIR);
        txtFldObs.setDisable(controllerMain.acao == EXCLUIR);
        cmbVaga.setDisable(controllerMain.acao == EXCLUIR);
        cmbVeiculo.setDisable(controllerMain.acao == EXCLUIR);

    }

}
