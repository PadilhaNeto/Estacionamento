/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import static config.Config.df;
import static config.Config.nfc;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author User
 */
@Document
public class Movimento {

    @Id
    private String id;

    @Indexed(unique = true)
    private Date horaEntrada;
    @Indexed(unique = true)
    private int codMovimento;

    private Date horaSaida;
    @DBRef   //relaciona com a outra tabela
    private Veiculo veiculo;
    @DBRef
    private Vaga vaga;

    private double valor;

    private String observacao;

    public Movimento() {
    }

    public int getCodMovimento() {
        return codMovimento;
    }

    public void setCodMovimento(int codMovimento) {
        this.codMovimento = codMovimento;
    }

    public Movimento(int codMovimento, Date horaEntrada, Date horaSaida, Veiculo veiculo, Vaga vaga) {
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.codMovimento = codMovimento;
    }

    public Movimento(int codMovimento, Date horaEntrada, Veiculo veiculo, Vaga vaga) {
        this.horaEntrada = horaEntrada;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.codMovimento = codMovimento;
    }

    public Movimento(Date horaEntrada, Date horaSaida, Veiculo veiculo, Vaga vaga, String observacao) {
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.observacao = observacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Movimento(Date horaEntrada, Date horaSaida) {
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public Date getHoraEntrada() {

        return horaEntrada;

    }

    public double getValor() {
        return valor;
    }

    public String getValorFormatado() {
        return nfc.format(valor);
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormatHoraEntrada() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (horaEntrada != null) {
            return sdf.format(horaEntrada);
        } else {
            return "";
        }
    }

    public String getFormatHoraSaida() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (horaSaida != null) {
            return sdf.format(horaSaida);
        } else {
            return "";
        }
    }

    public void setHoraEntrada(Date horaEntrada) {

        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Movimento{" + "horaEntrada=" + horaEntrada + ", horaSaida=" + horaSaida + ", veiculo=" + veiculo + '}';
    }

}
