/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Karen
 */
public class BaseDatos {

    private Connection conexionBD;

    public BaseDatos() {

    }

    public Connection getConexionBD() {
        return conexionBD;
    }

    public void conectar() {
        String url = "jdbc:postgresql://localhost:5432/SmartStore";
        String user = "postgres";
        String password = "chibi2998";
        try {
            conexionBD = DriverManager.getConnection(url, user, password);
            if (conexionBD.isValid(5000)) {
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void desconectar() {
        try {
            if (!conexionBD.isClosed()) {
                conexionBD.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
