/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorCliente;
import ec.edu.ups.controlador.ControladorDetalle;
import ec.edu.ups.controlador.ControladorEstante;
import ec.edu.ups.controlador.ControladorFactura;
import ec.edu.ups.controlador.ControladorPin;
import ec.edu.ups.controlador.ControladorProducto;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Detalle;
import ec.edu.ups.modelo.Estante;
import ec.edu.ups.modelo.Factura;
import ec.edu.ups.modelo.Pin;
import ec.edu.ups.modelo.Producto;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NRSerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Eduardo Ayora
 */
public class VistaCliente extends javax.swing.JFrame implements SerialPortEventListener {

    private NRSerialPort puertoUSB;
    private NRSerialPort puertoSensor;
    private ControladorProducto controladorProducto;
    private ControladorCliente controladorCliente;
    private ControladorFactura controladorFactura;
    private ControladorDetalle controladorDetalle;
    private ControladorEstante controladorEstante;
    private List<Pin> pines;
    private List<Estante> estantes;
    private List<Factura> facturas;
    private Cliente cliente;

    /**
     * Creates new form Prueba
     */
    public VistaCliente() {
        System.out.println(System.currentTimeMillis());
        initComponents();
        setLocationRelativeTo(null);
        facturas = new ArrayList<>();
        controladorProducto = new ControladorProducto();
        controladorCliente = new ControladorCliente();
        controladorFactura = new ControladorFactura();
        controladorDetalle = new ControladorDetalle();
        controladorEstante = new ControladorEstante();
        pines = new ControladorPin().listar();
        estantes = new ControladorEstante().listar();
        txtNumeroFactura.setText(Integer.toString(controladorFactura.getCodigo()));
        tblDetalles.setRowHeight(40);
        emparejarPinesEstantes();
        iniciarTimer();
        //conectar();
    }
    
    public void emparejarPinesEstantes() {
        for (Pin pin : pines) {
            for (Estante estante : estantes) {
                if (pin.getEstante().getCodigo() == estante.getCodigo()) {
                    pin.setEstante(estante);
                }
            }
        }
    }

    public void iniciarTimer() {
        Timer timer = new Timer(8000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Revisando");
                generarFactura();
            }
        });
        timer.start();
    }

    public void llegoPinArduino(int datoArduino) {
        Cliente clienteEstante = pines.get(datoArduino).getEstante().getCliente();
        System.out.println(pines.get(datoArduino).getEstante().getProducto());
        System.out.println("Cliente :" + clienteEstante != null);
        System.out.println("Pines :" + pines.get(datoArduino).getEstante().isAbierto());
        System.out.println("ultimo: " + pines.get(datoArduino).isActivado());
        if (clienteEstante != null && pines.get(datoArduino).getEstante().isAbierto() && pines.get(datoArduino).isActivado() == false) {
            Producto producto = pines.get(datoArduino).getEstante().getProducto();
            System.out.println(clienteEstante);
            facturacion(clienteEstante, producto);
            pines.get(datoArduino).setActivado(true);
        }
    }

    public void estanteSeleccionado(int estante) {
        if (cliente != null) {
            if (estantes.get(estante - 1).isAbierto() == false) {
                System.out.println("No esta abierto estante, se ha seleccionado");
                estantes.get(estante - 1).setSeleccionado(true);
                estantes.get(estante - 1).setCliente(cliente);
            } else {
                System.out.println("No puede seleccionar");
            }
        }
    }

    public void cerrarEstante(int codigoArduino) {
        estantes.get(codigoArduino - 1).setAbierto(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEdu = new javax.swing.JButton();
        btnKaren = new javax.swing.JButton();
        btnP1 = new javax.swing.JButton();
        btnP11 = new javax.swing.JButton();
        btnP2 = new javax.swing.JButton();
        btnP22 = new javax.swing.JButton();
        btnCaja1 = new javax.swing.JToggleButton();
        btnCaja2 = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCerrar1 = new javax.swing.JButton();
        btnCerrar2 = new javax.swing.JButton();
        btnFactura = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCedula = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        lblSubtotal = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        lblIva = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnEdu.setText("Edu");
        btnEdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEduActionPerformed(evt);
            }
        });

        btnKaren.setText("Karen");
        btnKaren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKarenActionPerformed(evt);
            }
        });

        btnP1.setText("Producto 1");
        btnP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP1ActionPerformed(evt);
            }
        });

        btnP11.setText("Producto 1");
        btnP11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP11ActionPerformed(evt);
            }
        });

        btnP2.setText("Producto 2");
        btnP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP2ActionPerformed(evt);
            }
        });

        btnP22.setText("Producto 2");
        btnP22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnP22ActionPerformed(evt);
            }
        });

        btnCaja1.setText("Estante1");
        btnCaja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaja1ActionPerformed(evt);
            }
        });

        btnCaja2.setText("Estante2");
        btnCaja2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaja2ActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCerrar1.setText("Cerrar1");
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });

        btnCerrar2.setText("Cerrar2");
        btnCerrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar2ActionPerformed(evt);
            }
        });

        btnFactura.setText("Crear Factura");
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });

        txtCedula.setEditable(false);

        lblCedula.setText("Cedula de Cliente:");

        lblNumero.setText("Número de Factura:");

        txtNumeroFactura.setEditable(false);
        txtNumeroFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroFacturaActionPerformed(evt);
            }
        });

        lblNombre.setText("Nombre:");

        lblTelefono.setText("Teléfono:");

        lblDireccion.setText("Dirección:");

        txtNombre.setEditable(false);

        txtTelefono.setEditable(false);

        txtDireccion.setEditable(false);

        lblFecha.setText("Fecha:");

        txtFecha.setEditable(false);

        tblDetalles.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de Producto", "Nombre de Producto", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDetalles);

        lblSubtotal.setText("Subtotal:");

        txtSubtotal.setEditable(false);
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });

        txtIva.setEditable(false);
        txtIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblIva.setText("Iva:");

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblTotal.setText("Total:");

        jLabel1.setText("Saldo:");

        txtSaldo.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIva)
                            .addComponent(lblTotal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtIva, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubtotal)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNombre)
                                            .addComponent(lblFecha))
                                        .addGap(84, 84, 84)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombre)
                                            .addComponent(txtFecha)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNumero)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDireccion)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(lblTelefono)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                            .addComponent(txtDireccion))
                                        .addGap(153, 153, 153))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 164, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1))))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedula)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefono)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccion)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIva)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCerrar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnP1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnP11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCerrar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnP2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnP22)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnEdu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnKaren)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFactura)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAceptar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCaja1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCaja2)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 538, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnP1)
                                    .addComponent(btnP11)
                                    .addComponent(btnCerrar1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnP2)
                                    .addComponent(btnP22)
                                    .addComponent(btnCerrar2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnKaren)
                                    .addComponent(btnEdu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCaja2)
                                    .addComponent(btnCaja1)
                                    .addComponent(btnFactura)))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEduActionPerformed
        // TODO add your handling code here:
        System.out.println("Edu esta usando la tarjeta");
        String usuario = "22517315225";
        try {
            cliente = controladorCliente.findByTarjeta(usuario);
            System.out.println(cliente);
            estantes.get(0).setAbierto(true);
            estantes.get(1).setAbierto(true);
            estantes.get(2).setAbierto(true);
            estantes.get(3).setAbierto(true);
            estantes.get(0).setCliente(cliente);
            estantes.get(1).setCliente(cliente);
            estantes.get(2).setCliente(cliente);
            estantes.get(3).setCliente(cliente);
            cajasDatosCliente();
            vaciarTablaFactura();
            for (Factura factura : facturas) {
                if (factura.getCliente().equals(cliente)) {
                    rellenarTabla(factura);
                }
            }
        } catch (PSQLException ex) {
            Logger.getLogger(VistaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEduActionPerformed

    private void btnKarenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKarenActionPerformed
        // TODO add your handling code here:
        System.out.println("Karen esta usando la tarjeta");
        String usuario = "1671621851";
        try {
            cliente = controladorCliente.findByTarjeta(usuario);
            System.out.println(cliente);
            estantes.get(0).setAbierto(true);
            estantes.get(1).setAbierto(true);
            estantes.get(2).setAbierto(true);
            estantes.get(3).setAbierto(true);
            estantes.get(0).setCliente(cliente);
            estantes.get(1).setCliente(cliente);
            estantes.get(2).setCliente(cliente);
            estantes.get(3).setCliente(cliente);
            cajasDatosCliente();
            vaciarTablaFactura();
            for (Factura factura : facturas) {
                if (factura.getCliente().equals(cliente)) {
                    rellenarTabla(factura);
                }
            }
        } catch (PSQLException ex) {
            Logger.getLogger(VistaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKarenActionPerformed

    private void btnP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP1ActionPerformed
        // TODO add your handling code here:
        int datoArduino = 0;
        llegoPinArduino(datoArduino);
    }//GEN-LAST:event_btnP1ActionPerformed

    private void btnP22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP22ActionPerformed
        // TODO add your handling code here:
        int datoArduino = 5;
        llegoPinArduino(datoArduino);
    }//GEN-LAST:event_btnP22ActionPerformed

    private void btnP11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP11ActionPerformed
        // TODO add your handling code here:
        int datoArduino = 10;
        llegoPinArduino(datoArduino);
    }//GEN-LAST:event_btnP11ActionPerformed

    private void btnP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnP2ActionPerformed
        // TODO add your handling code here:
        int datoArduino = 15;
        llegoPinArduino(datoArduino);
    }//GEN-LAST:event_btnP2ActionPerformed

    private void btnCaja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaja1ActionPerformed
        // TODO add your handling code here:
        int estante = 1;
        estanteSeleccionado(estante);
        btnCaja1.setSelected(false);
    }//GEN-LAST:event_btnCaja1ActionPerformed

    private void btnCaja2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaja2ActionPerformed
        // TODO add your handling code here:
        int estante = 2;
        estanteSeleccionado(estante);
    }//GEN-LAST:event_btnCaja2ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        cliente = null;
        int contador = 1;
        for (Estante estante : estantes) {
            if (estante.isSeleccionado()) {
                System.out.println("Esta seleccionado estante " + contador);
                estante.setAbierto(true);
                estante.setSeleccionado(false);
            }
            contador++;
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaActionPerformed
        // TODO add your handling code here:
        for (Factura factura : facturas) {
            factura.setNumeroFactura(controladorFactura.getCodigo());
            double subtotal = 0;
            for (Detalle detalle : factura.getDetalles()) {
                subtotal += detalle.getSubtotal();
            }
            factura.setSubtotal(subtotal);
            factura.setIva(factura.getSubtotal() * 0.12);
            factura.setTotal(factura.getSubtotal() + factura.getIva());
            try {
                controladorFactura.create(factura);
                JOptionPane.showMessageDialog(null, "Factura creada");
            } catch (PSQLException ex) {
                ex.printStackTrace();
            }
            for (Detalle detalle : factura.getDetalles()) {
                try {
                    controladorDetalle.create(detalle, factura.getNumeroFactura());
                } catch (PSQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        facturas = new ArrayList<>();
    }//GEN-LAST:event_btnFacturaActionPerformed

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        // TODO add your handling code here:
        enviarDato(101);
    }//GEN-LAST:event_btnCerrar1ActionPerformed

    private void btnCerrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar2ActionPerformed
        // TODO add your handling code here:
        int codigoArduino = 2;
        cerrarEstante(codigoArduino);
    }//GEN-LAST:event_btnCerrar2ActionPerformed

    private void txtNumeroFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroFacturaActionPerformed

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (puertoUSB != null && puertoUSB.isConnected()) {
            puertoUSB.disconnect();
            puertoUSB = null;
        }
        if (puertoSensor != null && puertoSensor.isConnected()) {
            puertoSensor.disconnect();
            puertoSensor = null;
        }
    }//GEN-LAST:event_formWindowClosing

    public void facturacion(Cliente clienteEstante, Producto producto) {
        boolean clienteEncontrado = false;
        for (Factura factura : facturas) {
            if (factura.getCliente().equals(clienteEstante)) {
                System.out.println("Cliente encontrado");
                boolean productoEncontrado = false;
                for (Detalle detalle : factura.getDetalles()) {
                    if (detalle.getProducto().equals(producto)) {
                        System.out.println("Producto encontrado");
                        detalle.setCantidad(detalle.getCantidad() + 1);
                        detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecio());
                        productoEncontrado = true;
                        rellenarTabla(factura);
                    }
                }
                if (productoEncontrado == false) {
                    Detalle detalle = new Detalle();
                    detalle.setPrecio(producto.getPrecio());
                    detalle.setCantidad(1);
                    detalle.setSubtotal(producto.getPrecio());
                    detalle.setProducto(producto);
                    factura.getDetalles().add(detalle);
                    rellenarTabla(factura);
                }
                clienteEncontrado = true;
                factura.setTiempoEspera(System.currentTimeMillis());
                break;
            }
        }
        if (clienteEncontrado == false) {
            Detalle detalle = new Detalle();
            detalle.setPrecio(producto.getPrecio());
            detalle.setCantidad(1);
            detalle.setSubtotal(producto.getPrecio());
            detalle.setProducto(producto);
            Factura factura = new Factura();
            factura.setCliente(clienteEstante);
            factura.getDetalles().add(detalle);
            facturas.add(factura);
            rellenarTabla(factura);
        }

    }

    public void generarFactura() {
        for (int i = 0; i < facturas.size(); i++) {
            Factura factura = facturas.get(i);
            System.out.println(System.currentTimeMillis() - factura.getTiempoEspera());
            if (System.currentTimeMillis() - factura.getTiempoEspera() > 15000) {
                factura.setNumeroFactura(controladorFactura.getCodigo());
                double subtotal = 0;
                for (Detalle detalle : factura.getDetalles()) {
                    subtotal += detalle.getSubtotal();
                    for(Estante estante : estantes){
                        if(estante.getProducto().equals(detalle.getProducto())){
                            estante.setCantidad(estante.getCantidad() - 1);
                            controladorEstante.update(estante);
                            break;
                        }
                    }
                }
                factura.setSubtotal(subtotal);
                factura.setIva(factura.getSubtotal() * 0.12);
                factura.setTotal(factura.getSubtotal() + factura.getIva());
                cliente.setSaldo(cliente.getSaldo() - factura.getTotal());
                try {
                    controladorFactura.create(factura);
                    controladorCliente.update(cliente);
                    vaciarCajas();
                    vaciarTablaFactura();
                } catch (PSQLException ex) {
                    ex.printStackTrace();
                }
                for (Detalle detalle : factura.getDetalles()) {
                    try {
                        controladorDetalle.create(detalle, factura.getNumeroFactura());
                    } catch (PSQLException ex) {
                        ex.printStackTrace();
                    }
                }
                facturas.remove(i);
            }
        }
    }

    public void conectar() {
        try {
            puertoUSB = new NRSerialPort("COM6", 9600);
            puertoUSB.connect();
            //Agregar para recibir
            puertoUSB.notifyOnDataAvailable(true);
            puertoUSB.addEventListener(this);

            puertoSensor = new NRSerialPort("COM8", 9600);
            puertoSensor.connect();
            //Agregar para recibir
            puertoSensor.notifyOnDataAvailable(true);
            puertoSensor.addEventListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent evento) {
        try {
            if (evento.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                DataInputStream lectura = new DataInputStream(puertoUSB.getInputStream());
                if (lectura.available() > 0) {
                    try {
                        System.out.println("Orden dada");
                        BufferedReader input = new BufferedReader(new InputStreamReader(puertoUSB.getInputStream()));
                        String entrada = input.readLine();
                        System.out.println(entrada);
                        System.out.println("Tamaño: " + entrada.length());
                        try {
                            if (entrada.length() > 1) {
                                cliente = controladorCliente.findByTarjeta(entrada);
                                if (cliente != null && cliente.getNombre() != null) {
                                    if(cliente.getSaldo() > 0){
                                        enviarDato(100);
                                    }
                                    System.out.println(cliente);
                                    estantes.get(0).setAbierto(true);
                                    estantes.get(1).setAbierto(true);
                                    estantes.get(2).setAbierto(true);
                                    estantes.get(3).setAbierto(true);
                                    estantes.get(0).setCliente(cliente);
                                    estantes.get(1).setCliente(cliente);
                                    estantes.get(2).setCliente(cliente);
                                    estantes.get(3).setCliente(cliente);
                                    cajasDatosCliente();
                                    vaciarTablaFactura();
                                }
                                for (Factura factura : facturas) {
                                    if (factura.getCliente().equals(cliente)) {
                                        rellenarTabla(factura);
                                    }
                                }
                            }
                        } catch (PSQLException ex) {
                            JOptionPane.showMessageDialog(null, "No esta registrado");
                            ex.printStackTrace();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                DataInputStream lecturaSensor = new DataInputStream(puertoSensor.getInputStream());
                if (lecturaSensor.available() > 0) {
                    int valor = lecturaSensor.read();
                    if (valor == 101) {
                        enviarDato(valor);
                    } else {
                        valor -= 20;
                        valor = valor / 2;
                        System.out.println(valor);
                        llegoPinArduino(valor);
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cajasDatosCliente() {
        txtNumeroFactura.setText(Integer.toString(controladorFactura.getCodigo()));
        txtCedula.setText(cliente.getCedula());
        txtNombre.setText(cliente.getNombre());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(new Date());
        txtFecha.setText(fecha);
        txtTelefono.setText(cliente.getCelular());
        txtDireccion.setText(cliente.getDireccion());
        txtSaldo.setText(String.format("%.2f", cliente.getSaldo()));
    }

    public void rellenarTabla(Factura factura) {
        DefaultTableModel modelo = (DefaultTableModel) tblDetalles.getModel();
        vaciarTablaFactura();
        List<Detalle> lista = factura.getDetalles();
        for (Detalle detalle : lista) {
            Object[] datos = {detalle.getProducto().getCodigoProducto(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecio(),
                detalle.getSubtotal()
            };
            modelo.addRow(datos);
        }
        double subtotal = 0;
        for (Detalle detalle : factura.getDetalles()) {
            subtotal += detalle.getSubtotal();
        }
        txtSubtotal.setText(Double.toString(subtotal));
        txtIva.setText(String.format("%.2f", subtotal * 0.12));
        double total = subtotal + (subtotal * 0.12);
        txtTotal.setText(String.format("%.2f", total));
        txtSaldo.setText(String.format("%.2f", cliente.getSaldo() - total));
    }

    public void vaciarCajas(){
        txtNumeroFactura.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtSaldo.setText("");
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
    }
    
    public void vaciarTablaFactura() {
        DefaultTableModel modelo = (DefaultTableModel) tblDetalles.getModel();
        int filas = tblDetalles.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    public void enviarDato(int valor) {
        try {
            OutputStream escritura = puertoUSB.getOutputStream();
            escritura.write(valor);
            escritura.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha conectado");
            ex.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JToggleButton btnCaja1;
    private javax.swing.JButton btnCaja2;
    private javax.swing.JButton btnCerrar1;
    private javax.swing.JButton btnCerrar2;
    private javax.swing.JButton btnEdu;
    private javax.swing.JButton btnFactura;
    private javax.swing.JButton btnKaren;
    private javax.swing.JButton btnP1;
    private javax.swing.JButton btnP11;
    private javax.swing.JButton btnP2;
    private javax.swing.JButton btnP22;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
