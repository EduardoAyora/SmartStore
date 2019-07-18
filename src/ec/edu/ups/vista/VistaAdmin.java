/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.vista;

import ec.edu.ups.controlador.ControladorCliente;
import ec.edu.ups.controlador.ControladorEstante;
import ec.edu.ups.controlador.ControladorFactura;
import ec.edu.ups.controlador.ControladorProducto;
import ec.edu.ups.modelo.ClaveAdmin;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Estante;
import ec.edu.ups.modelo.Factura;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Render;
import gnu.io.NRSerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Eduardo Ayora
 */
public class VistaAdmin extends javax.swing.JFrame implements SerialPortEventListener {

    private CambioContrasenia cambioContrasenia;
    private ClaveAdmin claveAdmin;
    private ControladorFactura controladorFactura;
    private ControladorCliente controladorCliente;
    private ControladorProducto controladorProducto;
    private ControladorEstante controladorEstante;
    private NRSerialPort puertoUSB;
    private double saldo;

    /**
     * Creates new form VistaPrincipal
     */
    public VistaAdmin() {
        initComponents();
        conectar();
        controladorFactura = new ControladorFactura();
        controladorCliente = new ControladorCliente();
        controladorProducto = new ControladorProducto();
        controladorEstante = new ControladorEstante();
        claveAdmin = new ClaveAdmin();
        this.setExtendedState(MAXIMIZED_BOTH);
        tabFactura.setRowHeight(40);
        tabProducto.setRowHeight(40);
        tabCliente.setRowHeight(40);
        tabEstante.setRowHeight(40);
        tabPin.setRowHeight(40);
        saldo = 0;
        iniciarRadioBotones();
        llenarDatosFactura();
        llenarDatosProducto();
        llenarDatosCliente();
        llenarDatosEstante();
        iniciarRadioBoton();
    }

    public void iniciarRadioBoton() {
        rbtnNuevoCli.setSelected(true);
        tCed.setEditable(true);
    }

    public void conectar() {
        try {
            puertoUSB = new NRSerialPort("COM6", 9600);
            puertoUSB.connect();
            //Agregar para recibir
            puertoUSB.notifyOnDataAvailable(true);
            puertoUSB.addEventListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void iniciarRadioBotones() {
        tCodPro.setText(Integer.toString(controladorProducto.getCodigo()));
        rbtnNuevo.setSelected(true);
    }

    public void llenarDatosFactura() {
        tabFactura.setDefaultRenderer(Object.class, new Render());
        JButton btnVer = new JButton("Ver");
        DefaultTableModel modelo = (DefaultTableModel) tabFactura.getModel();
        vaciarTablaFactura(modelo);
        List<Factura> lista = controladorFactura.listar();
        for (Factura factura : lista) {
            Object[] datos = {factura.getNumeroFactura(),
                factura.getFecha(),
                factura.getCliente().getNombre(),
                factura.getTotal(),
                factura.isActivo(),
                btnVer
            };
            modelo.addRow(datos);
        }

    }

    public void llenarDatosEstante(){
        DefaultTableModel modelo = (DefaultTableModel) tabEstante.getModel();
        vaciarTablaEstante(modelo);
        List<Estante> lista = controladorEstante.listar();
        for (Estante estante : lista) {
            Object[] datos = {estante.getCodigo(),
                estante.getProducto().getNombre(),
                estante.getCantidad()
            };
            modelo.addRow(datos);
        }
    }
    
    public void llenarDatosProducto() {
        DefaultTableModel modelo = (DefaultTableModel) tabProducto.getModel();
        vaciarTablaProducto(modelo);
        List<Producto> lista = controladorProducto.listar();
        for (Producto producto : lista) {
            Object[] datos = {producto.getCodigoProducto(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion()
            };
            modelo.addRow(datos);
        }
    }

    public void llenarDatosCliente() {
        DefaultTableModel modelo = (DefaultTableModel) tabCliente.getModel();
        vaciarTablaCliente(modelo);
        List<Cliente> lista = controladorCliente.listar();
        for (Cliente cliente : lista) {
            Object[] datos = {cliente.getCedula(),
                cliente.getNombre() + " " + cliente.getApellido(),
                cliente.getCelular(),
                cliente.getCorreo(),
                cliente.getDireccion(),
                cliente.getCodigoTarjeta(),
                cliente.getSaldo()
            };
            modelo.addRow(datos);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pestaFactura = new javax.swing.JTabbedPane();
        pFac = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabFactura = new javax.swing.JTable();
        bBuscarFac = new javax.swing.JButton();
        tNumFactura = new javax.swing.JTextField();
        lNumFactura = new javax.swing.JLabel();
        pPro = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabProducto = new javax.swing.JTable();
        lCodProducto = new javax.swing.JLabel();
        tCodProducto = new javax.swing.JTextField();
        bBuscarPro = new javax.swing.JButton();
        bEliminarPro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lCodPro = new javax.swing.JLabel();
        lNomPro = new javax.swing.JLabel();
        lPrePro = new javax.swing.JLabel();
        lDesPro = new javax.swing.JLabel();
        tCodPro = new javax.swing.JTextField();
        tNomPro = new javax.swing.JTextField();
        tPrePro = new javax.swing.JTextField();
        tDesPro = new javax.swing.JTextField();
        rbtnActualizarPro = new javax.swing.JRadioButton();
        rbtnNuevo = new javax.swing.JRadioButton();
        btnAceptarProducto = new javax.swing.JButton();
        btnSeleccionarProducto = new javax.swing.JButton();
        btnSeleccionarProducto1 = new javax.swing.JButton();
        pCli = new javax.swing.JPanel();
        lCedCliente = new javax.swing.JLabel();
        tCedCliente = new javax.swing.JTextField();
        bBuscarCli = new javax.swing.JButton();
        bEliminarCli = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabCliente = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lCed = new javax.swing.JLabel();
        tCed = new javax.swing.JTextField();
        lNombre = new javax.swing.JLabel();
        lApellido = new javax.swing.JLabel();
        lCelular = new javax.swing.JLabel();
        lTarjeta = new javax.swing.JLabel();
        lCorreo = new javax.swing.JLabel();
        lDireccion = new javax.swing.JLabel();
        tNomCli = new javax.swing.JTextField();
        tApellidoCli = new javax.swing.JTextField();
        tCelCli = new javax.swing.JTextField();
        tCoCli = new javax.swing.JTextField();
        tDirCli = new javax.swing.JTextField();
        tTarCli = new javax.swing.JTextField();
        rbtnNuevoCli = new javax.swing.JRadioButton();
        rbtnActualizarCli = new javax.swing.JRadioButton();
        bAceptarCli = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSaldo = new javax.swing.JTextField();
        btnAgregarSaldo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        pEst = new javax.swing.JPanel();
        lCodEstante = new javax.swing.JLabel();
        tCodEstante = new javax.swing.JTextField();
        bBuscarEst = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabEstante = new javax.swing.JTable();
        bEliminarEst = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lCodEst = new javax.swing.JLabel();
        lProEst = new javax.swing.JLabel();
        tCodEst = new javax.swing.JTextField();
        tProEst = new javax.swing.JTextField();
        rbtnActualizarEst = new javax.swing.JRadioButton();
        rbtnNuevoEst = new javax.swing.JRadioButton();
        btnAceptarEst = new javax.swing.JButton();
        pPin = new javax.swing.JPanel();
        lCodigoPin = new javax.swing.JLabel();
        tCodigoPin = new javax.swing.JTextField();
        bBuscarPin = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabPin = new javax.swing.JTable();
        bEliminarPin = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lCodPin = new javax.swing.JLabel();
        lEstantePin = new javax.swing.JLabel();
        tCodPin = new javax.swing.JTextField();
        tEstantePin = new javax.swing.JTextField();
        rbtnActualizarPin = new javax.swing.JRadioButton();
        rbtnNuevoPin = new javax.swing.JRadioButton();
        bAceptarPin = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administración");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Fecha", "Cliente", "Total", "Estado", "Ver"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabFacturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabFactura);

        bBuscarFac.setText("Buscar");
        bBuscarFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarFacActionPerformed(evt);
            }
        });

        lNumFactura.setText("Número de Factura:");

        javax.swing.GroupLayout pFacLayout = new javax.swing.GroupLayout(pFac);
        pFac.setLayout(pFacLayout);
        pFacLayout.setHorizontalGroup(
            pFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFacLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pFacLayout.createSequentialGroup()
                        .addComponent(lNumFactura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bBuscarFac)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)))
        );
        pFacLayout.setVerticalGroup(
            pFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFacLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pFacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lNumFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tNumFactura)
                    .addComponent(bBuscarFac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
        );

        pestaFactura.addTab("Factura", pFac);

        tabProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Precio", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabProducto);
        if (tabProducto.getColumnModel().getColumnCount() > 0) {
            tabProducto.getColumnModel().getColumn(2).setHeaderValue("Precio");
            tabProducto.getColumnModel().getColumn(3).setHeaderValue("Descripción");
        }

        lCodProducto.setText("Código de Producto:");

        bBuscarPro.setText("Buscar");
        bBuscarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarProActionPerformed(evt);
            }
        });

        bEliminarPro.setText("Eliminar");
        bEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarProActionPerformed(evt);
            }
        });

        lCodPro.setText("Código:");

        lNomPro.setText("Nombre:");

        lPrePro.setText("Precio:");

        lDesPro.setText("Descripción:");

        tCodPro.setEditable(false);

        rbtnActualizarPro.setText("Actualizar");
        rbtnActualizarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnActualizarProActionPerformed(evt);
            }
        });

        rbtnNuevo.setText("Nuevo");
        rbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNuevoActionPerformed(evt);
            }
        });

        btnAceptarProducto.setText("Aceptar");
        btnAceptarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lCodPro)
                    .addComponent(lNomPro)
                    .addComponent(lPrePro)
                    .addComponent(lDesPro))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tDesPro, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(tNomPro)
                    .addComponent(tCodPro, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tPrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rbtnActualizarPro)
                        .addComponent(rbtnNuevo))
                    .addComponent(btnAceptarProducto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCodPro)
                            .addComponent(tCodPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(rbtnNuevo)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomPro)
                    .addComponent(tNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnActualizarPro))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tPrePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lPrePro))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDesPro)
                    .addComponent(tDesPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptarProducto))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        btnSeleccionarProducto.setText("Seleccionar");
        btnSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoActionPerformed(evt);
            }
        });

        btnSeleccionarProducto1.setText("Seleccionar");
        btnSeleccionarProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProducto1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pProLayout = new javax.swing.GroupLayout(pPro);
        pPro.setLayout(pProLayout);
        pProLayout.setHorizontalGroup(
            pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pProLayout.createSequentialGroup()
                        .addComponent(lCodProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBuscarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bEliminarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarProducto1))
                .addContainerGap())
        );
        pProLayout.setVerticalGroup(
            pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tCodProducto)
                        .addComponent(bBuscarPro))
                    .addComponent(lCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pProLayout.createSequentialGroup()
                        .addComponent(btnSeleccionarProducto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bEliminarPro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pestaFactura.addTab("Producto", pPro);

        lCedCliente.setText("Cédula de Cliente:");

        bBuscarCli.setText("Buscar");
        bBuscarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscarCliActionPerformed(evt);
            }
        });

        bEliminarCli.setText("Eliminar");
        bEliminarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCliActionPerformed(evt);
            }
        });

        tabCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Celular", "Correo", "Direccion", "Tarjeta", "Saldo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabCliente);

        lCed.setText("Cédula:");

        tCed.setEditable(false);

        lNombre.setText("Nombre:");

        lApellido.setText("Apellido:");

        lCelular.setText("Celular:");

        lTarjeta.setText("Tarjeta:");

        lCorreo.setText("Correo:");

        lDireccion.setText("Dirección:");

        tNomCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNomCliActionPerformed(evt);
            }
        });

        tApellidoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tApellidoCliActionPerformed(evt);
            }
        });

        tTarCli.setEditable(false);

        rbtnNuevoCli.setText("Nuevo");
        rbtnNuevoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNuevoCliActionPerformed(evt);
            }
        });

        rbtnActualizarCli.setText("Actualizar");
        rbtnActualizarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnActualizarCliActionPerformed(evt);
            }
        });

        bAceptarCli.setText("Aceptar");
        bAceptarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarCliActionPerformed(evt);
            }
        });

        jLabel1.setText("Saldo:");

        txtSaldo.setEditable(false);

        btnAgregarSaldo.setText("Agregar Saldo");
        btnAgregarSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSaldoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lApellido)
                            .addComponent(lCelular)
                            .addComponent(lNombre)
                            .addComponent(lCed)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lDireccion)
                            .addComponent(lCorreo)
                            .addComponent(lTarjeta)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tCoCli, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(tDirCli)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tTarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tCed, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tNomCli)
                        .addComponent(tApellidoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tCelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAgregarSaldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnActualizarCli)
                    .addComponent(rbtnNuevoCli)
                    .addComponent(bAceptarCli))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rbtnNuevoCli)
                        .addGap(22, 22, 22)
                        .addComponent(rbtnActualizarCli)
                        .addGap(169, 169, 169)
                        .addComponent(bAceptarCli))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCed)
                            .addComponent(tCed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNombre)
                            .addComponent(tNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lApellido)
                            .addComponent(tApellidoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCelular)
                            .addComponent(tCelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCorreo)
                            .addComponent(tCoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lDireccion)
                            .addComponent(tDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lTarjeta)
                            .addComponent(tTarCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarSaldo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCliLayout = new javax.swing.GroupLayout(pCli);
        pCli.setLayout(pCliLayout);
        pCliLayout.setHorizontalGroup(
            pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addGroup(pCliLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)))
                .addGap(18, 18, 18)
                .addGroup(pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bEliminarCli, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pCliLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lCedCliente)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(tCedCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(358, Short.MAX_VALUE)))
        );
        pCliLayout.setVerticalGroup(
            pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCliLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(bBuscarCli)
                .addGroup(pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCliLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(pCliLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnSeleccionar)
                        .addGap(29, 29, 29)
                        .addComponent(bEliminarCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pCliLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(pCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tCedCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lCedCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(399, Short.MAX_VALUE)))
        );

        pestaFactura.addTab("Cliente", pCli);

        lCodEstante.setText("Código de Estante:");

        bBuscarEst.setText("Buscar");

        tabEstante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Producto", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabEstante);

        bEliminarEst.setText("Eliminar");
        bEliminarEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarEstActionPerformed(evt);
            }
        });

        lCodEst.setText("Código:");

        lProEst.setText("Producto:");

        tCodEst.setEditable(false);

        rbtnActualizarEst.setText("Actualizar");

        rbtnNuevoEst.setText("Nuevo");

        btnAceptarEst.setText("Aceptar");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lProEst)
                    .addComponent(lCodEst))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCodEst, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tProEst, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnActualizarEst)
                    .addComponent(rbtnNuevoEst)
                    .addComponent(btnAceptarEst))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCodEst)
                            .addComponent(tCodEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(rbtnNuevoEst)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProEst)
                    .addComponent(tProEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnActualizarEst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptarEst)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pEstLayout = new javax.swing.GroupLayout(pEst);
        pEst.setLayout(pEstLayout);
        pEstLayout.setHorizontalGroup(
            pEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEstLayout.createSequentialGroup()
                        .addComponent(lCodEstante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tCodEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBuscarEst, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bEliminarEst, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pEstLayout.setVerticalGroup(
            pEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tCodEstante)
                        .addComponent(bBuscarEst))
                    .addComponent(lCodEstante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEstLayout.createSequentialGroup()
                        .addComponent(bEliminarEst)
                        .addGap(0, 245, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pestaFactura.addTab("Estante", pEst);

        lCodigoPin.setText("Código de Pin:");

        bBuscarPin.setText("Buscar");

        tabPin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Estante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabPin);

        bEliminarPin.setText("Eliminar");

        lCodPin.setText("Código:");

        lEstantePin.setText("Estante:");

        tCodPin.setEditable(false);

        rbtnActualizarPin.setText("Actualizar");

        rbtnNuevoPin.setText("Nuevo");

        bAceptarPin.setText("Aceptar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lEstantePin)
                    .addComponent(lCodPin))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCodPin, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tEstantePin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnActualizarPin)
                    .addComponent(rbtnNuevoPin)
                    .addComponent(bAceptarPin))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lCodPin)
                            .addComponent(tCodPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(rbtnNuevoPin)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lEstantePin)
                    .addComponent(tEstantePin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnActualizarPin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAceptarPin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pPinLayout = new javax.swing.GroupLayout(pPin);
        pPin.setLayout(pPinLayout);
        pPinLayout.setHorizontalGroup(
            pPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPinLayout.createSequentialGroup()
                        .addComponent(lCodigoPin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tCodigoPin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBuscarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bEliminarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pPinLayout.setVerticalGroup(
            pPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tCodigoPin)
                        .addComponent(bBuscarPin))
                    .addComponent(lCodigoPin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPinLayout.createSequentialGroup()
                        .addComponent(bEliminarPin)
                        .addGap(0, 245, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pestaFactura.addTab("Pin", pPin);

        jMenu1.setText("Ajustes");

        jMenuItem1.setText("Administrar contraseña");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem2.setText("Salir");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pestaFactura)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pestaFactura)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (puertoUSB != null && puertoUSB.isConnected()) {
            puertoUSB.disconnect();
            puertoUSB = null;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (cambioContrasenia == null || cambioContrasenia.isVisible() == false) {
            cambioContrasenia = new CambioContrasenia(claveAdmin);
            cambioContrasenia.setVisible(true);
            cambioContrasenia.toFront();
        } else {
            cambioContrasenia.toFront();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void bBuscarFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarFacActionPerformed
        // TODO add your handling code here:
        try {
            Factura factura = controladorFactura.read(Integer.parseInt(tNumFactura.getText()));
            DetallesFactura detallesFactura = new DetallesFactura(factura);
            detallesFactura.setVisible(true);
        } catch (PSQLException ex) {
            JOptionPane.showMessageDialog(null, "La factura no existe");
        }
    }//GEN-LAST:event_bBuscarFacActionPerformed

    private void tabFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabFacturaMouseClicked
        // TODO add your handling code here:
        try {
            int columna = tabFactura.getColumnModel().getColumnIndexAtX(evt.getX());
            int fila = evt.getY() / tabFactura.getRowHeight();

            if (fila < tabFactura.getRowCount() && fila >= 0 && columna < tabFactura.getColumnCount() && columna >= 0) {
                Object value = tabFactura.getValueAt(fila, columna);
                if (value instanceof JButton) {
                    ((JButton) value).doClick();
                    JButton boton = (JButton) value;
                    int numFactura = (evt.getY() / tabFactura.getRowHeight()) + 1;
                    try {
                        Factura factura = controladorFactura.read(numFactura);
                        DetallesFactura detallesFactura = new DetallesFactura(factura);
                        detallesFactura.setVisible(true);
                    } catch (PSQLException ex) {
                        Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (java.lang.IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tabFacturaMouseClicked

    private void bNuevoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuevoCliActionPerformed
        // TODO add your handling code here:
        int fila = tabCliente.getSelectedRow();
        try {
            Cliente cliente = controladorCliente.findByCedula((String) tabCliente.getValueAt(fila, 0));

        } catch (PSQLException ex) {
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNuevoCliActionPerformed

    private void bEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarProActionPerformed
        // TODO add your handling code here:
        int fila = tabProducto.getSelectedRow();
        try {
            controladorProducto.delete((int) tabProducto.getValueAt(fila, 0));
        } catch (PSQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarDatosProducto();
    }//GEN-LAST:event_bEliminarProActionPerformed

    private void rbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNuevoActionPerformed
        // TODO add your handling code here:
        vaciarCajas();
        tCodPro.setText(Integer.toString(controladorProducto.getCodigo()));
        rbtnActualizarPro.setSelected(false);
    }//GEN-LAST:event_rbtnNuevoActionPerformed

    private void rbtnActualizarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnActualizarProActionPerformed
        // TODO add your handling code here:
        rbtnNuevo.setSelected(false);
    }//GEN-LAST:event_rbtnActualizarProActionPerformed

    private void btnAceptarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarProductoActionPerformed
        // TODO add your handling code here:
        try {
            if (rbtnNuevo.isSelected()) {
                Producto producto = new Producto();
                producto.setNombre(tNomPro.getText());
                producto.setPrecio(Double.parseDouble(tPrePro.getText()));
                producto.setDescripcion(tDesPro.getText());
                try {
                    controladorProducto.create(producto);
                } catch (PSQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Producto producto = new Producto();
                producto.setCodigoProducto(Integer.parseInt(tCodPro.getText()));
                producto.setNombre(tNomPro.getText());
                producto.setPrecio(Double.parseDouble(tPrePro.getText()));
                producto.setDescripcion(tDesPro.getText());
                controladorProducto.update(producto);
            }
            llenarDatosProducto();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_btnAceptarProductoActionPerformed

    private void bAceptarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarCliActionPerformed
        // TODO add your handling code here:
        try {
            if (!tTarCli.getText().equals("") && !tCed.getText().equals("")) {
                if (rbtnNuevoCli.isSelected()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoTarjeta(tTarCli.getText());
                cliente.setCedula(tCed.getText());
                cliente.setNombre(tNomCli.getText());
                cliente.setApellido(tApellidoCli.getText());
                cliente.setCelular(tCelCli.getText());
                cliente.setCorreo(tCoCli.getText());
                cliente.setDireccion(tDirCli.getText());
                cliente.setSaldo(saldo);
                controladorCliente.create(cliente);
                llenarDatosCliente();
                saldo = 0;
            } else {
                Cliente cliente = new Cliente();
                cliente.setCodigoTarjeta(tTarCli.getText());
                cliente.setCedula(tCed.getText());
                cliente.setNombre(tNomCli.getText());
                cliente.setApellido(tApellidoCli.getText());
                cliente.setCelular(tCelCli.getText());
                cliente.setCorreo(tCoCli.getText());
                cliente.setDireccion(tDirCli.getText());
                cliente.setSaldo(saldo);
                controladorCliente.update(cliente);
                llenarDatosCliente();
                saldo = 0;
            }
            }else{
                JOptionPane.showMessageDialog(null, "Hay campos vacios");
            }
            

        } catch (PSQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bAceptarCliActionPerformed

    private void rbtnActualizarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnActualizarCliActionPerformed
        // TODO add your handling code here:
        rbtnNuevoCli.setSelected(false);
        tCed.setEditable(false);
    }//GEN-LAST:event_rbtnActualizarCliActionPerformed

    private void rbtnNuevoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNuevoCliActionPerformed
        // TODO add your handling code here:
        rbtnActualizarCli.setSelected(false);
        tCed.setEditable(true);
        saldo = 0;
        vaciarCajasCliente();
    }//GEN-LAST:event_rbtnNuevoCliActionPerformed

    private void bEliminarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarCliActionPerformed
        // TODO add your handling code here:
        int fila = tabCliente.getSelectedRow();
        try {
            controladorCliente.delete((String) tabCliente.getValueAt(fila, 0));
        } catch (PSQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        llenarDatosCliente();
    }//GEN-LAST:event_bEliminarCliActionPerformed

    private void tNomCliActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        vaciarCajas();
        tCodPro.setText(Integer.toString(controladorProducto.getCodigo()));
        rbtnActualizarPro.setSelected(false);
    }

    private void tApellidoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tApellidoCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tApellidoCliActionPerformed

    private void bEliminarEstActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            if (rbtnNuevo.isSelected()) {
                Producto producto = new Producto();
                producto.setNombre(tNomPro.getText());
                producto.setPrecio(Integer.parseInt(tPrePro.getText()));
                producto.setDescripcion(tDesPro.getText());
                try {
                    controladorProducto.create(producto);
                } catch (PSQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(VistaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Producto producto = new Producto();
                producto.setCodigoProducto(Integer.parseInt(tCodPro.getText()));
                producto.setNombre(tNomPro.getText());
                producto.setPrecio(Double.parseDouble(tPrePro.getText()));
                producto.setDescripcion(tDesPro.getText());
                controladorProducto.update(producto);
            }
            llenarDatosProducto();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    private void btnSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductoActionPerformed
        // TODO add your handling code here:
        int fila = tabProducto.getSelectedRow();
        int codigo = (int) tabProducto.getValueAt(fila, 0);
        try {
            Producto producto = controladorProducto.read(codigo);
            tCodPro.setText(Integer.toString(producto.getCodigoProducto()));
            tNomPro.setText(producto.getNombre());
            tPrePro.setText(Double.toString(producto.getPrecio()));
            tDesPro.setText(producto.getDescripcion());
            rbtnActualizarPro.setSelected(true);
            rbtnNuevo.setSelected(false);
        } catch (PSQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSeleccionarProductoActionPerformed

    private void bBuscarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarProActionPerformed
        // TODO add your handling code here:
        try {
            Producto producto = controladorProducto.read(Integer.parseInt(tCodProducto.getText()));
            tCodPro.setText(Integer.toString(producto.getCodigoProducto()));
            tNomPro.setText(producto.getNombre());
            tPrePro.setText(Double.toString(producto.getPrecio()));
            tDesPro.setText(producto.getDescripcion());
            rbtnActualizarPro.setSelected(true);
            rbtnNuevo.setSelected(false);
        } catch (PSQLException ex) {
            JOptionPane.showMessageDialog(null, "El producto no existe");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bBuscarProActionPerformed

    private void btnSeleccionarProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProducto1ActionPerformed
        // TODO add your handling code here:
        try {
            int fila = tabProducto.getSelectedRow();
            int codigo = (int) tabProducto.getValueAt(fila, 0);
            Producto producto = controladorProducto.read(codigo);
            tCodPro.setText(Integer.toString(producto.getCodigoProducto()));
            tNomPro.setText(producto.getNombre());
            tPrePro.setText(Double.toString(producto.getPrecio()));
            tDesPro.setText(producto.getDescripcion());
            rbtnActualizarPro.setSelected(true);
            rbtnNuevo.setSelected(false);
        } catch (PSQLException ex) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSeleccionarProducto1ActionPerformed

    private void btnAgregarSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSaldoActionPerformed
        // TODO add your handling code here:
        try {
            double saldoExtra = Double.parseDouble(JOptionPane.showInputDialog(null, " Ingrese saldo: "));
            saldo += saldoExtra;
            txtSaldo.setText(Double.toString(saldo));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAgregarSaldoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        try {
            rbtnActualizarCli.setSelected(true);
            rbtnNuevoCli.setSelected(false);
            tCed.setEditable(false);
            int fila = tabCliente.getSelectedRow();
            String cedula = (String) tabCliente.getValueAt(fila, 0);
            Cliente cliente = controladorCliente.findByCedula(cedula);
            tCed.setText(cliente.getCedula());
            tNomCli.setText(cliente.getNombre());
            tApellidoCli.setText(cliente.getApellido());
            tCelCli.setText(cliente.getCelular());
            tCoCli.setText(cliente.getCorreo());
            tDirCli.setText(cliente.getDireccion());
            tTarCli.setText(cliente.getCodigoTarjeta());
            txtSaldo.setText(Double.toString(cliente.getSaldo()));
            saldo = cliente.getSaldo();
            rbtnNuevo.setSelected(false);
        } catch (PSQLException ex) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void bBuscarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscarCliActionPerformed
        // TODO add your handling code here:
        try {
            rbtnActualizarCli.setSelected(true);
            rbtnNuevoCli.setSelected(false);
            tCed.setEditable(false);
            Cliente cliente = controladorCliente.findByCedula(tCedCliente.getText());
            tCed.setText(cliente.getCedula());
            tNomCli.setText(cliente.getNombre());
            tApellidoCli.setText(cliente.getApellido());
            tCelCli.setText(cliente.getCelular());
            tCoCli.setText(cliente.getCorreo());
            tDirCli.setText(cliente.getDireccion());
            tTarCli.setText(cliente.getCodigoTarjeta());
            txtSaldo.setText(Double.toString(cliente.getSaldo()));
            saldo = cliente.getSaldo();
            rbtnNuevo.setSelected(false);
        } catch (PSQLException ex) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado una fila");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bBuscarCliActionPerformed

    public void vaciarTablaEstante(DefaultTableModel modelo) {
        int filas = tabEstante.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }
    
    public void vaciarTablaProducto(DefaultTableModel modelo) {
        int filas = tabProducto.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    public void vaciarTablaFactura(DefaultTableModel modelo) {
        int filas = tabFactura.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    public void vaciarTablaCliente(DefaultTableModel modelo) {
        int filas = tabCliente.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }

    public void vaciarCajas() {
        tCodPro.setText("");
        tNomPro.setText("");
        tPrePro.setText("");
        tDesPro.setText("");
    }

    public void vaciarCajasCliente() {
        tCed.setText("");
        tNomCli.setText("");
        tApellidoCli.setText("");
        tCelCli.setText("");
        tCoCli.setText("");
        tDirCli.setText("");
        tTarCli.setText("");
        txtSaldo.setText("");
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
                        if (entrada.length() > 1) {
                            tTarCli.setText(entrada);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptarCli;
    private javax.swing.JButton bAceptarPin;
    private javax.swing.JButton bBuscarCli;
    private javax.swing.JButton bBuscarEst;
    private javax.swing.JButton bBuscarFac;
    private javax.swing.JButton bBuscarPin;
    private javax.swing.JButton bBuscarPro;
    private javax.swing.JButton bEliminarCli;
    private javax.swing.JButton bEliminarEst;
    private javax.swing.JButton bEliminarPin;
    private javax.swing.JButton bEliminarPro;
    private javax.swing.JButton btnAceptarEst;
    private javax.swing.JButton btnAceptarProducto;
    private javax.swing.JButton btnAgregarSaldo;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnSeleccionarProducto;
    private javax.swing.JButton btnSeleccionarProducto1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lApellido;
    private javax.swing.JLabel lCed;
    private javax.swing.JLabel lCedCliente;
    private javax.swing.JLabel lCelular;
    private javax.swing.JLabel lCodEst;
    private javax.swing.JLabel lCodEstante;
    private javax.swing.JLabel lCodPin;
    private javax.swing.JLabel lCodPro;
    private javax.swing.JLabel lCodProducto;
    private javax.swing.JLabel lCodigoPin;
    private javax.swing.JLabel lCorreo;
    private javax.swing.JLabel lDesPro;
    private javax.swing.JLabel lDireccion;
    private javax.swing.JLabel lEstantePin;
    private javax.swing.JLabel lNomPro;
    private javax.swing.JLabel lNombre;
    private javax.swing.JLabel lNumFactura;
    private javax.swing.JLabel lPrePro;
    private javax.swing.JLabel lProEst;
    private javax.swing.JLabel lTarjeta;
    private javax.swing.JPanel pCli;
    private javax.swing.JPanel pEst;
    private javax.swing.JPanel pFac;
    private javax.swing.JPanel pPin;
    private javax.swing.JPanel pPro;
    private javax.swing.JTabbedPane pestaFactura;
    private javax.swing.JRadioButton rbtnActualizarCli;
    private javax.swing.JRadioButton rbtnActualizarEst;
    private javax.swing.JRadioButton rbtnActualizarPin;
    private javax.swing.JRadioButton rbtnActualizarPro;
    private javax.swing.JRadioButton rbtnNuevo;
    private javax.swing.JRadioButton rbtnNuevoCli;
    private javax.swing.JRadioButton rbtnNuevoEst;
    private javax.swing.JRadioButton rbtnNuevoPin;
    private javax.swing.JTextField tApellidoCli;
    private javax.swing.JTextField tCed;
    private javax.swing.JTextField tCedCliente;
    private javax.swing.JTextField tCelCli;
    private javax.swing.JTextField tCoCli;
    private javax.swing.JTextField tCodEst;
    private javax.swing.JTextField tCodEstante;
    private javax.swing.JTextField tCodPin;
    private javax.swing.JTextField tCodPro;
    private javax.swing.JTextField tCodProducto;
    private javax.swing.JTextField tCodigoPin;
    private javax.swing.JTextField tDesPro;
    private javax.swing.JTextField tDirCli;
    private javax.swing.JTextField tEstantePin;
    private javax.swing.JTextField tNomCli;
    private javax.swing.JTextField tNomPro;
    private javax.swing.JTextField tNumFactura;
    private javax.swing.JTextField tPrePro;
    private javax.swing.JTextField tProEst;
    private javax.swing.JTextField tTarCli;
    private javax.swing.JTable tabCliente;
    private javax.swing.JTable tabEstante;
    public static javax.swing.JTable tabFactura;
    private javax.swing.JTable tabPin;
    private javax.swing.JTable tabProducto;
    private javax.swing.JTextField txtSaldo;
    // End of variables declaration//GEN-END:variables

}
