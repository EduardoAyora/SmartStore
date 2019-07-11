/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Pin;
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
public class ControladorPin {
    
    private int codigo;
    private BaseDatos miBaseDatos;

    public ControladorPin() {
        miBaseDatos = new BaseDatos();
    }
    
    
    
    public void create(Pin pin) throws PSQLException{
        String sql = "INSERT INTO \"PIN\" VALUES(" + pin.getCodigo() + ", "
                + pin.getEstante().getCodigo() + ");";

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
        String sql = "DELETE FROM \"PIN\" WHERE \"PIN_CODIGO\" = " + codigo + ";";
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

    public void update(Pin pin) {
        String sql = "UPDATE \"PIN\" SET \"EST_CODIGO\" = " + pin.getEstante().getCodigo()
                + " WHERE \"PIN_CODIGO\" = " + pin.getCodigo() + ";";
        miBaseDatos.conectar();

        try {
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            sta.execute(sql);
            miBaseDatos.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Pin> listar(){
        List<Pin> pines = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"PIN\"";
            System.out.println(sql);
            miBaseDatos.conectar();
            Statement sta = miBaseDatos.getConexionBD().createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()) {
                Pin pin = new Pin(false);
                pin.setCodigo(rs.getInt("PIN_CODIGO"));
                pin.setEstante(new ControladorEstante().read(rs.getInt("EST_CODIGO")));
                pines.add(pin);
            }
            rs.close();
            sta.close();
            miBaseDatos.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pines;
    }
    
}
