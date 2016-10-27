/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author padilha
 */
public class Preco {
    
    private double hora;
    private double meiaHora;
    private double diaria;
    private double mensal;

    public Preco() {
    }

    public Preco(double hora, double meiaHora, double diaria, double mensal) {
        this.hora = hora;
        this.meiaHora = meiaHora;
        this.diaria = diaria;
        this.mensal = mensal;
    }

    public double getHora() {
        return hora;
    }

    public void setHora(double hora) {
        this.hora = hora;
    }

    public double getMeiaHora() {
        return meiaHora;
    }

    public void setMeiaHora(double meiaHora) {
        this.meiaHora = meiaHora;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public double getMensal() {
        return mensal;
    }

    public void setMensal(double mensal) {
        this.mensal = mensal;
    }
    
    
    
    
    
}
