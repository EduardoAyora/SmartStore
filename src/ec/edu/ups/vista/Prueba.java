/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.controlador.BaseDatos;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Karen
 */
public class Prueba {
    
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/SmartStore";
        String user = "postgres";
        String password = "chibi2998";
        try {
            BaseDatos baseDatos = new BaseDatos();
            baseDatos.conectar();
            File reporteArchivo = new File("src/ec/edu/ups/reporte/factura.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(reporteArchivo);
            Map parametro = new HashMap();
            int factura = 3;
            //Puse el parametro CEDULA porque lo llame de la misma forma en el .jrxml - REPPORT INSPECTOR - PARAMETERS
            //El resto de codigo está en la sentencia sql
            parametro.put("FACTURA", factura);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, baseDatos.getConexionBD());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "reporteDireccion.pdf");
            JasperViewer.viewReport(jasperPrint);
            baseDatos.desconectar();
        } catch (JRException ex) {
            ex.printStackTrace();
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
