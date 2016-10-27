/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import MODEL.Vaga;
import com.jfoenix.controls.JFXTextField;
import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;

import static config.Config.i18n;

import static config.DAO.vagaRepository;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Jpadi
 */
public class VagaController implements Initializable {

    public char acao;

    public Vaga vaga;

 
    @FXML
    public TableView tblViewVaga;
    @FXML
    public Label lblAlteraVaga;
   
    @FXML
    public Label lblExcluirVaga;
    @FXML
    public AnchorPane paneCliente;
    @FXML
    public MaterialDesignIconView btnAlterar;
    @FXML
    public MaterialDesignIconView btnIncluir;
    @FXML
    public MaterialDesignIconView btnExcluir;

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        vaga = new Vaga();
        System.out.println("funfa essa bagaça");
       
        showCRUD();

    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        vaga = (Vaga) tblViewVaga.getSelectionModel().getSelectedItem();
        showCRUD();

    }

    @FXML
    private void acExcluir() {
        acao = EXCLUIR;
        vaga = (Vaga) tblViewVaga.getSelectionModel().getSelectedItem();
        showCRUD();

    }

    private void showCRUD() {   //constroi o popver
        String  cena = "/fxml/CRUDVaga.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena,"Inclusão de Vaga", btnIncluir);  // vincula no obj (icone) btnIncluir
                break;

            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Vaga", btnAlterar);  // vincula no obj (icone) btnAlterar

                break;

            case EXCLUIR:
                popOver = new XPopOver(cena, "Exclusão de Vaga", btnExcluir);  // vincula no obj (icone) btnAlterar

                break;
        }

        CRUDVagaController controller = popOver.getLoader().getController();  // pega do popOver, guarda no endereço
        controller.setCadastroControllerA(this);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblViewVaga.setItems(
                FXCollections.observableList(vagaRepository.findAll(new Sort(new Sort.Order("nome")))));
        
      
        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblViewVaga.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        lblAlteraVaga.visibleProperty().bind(btnAlterar.visibleProperty());
     lblExcluirVaga.visibleProperty().bind(btnAlterar.visibleProperty());

    }

}
