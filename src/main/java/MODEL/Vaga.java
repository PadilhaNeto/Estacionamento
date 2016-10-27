/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import static config.Config.df;

import java.time.LocalDate;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author User
 */

@Document
public class Vaga {

    @Id
    private String id;

    @Indexed(unique = true)
    private int nrVaga;
    private String status;
    private String tipoVaga;
    private String tamanho;
    private String observacao;
    

    public Vaga() {
    }

    public Vaga(int nrVaga, String status) {
        this.nrVaga = nrVaga;
        this.status = status;
    }

    public Vaga(int nrVaga, String status, String tipoVaga, String tamanho, String observacao) {
        this.nrVaga = nrVaga;
        this.status = status;
        this.tipoVaga = tipoVaga;
        this.tamanho = tamanho;
        this.observacao = observacao;
    }
    
    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    

    public int getNrVaga() {
        return nrVaga;
    }

    public void setNrVaga(int nrVaga) {
        this.nrVaga = nrVaga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  Integer.toString(nrVaga);
    }
    
    
    
   
    
    
}