/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Estante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

/**
 *
 * @author Eduardo Ayora
 */
public class ControladorEstante {
    
    private int codigo;
    private BaseDatos miBaseDatos;

    public ControladorEstante() {
        miBaseDatos = new BaseDatos();
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void obtenerCodigo(){
        String sql = "SELECT MAX(\"EST_CODIGO\") FROM \"ESTANTE\";";
        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                codigo = rs.getInt(1);
                codigo++;
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void create(Estante estante) throws PSQLException{
        String sql = "INSERT INTO \"ESTANTE\" VALUES(" + codigo + ", "
                + estante.getProducto().getCodigoProducto() 
                + estante.getCantidad() + ");";

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
    
    public void delete(int codigo) throws PSQLException {
        String sql = "DELETE FROM \"ESTANTE\" WHERE \"EST_CODIGO\" = " + codigo + ";";
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

    public void update(Estante estante) {
        String sql = "UPDATE \"ESTANTE\" SET \"PRO_CODIGO\" = " + estante.getProducto().getCodigoProducto()
                + ", \"EST_CANTIDAD\" = " + estante.getCantidad()
                + " WHERE \"EST_CODIGO\" = " + estante.getCodigo() + ";";
        miBaseDatos.conectar();

        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Estante read(int codigo) throws PSQLException{
        Estante estante = new Estante();
        
        try {
            String sql = "SELECT * FROM \"ESTANTE\" WHERE \"EST_CODIGO\" = " + codigo + ";";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                estante.setCodigo(rs.getInt("EST_CODIGO"));
                estante.setProducto(new ControladorProducto().read(rs.getInt("PRO_CODIGO")));
                estante.setCantidad(rs.getInt("EST_CANTIDAD"));
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
        
        return estante;
    }
    
    public List<Estante> listar(){
        List<Estante> estantes = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM \"ESTANTE\"";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Estante estante = new Estante();
                estante.setCodigo(rs.getInt("EST_CODIGO"));
                estante.setProducto(new ControladorProducto().read(rs.getInt("PRO_CODIGO")));
                estante.setCantidad(rs.getInt("EST_CANTIDAD"));
                estantes.add(estante);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return estantes;
    }
    
    
}
