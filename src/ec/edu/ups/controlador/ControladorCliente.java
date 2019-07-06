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
        Format formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(persona.getFechaNacimiento());
        String sql = "INSERT INTO \"PERSONA\" VALUES('" + persona.getCedula() + "', '"
                + persona.getNombre() + "', '"
                + persona.getApellido() + "', "
                + persona.getEdad() + ", '"
                + fecha + "', '" + persona.getCelular() + "'," + persona.getSalario() + ");";

        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
    }

    public void delete(String cedula) {
        String sql = "DELETE FROM \"PERSONA\" WHERE \"PER_CEDULA\" = '" + cedula + "';";
        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Cliente persona) {
        Format formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(persona.getFechaNacimiento());
        String sql = "UPDATE \"PERSONA\" SET \"PER_NOMBRES\" = '" + persona.getNombre() + "',"
                + "\"PER_APELLIDOS\" = '" + persona.getApellido() + "',"
                + "\"PER_EDAD\" = " + persona.getEdad() + ","
                + "\"PER_FECHA_NACIMIENTO\" = '" + fecha + "',"
                + "\"PER_CELULAR\" = '" + persona.getCelular() + "',"
                + "\"PER_SALARIO\" = " + persona.getSalario()
                + "WHERE \"PER_CEDULA\" = '" + persona.getCedula() + "';";
        miBaseDatos.conectar();

        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Cliente findByCedula(String cedula) {
        Cliente persona = new Cliente();
        try {
            String sql = "SELECT * FROM \"PERSONA\" WHERE \"PER_CEDULA\" = '" + cedula + "';";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                persona.setCedula(cedula);
                persona.setNombre(rs.getString("PER_NOMBRES"));
                persona.setApellido(rs.getString("PER_APELLIDOS"));
                persona.setEdad(rs.getInt("PER_EDAD"));
                persona.setFechaNacimiento(rs.getDate("PER_FECHA_NACIMIENTO"));
                persona.setCelular(rs.getString("PER_CELULAR"));
                persona.setSalario(rs.getDouble("PER_SALARIO"));
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return persona;
    }
    
    /*
    public List<Direccion> listarDirecciones(String cedula){
        List<Direccion> direcciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"DIRECCION\" WHERE \"PER_CEDULA\" = '" + cedula + "';";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Direccion direccion = new Direccion();
                direccion.setCodigo(rs.getInt("DIR_CODIGO"));
                direccion.setCallePrincipal(rs.getString("DIR_CALLE_PRINCIPAL"));
                direccion.setCalleSecundaria(rs.getString("DIR_CALLE_SECUNDARIA"));
                direccion.setNumero(rs.getInt("DIR_NUMERO"));
                direccion.setCedulaPersona(rs.getString("PER_CEDULA"));
                direcciones.add(direccion);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return direcciones;
    }*/
    
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
                persona.setCedula(rs.getString("PER_CEDULA"));
                persona.setNombre(rs.getString("PER_NOMBRES"));
                persona.setApellido(rs.getString("PER_APELLIDOS"));
                persona.setEdad(rs.getInt("PER_EDAD"));
                persona.setFechaNacimiento(rs.getDate("PER_FECHA_NACIMIENTO"));
                persona.setCelular(rs.getString("PER_CELULAR"));
                persona.setSalario(rs.getDouble("PER_SALARIO"));
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
