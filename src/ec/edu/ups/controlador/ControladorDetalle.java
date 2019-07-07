/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Detalle;
import ec.edu.ups.modelo.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

/**
 *
 * @author Eduardo Ayora
 */
public class ControladorDetalle {
    
    private int codigo;
    private BaseDatos miBaseDatos;

    public ControladorDetalle() {
        miBaseDatos = new BaseDatos();
        obtenerCodigo();
    }

    public void obtenerCodigo(){
        String sql = "SELECT MAX(\"PRO_CODIGO\") FROM \"PRODUCTO\";";
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
    
    public void create(Detalle detalle) throws PSQLException{
        String sql = "INSERT INTO \"DETALLE\" VALUES(" + detalle.getCodigo()+ ", "
                + detalle.getPrecio()+ ", "
                + detalle.getCantidad()+ ", "
                + detalle.getSubtotal()+ ", "
                + detalle.getProducto().getCodigoProducto()
                + ");";

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
    
    public void delete(int codigo) {
        String sql = "DELETE FROM \"DETALLE\" WHERE \"DET_CODIGO\" = " + codigo + ";";
        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(Detalle detalle){
        String sql = "UPDATE \"DETALLE\" SET \"DET_PRECIO\" = " + detalle.getPrecio()+ ","
                + "\"DET_CANTIDAD\" = " + detalle.getCantidad()+ ","
                + "\"DET_SUBTOTAL\" = " + detalle.getSubtotal()
                + " WHERE \"DET_CODIGO\" = " + detalle.getCodigo() + ";";
        miBaseDatos.conectar();

        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Detalle read(int codigo) {
        Detalle detalle = new Detalle();
        try {
            String sql = "SELECT * FROM \"DETALLE\" WHERE \"DET_CODIGO\" = " + codigo + ";";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                detalle.setCodigo(codigo);
                detalle.setCantidad(rs.getInt("DET_CANTIDAD"));
                detalle.setPrecio(rs.getDouble("DET_PRECIO"));
                detalle.setSubtotal(rs.getInt("DET_SUBTOTAL"));
                detalle.setProducto(new ControladorProducto().read(rs.getInt("PRO_CODIGO")));
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return detalle;
    }
    
    public List<Detalle> listar(){
        List<Detalle> detalles = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM \"DETALLE\"";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Detalle detalle = new Detalle();
                detalle.setCodigo(rs.getInt("DET_CODIGO"));
                detalle.setCantidad(rs.getInt("DET_CANTIDAD"));
                detalle.setPrecio(rs.getDouble("DET_PRECIO"));
                detalle.setSubtotal(rs.getInt("DET_SUBTOTAL"));
                detalle.setProducto(new ControladorProducto().read(rs.getInt("PRO_CODIGO")));
                detalles.add(detalle);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return detalles;
    }
    
    
}
