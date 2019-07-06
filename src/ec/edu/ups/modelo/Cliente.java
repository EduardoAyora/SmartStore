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
    private int edad;
    private Date fechaNacimiento;
    private String celular;
    private Double salario;

    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String apellido, int edad, Date fechaNacimiento, String celular, Double salario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {

        this.edad = edad;

    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", fechaNacimiento=" + fechaNacimiento + ", celular=" + celular + ", salario=" + salario + '}';
    }

}
