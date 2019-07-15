/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

/**
 *
 * @author Karen
 */
public class ControladorCliente{

    private BaseDatos miBaseDatos;

    public ControladorCliente() {
        miBaseDatos = new BaseDatos();
    }

    public void create(Cliente persona) throws PSQLException{
        String sql = "INSERT INTO \"CLIENTE\" VALUES('" + persona.getCedula() + "', '"
                + persona.getNombre() + "', '"
                + persona.getApellido() + "', '"
                + persona.getCelular()+ "', '"
                + persona.getCorreo() + "', '" 
                + persona.getDireccion()+ "', '"
                + persona.getCodigoTarjeta()+ "');";

        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
    }

    public void delete(String cedula) throws PSQLException{
        String sql = "DELETE FROM \"CLIENTE\" WHERE \"CLI_CEDULA\" = '" + cedula + "';";
        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
    }

    public void update(Cliente persona) {
        String sql = "UPDATE \"CLIENTE\" SET \"CLI_NOMBRE\" = '" + persona.getNombre() + "',"
                + "\"CLI_APELLIDO\" = '" + persona.getApellido() + "',"
                + "\"CLI_CELULAR\" = '" + persona.getCelular() + "',"
                + "\"CLI_CORREO\" = '" + persona.getCorreo() + "',"
                + "\"CLI_DIRECCION\" = '" + persona.getCorreo() + "', '"
                + "\"CLI_TARJETA\" = '" + persona.getCodigoTarjeta()
                + " WHERE \"CLI_CEDULA\" = '" + persona.getCedula() + "';";
        miBaseDatos.conectar();

        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Cliente findByCedula(String cedula) throws PSQLException{
        Cliente persona = new Cliente();
        try {
            String sql = "SELECT * FROM \"CLIENTE\" WHERE \"CLI_CEDULA\" = '" + cedula + "';";
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                persona.setCedula(cedula);
                persona.setNombre(rs.getString("CLI_NOMBRE"));
                persona.setApellido(rs.getString("CLI_APELLIDO"));
                persona.setCelular(rs.getString("CLI_CELULAR"));
                persona.setCorreo(rs.getString("CLI_CORREO"));
                persona.setDireccion(rs.getString("CLI_DIRECCION"));
                persona.setCodigoTarjeta(rs.getString("CLI_TARJETA"));
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
        return persona;
    }
    
    public Cliente findByTarjeta(String tarjeta) throws PSQLException{
        Cliente persona = new Cliente();
        try {
            String sql = "SELECT * FROM \"CLIENTE\" WHERE \"CLI_TARJETA\" = '" + tarjeta + "';";
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                persona.setCedula(rs.getString("CLI_CEDULA"));
                persona.setNombre(rs.getString("CLI_NOMBRE"));
                persona.setApellido(rs.getString("CLI_APELLIDO"));
                persona.setCelular(rs.getString("CLI_CELULAR"));
                persona.setCorreo(rs.getString("CLI_CORREO"));
                persona.setDireccion(rs.getString("CLI_DIRECCION"));
                persona.setCodigoTarjeta(tarjeta);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
        return persona;
    }
    
    public List<Cliente> listar(){
        List<Cliente> personas = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM \"PERSONA\"";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Cliente persona = new Cliente();
                persona.setCedula(rs.getString("CLI_CEDULA"));
                persona.setNombre(rs.getString("CLI_NOMBRE"));
                persona.setApellido(rs.getString("CLI_APELLIDO"));
                persona.setCelular(rs.getString("CLI_CELULAR"));
                persona.setCorreo(rs.getString("CLI_CORREO"));
                persona.setDireccion(rs.getString("CLI_DIRECCION"));
                persona.setCodigoTarjeta(rs.getString("CLI_TARJETA"));
                personas.add(persona);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return personas;
    }

}
