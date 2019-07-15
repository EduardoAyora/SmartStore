/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Factura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;

/**
 *
 * @author Eduardo Ayora
 */
public class ControladorFactura {

    private BaseDatos miBaseDatos;
    private int codigo;
    
    public ControladorFactura() {
        miBaseDatos = new BaseDatos();
        codigo = getCodigo();
    }
    
    public int getCodigo(){
        int codigoFactura = 0;
        String sql = "SELECT MAX(\"FAC_CODIGO\") FROM \"FACTURA\";";
        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                codigoFactura = rs.getInt(1);
                codigoFactura++;
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return codigoFactura;
    }

    public void create(Factura factura) throws PSQLException{
        Format formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = formato.format(factura.getFecha());
        String sql = "INSERT INTO \"FACTURA\" VALUES(" + codigo + ", '"
                + fecha + "', '"
                + factura.getCliente().getCedula() + "', "
                + factura.getSubtotal() + ", "
                + factura.getIva()+ ", "
                + factura.getTotal()+ ", "
                + factura.isActivo()+ ");";
        codigo++;
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
    
    public void anular(int codigo) {
        String sql = "UPDATE \"FACTURA\" SET \"FAC_ACTIVO\" = false WHERE \"FAC_CODIGO\" = " + codigo + ";";
        miBaseDatos.conectar();
        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Factura read(int codigo) throws PSQLException{
        Factura factura = new Factura();
        try {
            String sql = "SELECT * FROM \"FACTURA\" WHERE \"FAC_CODIGO\" = " + codigo + ";";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            if (rs.next()) {
                factura.setNumeroFactura(codigo);
                factura.setFecha(rs.getDate("FAC_FECHA"));
                factura.setCliente(new ControladorCliente().findByCedula(rs.getString("CLI_CEDULA")));
                factura.setSubtotal(rs.getDouble("FAC_SUBTOTAL"));
                factura.setIva(rs.getDouble("FAC_IVA"));
                factura.setTotal(rs.getDouble("FAC_TOTAL"));
                factura.setActivo(rs.getBoolean("FAC_ACTIVO"));
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
            ServerErrorMessage serverError = new ServerErrorMessage("");
            throw new PSQLException(serverError);
        }
        return factura;
    }


    public List<Factura> listar(){
        List<Factura> facturas = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM \"FACTURA\"";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setNumeroFactura(rs.getInt("FAC_CODIGO"));
                factura.setFecha(rs.getDate("FAC_FECHA"));
                factura.setCliente(new ControladorCliente().findByCedula(rs.getString("CLI_CEDULA")));
                factura.setSubtotal(rs.getDouble("FAC_SUBTOTAL"));
                factura.setIva(rs.getDouble("FAC_IVA"));
                factura.setTotal(rs.getDouble("FAC_TOTAL"));
                factura.setActivo(rs.getBoolean("FAC_ACTIVO"));
                facturas.add(factura);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return facturas;
    }
    
}
