/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

/**
 *
 * @author Eduardo Ayora
 */
public class Pin {
    
    private int codigo;
    private boolean activado;
    private Estante estante;

    public Pin(boolean activado) {
        this.activado = activado;
    }
    
    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }

    @Override
    public String toString() {
        return "Pin{" + "codigo=" + codigo + ", activado=" + activado + ", estante=" + estante + '}';
    }
    
    
    
}
