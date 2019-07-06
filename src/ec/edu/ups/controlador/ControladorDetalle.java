/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Detalle;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Eduardo Ayora
 */
public class ControladorDetalle {
    
    private int contador;
    private SortedSet<Detalle> lista;

    public ControladorDetalle() {
        contador = 1;
        lista = new TreeSet<>();
    }

    public SortedSet<Detalle> getLista() {
        return lista;
    }
    
    public void createFacturaDetalle(Detalle detalle){
        detalle.setCodigo(contador);
        contador++;
        lista.add(detalle);
    }
    
    public Detalle readFacturaDetalle(int codigo){
        for (Detalle detalle : lista) {
            if(detalle.getCodigo() == codigo){
                return detalle;
            }
        }
        return null;
    }
    
    public void updateDetalle(Detalle detalle){
        if(lista.contains(detalle)){
            lista.remove(detalle);
            lista.add(detalle);
        }
    }
    
    public void deleteFacturaDetalle(int codigo){
        for(Detalle detalle : lista){
            if(detalle.getCodigo() == codigo){
                lista.remove(detalle);
                break;
            }
        }
    }
    
    public void quitarDetalle(Detalle detalle){
        lista.remove(detalle);
    }
    
    public Detalle buscarPosicion(int posicion){
        int i = 0;
        for(Detalle detalle : lista){
            if(i == posicion){
                return detalle;
            }
            i++;
        }
        return null;
    }
    
    
}
