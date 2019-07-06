/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.Objects;
import javax.swing.ImageIcon;

/**
 *
 * @author Eduardo Ayora
 */
public class Producto {

    private int codigoProducto;
    private String nombre;
    private double precio;
    private String descripcion;
    private ImageIcon imgIcon;

    public Producto() {
        imgIcon = new ImageIcon();
    }

    public Producto(String nombre, int precio, String marca, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigo) {
        this.codigoProducto = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDetalle() {
        return descripcion;
    }

    public void setDetalle(String detalle) {
        this.descripcion = detalle;
    }

    public ImageIcon getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(ImageIcon imgIcon) {
        this.imgIcon = imgIcon;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.codigoProducto;
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
        final Producto other = (Producto) obj;
        if (this.codigoProducto != other.codigoProducto) {
            return false;
        }
        return true;
    }

}
