/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Eduardo Ayora
 */
public class Cliente {

    private String cedula;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String direccion;

    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String apellido, String celular, String correo, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {

        this.celular = celular;

    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {

        this.cedula = cedula;

    }

    public void setNombre(String nombre) {

        this.nombre = nombre;

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {

        this.apellido = apellido;

    }

    @Override
    public String toString() {
        return "Cliente{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + ", correo=" + correo + ", direccion=" + direccion + '}';
    }

    

}
