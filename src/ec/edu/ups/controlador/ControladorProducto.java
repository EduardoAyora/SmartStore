/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Producto;
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
public class ControladorProducto {

    private BaseDatos miBaseDatos;
    private int codigo;

    
    
    public ControladorProducto() {
        miBaseDatos = new BaseDatos();
        obtenerCodigo();
    }

    public int getCodigo() {
        return codigo;
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

    public void create(Producto producto) throws PSQLException{
        String sql = "INSERT INTO \"PRODUCTO\" VALUES(" + producto.getCodigoProducto() + ", '"
                + producto.getNombre()+ "', "
                + producto.getPrecio() + ", '"
                + producto.getDescripcion() + "');";

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

    public void delete(int codigo) throws PSQLException{
        String sql = "DELETE FROM \"PRODUCTO\" WHERE \"PRO_CODIGO\" = " + codigo + ";";
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
    
    public void update(Producto producto) {
        String sql = "UPDATE \"PRODUCTO\" SET \"PRO_NOMBRE\" = '" + producto.getNombre() + "',"
                + "\"PRO_PRECIO\" = " + producto.getPrecio() + ","
                + "\"PRO_DESCRIPCION\" = '" + producto.getDescripcion() + "'"
                + " WHERE \"PRO_CODIGO\" = " + producto.getCodigoProducto() + ";";
        miBaseDatos.conectar();

        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Producto read(int codigo) throws PSQLException{
        Producto producto = new Producto();
        try {
            String sql = "SELECT * FROM \"PRODUCTO\" WHERE \"PRO_CODIGO\" = " + codigo + ";";
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                producto.setCodigoProducto(codigo);
                producto.setNombre(rs.getString("PRO_NOMBRE"));
                producto.setPrecio(rs.getDouble("PRO_PRECIO"));
                producto.setDescripcion(rs.getString("PRO_DESCRIPCION"));
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
        return producto;
    }


    public List<Producto> listar(){
        List<Producto> productos = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM \"PRODUCTO\"";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setCodigoProducto(rs.getInt("PRO_CODIGO"));
                producto.setNombre(rs.getString("PRO_NOMBRE"));
                producto.setPrecio(rs.getDouble("PRO_PRECIO"));
                producto.setDescripcion(rs.getString("PRO_DESCRIPCION"));
                productos.add(producto);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return productos;
    }

}
