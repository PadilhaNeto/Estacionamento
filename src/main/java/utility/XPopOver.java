/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import static config.Config.i18n;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.controlsfx.control.PopOver;


public class XPopOver {
    
    private FXMLLoader loader;
    
    public XPopOver(String arquivoFXML, String titulo, Node node) {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(arquivoFXML));
            loader.setResources(i18n);
            PopOver popOverCRUD = new PopOver();
            popOverCRUD.setContentNode( loader.load());
            popOverCRUD.setAutoFix(true);
            popOverCRUD.setAutoHide(true);
            popOverCRUD.setHideOnEscape(true);
            popOverCRUD.setHeaderAlwaysVisible(true);
            popOverCRUD.setArrowLocation(PopOver.ArrowLocation.TOP_LEFT);
            popOverCRUD.setTitle(titulo);
            popOverCRUD.show(node);
        } catch (IOException ex) {
            Logger.getLogger(XPopOver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FXMLLoader getLoader() {
        return loader;
    }
    
    
}
