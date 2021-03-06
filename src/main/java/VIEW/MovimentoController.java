/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import MODEL.Movimento;
import MODEL.Preco;
import com.jfoenix.controls.JFXButton;
import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.Config.i18n;
import static config.DAO.movimentoRepository;
import static config.DAO.precoRepository;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.controlsfx.control.PopOver;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MovimentoController implements Initializable {

    public char acao;
    public Movimento movimento;

    @FXML
    public MaterialDesignIconView btnIncluir;

    @FXML
    public MaterialDesignIconView btnAlterar;

    @FXML
    public MaterialDesignIconView btnExcluir;

    @FXML
    private MenuItem mnIncluir;

    @FXML
    private MenuItem mnAlterar;

    @FXML
    private MenuItem mnExcluir;
    @FXML
    private TextField txtFldBusca;
    @FXML
    private JFXButton btnOutMov;
    @FXML
    private JFXButton btnExMov;
    @FXML
    public TableView tblViewMovimento;
    @FXML
    public AnchorPane paneCliente;

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        movimento = new Movimento();
        showCRUD();

    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        movimento = (Movimento) tblViewMovimento.getSelectionModel().getSelectedItem();
        showCRUD();

    }

    /* p/ alterar na hora de insert dá um save!
             ação alterar : new discipli não pode ser vazio, pegar o cara selecionado na tble view,
             qdo mandar p curd já vai estra selecionado.
     */
    @FXML
    private void acExcluir() {
        acao = EXCLUIR;
        movimento = (Movimento) tblViewMovimento.getSelectionModel().getSelectedItem();
        showCRUD();

    }
   

    @FXML
    public void btnInicioClick() throws IOException {
        Stage stage = new Stage();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));
        Scene scene = new Scene(root);

        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Inicio - PdzPark");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        paneCliente.getScene().getWindow().hide();

    }

    private void showCRUD() {   //constroi o popver
        String cena = "/fxml/CRUDMovimento.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Incluir Movimento", btnIncluir);  // vincula no obj (icone) btnIncluir
                break;

            case ALTERAR:
                popOver = new XPopOver(cena, "Inserir Saida", btnAlterar);  // vincula no obj (icone) btnAlterar

                break;

            case EXCLUIR:
                popOver = new XPopOver(cena, "Excluir Registro", btnExcluir);  // vincula no obj (icone) btnAlterar

                break;
        }

        CRUDMovimentoController controller = popOver.getLoader().getController();  // pega do popOver, guarda no endereço
        controller.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        tblViewMovimento.setItems(FXCollections.observableList(movimentoRepository.findAll()));

        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblViewMovimento.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        btnExMov.visibleProperty().bind(btnAlterar.visibleProperty());
        btnOutMov.visibleProperty().bind(btnAlterar.visibleProperty());

    }

}
