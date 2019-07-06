/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Ayora
 */
public class ControladorProducto {

    private List<Producto> lista;
    private int codigo;

    public ControladorProducto() {
        lista = new ArrayList<>();
        codigo = 1;
    }

    public int getCodigo() {
        return codigo;
    }

    public void create(Producto producto) {
        producto.setCodigoProducto(codigo);
        codigo++;
        lista.add(producto);
    }

    public Producto read(int codigo) {
        for (Producto producto : lista) {
            if (producto.getCodigoProducto() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public void update(Producto producto) {
        if (lista.contains(producto)) {
            lista.remove(producto);
            lista.add(producto);
        }
    }

    public void delete(int codigo) {
        for (Producto producto : lista) {
            if (producto.getCodigoProducto() == codigo) {
                lista.remove(producto);
                break;
            }
        }
    }

    public void imprimir() {
        for (Producto producto : lista) {
            System.out.println(producto.getNombre());
        }
    }

    public List<Producto> getLista() {
        return lista;
    }

}
