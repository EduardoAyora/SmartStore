/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import ec.edu.ups.controlador.ControladorDetalle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eduardo Ayora
 */
public class Factura {

    private int numeroFactura;
    private Date fecha;
    private Cliente cliente;
    private double subtotal;
    private double iva;
    private double total;
    private boolean activo;
    private long tiempoEspera;
    private List<Detalle> detalles;

    public Factura() {
        tiempoEspera = System.currentTimeMillis();
        activo = true;
        fecha = new Date();
        detalles = new ArrayList<>();
    }

    public Factura(int numeroFactura, Date fecha, Cliente cliente, double subtotal, double iva, double total) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public long getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(long tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.numeroFactura;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factura other = (Factura) obj;
        if (this.numeroFactura != other.numeroFactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Factura{" 
                + "numeroFactura=" + numeroFactura 
                + ", fecha=" + fecha 
                + ", cliente=" + cliente.getNombre() 
                + ", subtotal=" + subtotal 
                + ", iva=" + iva 
                + ", total=" + total 
                + ", activo=" + activo 
                + ", detalles=" + detalles + '}';
    }
    
}
