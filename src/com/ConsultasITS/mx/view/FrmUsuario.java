/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;

import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.UsuarioBO;
import com.ConsultasITS.mx.entity.Usuario;
import javax.swing.JOptionPane;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author kevin
 */
public class FrmUsuario extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    public FrmUsuario() {
        initComponents();
        setTitle("FORMULARIO USUARIOS");
        setLocationRelativeTo(null);
        setResizable(false);
        listarUsuario();
        idMax();
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        initComponents1();
    }

    public void initComponents1() {
        txtId_Usuario.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validarYCargarUsuario();
            }

            public void removeUpdate(DocumentEvent e) {
                validarYCargarUsuario();
            }

            public void changeUpdate(DocumentEvent e) {
                validarYCargarUsuario();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    private void validarYCargarUsuario() {
        String idTexto = txtId_Usuario.getText();
        if (!idTexto.isEmpty() && idTexto.matches("\\d+")) {
            int idUsuario = Integer.parseInt(idTexto);
            Usuario usuario = userbo.buscarUsuarioPorId(idUsuario);

            if (usuario != null) {
                txtIngreso_Usuario.setText(usuario.getIngreso_usuario());
                txtIngreso_Contrasena.setText(usuario.getIngreso_contrasena());
                txtNombre.setText(usuario.getNombre());
                txtAp_Paterno.setText(usuario.getAp_Paterno());
                txtAp_Materno.setText(usuario.getAp_Materno());
                // Verificar el tipo de usuario y seleccionar el RadioButton correspondiente
                if (usuario.getTipo_usuario() == 'A') {  // Usamos un char para la comparación
                    rdAdministrador.setSelected(true);   // Selecciona el radio de administrador
                } else if (usuario.getTipo_usuario() == 'P') {  // Usamos un char para la comparación
                    rdPersonaS.setSelected(true);  // Selecciona el radio de personal
                }
            } else {
                txtIngreso_Usuario.setText("");
                txtIngreso_Contrasena.setText("");
                rdAdministrador.setSelected(false);
                rdPersonaS.setSelected(false);
                txtNombre.setText("");
                txtAp_Paterno.setText("");
                txtAp_Materno.setText("");
            }
        } else {
            txtIngreso_Usuario.setText("");
            txtIngreso_Contrasena.setText("");
            rdAdministrador.setSelected(false);
            rdPersonaS.setSelected(false);
            txtNombre.setText("");
            txtAp_Paterno.setText("");
            txtAp_Materno.setText("");
        }
    }

    public void validar() {
        if (txtId_Usuario.getText().isEmpty()) {
            jlbIdUsuario.setText("*Obligatorio");
        } else {
            jlbIdUsuario.setText("");
        }
        if (txtIngreso_Usuario.getText().isEmpty()) {
            jlbUsuario.setText("*Obligatorio");
        } else {
            jlbUsuario.setText("");
        }
        if (txtIngreso_Contrasena.getText().isEmpty()) {
            jlbContraseña.setText("*Obligatorio");
        } else {
            jlbContraseña.setText("");
        }
        if (rdAdministrador.isSelected() || rdPersonaS.isSelected() || rdRecepcionista.isSelected()) {
            jlbTipo.setText("");
        } else {
            jlbTipo.setText("*Obligatorio");
        }
        if (txtId_Usuario.getText().isEmpty() || txtIngreso_Usuario.getText().isEmpty() || txtIngreso_Contrasena.getText().isEmpty()
                || !(rdAdministrador.isSelected() || rdPersonaS.isSelected() || rdRecepcionista.isSelected())) {
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        } else {
            btnAgregar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }
    private UsuarioBO userbo = new UsuarioBO();

    public void listarUsuario() {
        userbo.listarUsuario(tbUsuario);
    }

    public void idMax() {
        txtId_Usuario.setText(userbo.getMaxID() + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngroupTipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtId_Usuario = new javax.swing.JTextField();
        jlbIdUsuario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIngreso_Usuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIngreso_Contrasena = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_MostrarM = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btn_MostrarM2 = new javax.swing.JButton();
        rdAdministrador = new javax.swing.JRadioButton();
        rdPersonaS = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuario = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jlbUsuario = new javax.swing.JLabel();
        jlbTipo = new javax.swing.JLabel();
        jlbContraseña = new javax.swing.JLabel();
        rdRecepcionista = new javax.swing.JRadioButton();
        Nombre = new javax.swing.JLabel();
        txtAp_Paterno = new javax.swing.JTextField();
        AP_Paterno = new javax.swing.JLabel();
        txtAp_Materno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        AP_Paterno1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LogoP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtId_Usuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId_Usuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_UsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_UsuarioKeyTyped(evt);
            }
        });

        jlbIdUsuario.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdUsuario.setForeground(new java.awt.Color(153, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ingreso Usuario: ");

        txtIngreso_Usuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIngreso_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIngreso_UsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngreso_UsuarioKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Contraseña:");

        txtIngreso_Contrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIngreso_Contrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIngreso_ContrasenaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIngreso_ContrasenaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngreso_ContrasenaKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tipo Usuario:");

        btn_MostrarM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM.setText("Back Menu ");
        btn_MostrarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MostrarMSalirMenu(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btn_MostrarM2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM2.setText("Salir");
        btn_MostrarM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MostrarM2SalirMenu(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addGap(112, 112, 112)
                .addComponent(btn_MostrarM)
                .addGap(36, 36, 36)
                .addComponent(btn_MostrarM2)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btn_MostrarM)
                    .addComponent(btn_MostrarM2))
                .addGap(14, 14, 14))
        );

        btngroupTipo.add(rdAdministrador);
        rdAdministrador.setText("Administrador");
        rdAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAdministradorActionPerformed(evt);
            }
        });

        btngroupTipo.add(rdPersonaS);
        rdPersonaS.setText("Personal Salud");
        rdPersonaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdPersonaSActionPerformed(evt);
            }
        });

        tbUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuario);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID Usuario:");

        jlbUsuario.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbUsuario.setForeground(new java.awt.Color(153, 0, 0));

        jlbTipo.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbTipo.setForeground(new java.awt.Color(153, 0, 0));

        jlbContraseña.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbContraseña.setForeground(new java.awt.Color(153, 0, 0));

        btngroupTipo.add(rdRecepcionista);
        rdRecepcionista.setText("Recepcionista");
        rdRecepcionista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdRecepcionistaActionPerformed(evt);
            }
        });

        Nombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nombre.setText("Nombre: ");

        txtAp_Paterno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAp_Paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAp_PaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAp_PaternoKeyTyped(evt);
            }
        });

        AP_Paterno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AP_Paterno.setText("Apellido Paterno: ");

        txtAp_Materno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAp_Materno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAp_MaternoActionPerformed(evt);
            }
        });
        txtAp_Materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAp_MaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAp_MaternoKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        AP_Paterno1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        AP_Paterno1.setText("Apellido Materno: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(AP_Paterno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAp_Paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtId_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68))
                                    .addComponent(txtIngreso_Usuario)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(Nombre)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jlbIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlbContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(449, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtIngreso_Contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdAdministrador)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdPersonaS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdRecepcionista)))
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(AP_Paterno1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAp_Materno, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(126, 126, 126)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(565, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbIdUsuario)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtIngreso_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtIngreso_Contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jlbUsuario)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdAdministrador)
                            .addComponent(rdPersonaS)
                            .addComponent(rdRecepcionista))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jlbContraseña)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AP_Paterno)
                    .addComponent(txtAp_Paterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jlbTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AP_Paterno1)
                    .addComponent(txtAp_Materno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(119, 119, 119)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(354, Short.MAX_VALUE)))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuarios");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 706, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtId_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_UsuarioKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numero = key >= 48 && key <= 57;

        if (!numero) {
            evt.consume();
        }

        if (txtId_Usuario.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtId_UsuarioKeyTyped

    private void txtIngreso_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngreso_UsuarioKeyTyped
        // TODO add your handling code here:
        if (txtIngreso_Usuario.getText().length() >= 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIngreso_UsuarioKeyTyped

    private void txtIngreso_ContrasenaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngreso_ContrasenaKeyTyped
        // TODO add your handling code here:
        if (txtIngreso_Contrasena.getText().length() >= 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIngreso_ContrasenaKeyTyped

    private void btn_MostrarMSalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarMSalirMenu
        String tipoUsuario = UsuarioContexto.getTipoUsuario();
        String nombreUsuario = UsuarioContexto.getNombreUsuario();
        if (tipoUsuario.equals("A")) {
            MneuPrincipalAdm menu1 = new MneuPrincipalAdm(nombreUsuario);
            menu1.setVisible(true);
        } else if (tipoUsuario.equals("P")) {
            MenuPrincipalPersonal menu = new MenuPrincipalPersonal(nombreUsuario);
            menu.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btn_MostrarMSalirMenu

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (txtId_Usuario.getText().isEmpty() || txtIngreso_Usuario.getText().isEmpty() || txtIngreso_Contrasena.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        } else {
            char tipo = ' '; // Inicialización predeterminada, podría usar otro valor que indique "sin seleccionar"

            if (rdAdministrador.isSelected()) {
                tipo = 'A';
            } else if (rdPersonaS.isSelected()) {
                tipo = 'P';
            } else if (rdRecepcionista.isSelected()) {
                tipo = 'R';
            }

            if (tipo == ' ') {
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de usuario.");
                return; // Detiene la ejecución si no se seleccionó un tipo
            }

            Usuario user = new Usuario();
            user.setId_Usuario(Integer.parseInt(txtId_Usuario.getText()));
            user.setIngreso_usuario(txtIngreso_Usuario.getText());
            user.setIngreso_contrasena(txtIngreso_Contrasena.getText());
            user.setTipo_usuario(tipo);
            user.setNombre(txtNombre.getText());
            user.setAp_Paterno(txtAp_Paterno.getText());
            user.setAp_Materno(txtAp_Materno.getText());
            String mensaje = userbo.agregarUsuario(user);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarUsuario();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtId_Usuario.getText().isEmpty() || txtIngreso_Usuario.getText().isEmpty() || txtIngreso_Contrasena.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        } else {
            char tipo = ' '; // Inicialización predeterminada, podría usar otro valor que indique "sin seleccionar"

            if (rdAdministrador.isSelected()) {
                tipo = 'A';
            } else if (rdPersonaS.isSelected()) {
                tipo = 'P';
            } else if (rdRecepcionista.isSelected()) {
                tipo = 'R';
            }

            if (tipo == ' ') {
                JOptionPane.showMessageDialog(null, "Seleccione un tipo de usuario.");
                return; // Detiene la ejecución si no se seleccionó un tipo
            }

            Usuario user = new Usuario();
            user.setId_Usuario(Integer.parseInt(txtId_Usuario.getText()));
            user.setIngreso_usuario(txtIngreso_Usuario.getText());
            user.setIngreso_contrasena(txtIngreso_Contrasena.getText());
            user.setTipo_usuario(tipo);
            user.setNombre(txtNombre.getText());
            user.setAp_Paterno(txtAp_Paterno.getText());
            user.setAp_Materno(txtAp_Materno.getText());
            String mensaje = userbo.modificarUsuario(user);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarUsuario();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (txtId_Usuario.getText().isEmpty() || txtIngreso_Usuario.getText().isEmpty() || txtIngreso_Contrasena.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        } else {

            String mensaje = userbo.eliminarUsuario(Integer.parseInt(txtId_Usuario.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarUsuario();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btn_MostrarM2SalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarM2SalirMenu
        System.exit(0);
    }//GEN-LAST:event_btn_MostrarM2SalirMenu

    private void rdAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAdministradorActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdAdministradorActionPerformed

    private void tbUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuarioMouseClicked
        int seleccion = tbUsuario.rowAtPoint(evt.getPoint());
        txtId_Usuario.setText(tbUsuario.getValueAt(seleccion, 0) + "");
        txtIngreso_Usuario.setText(tbUsuario.getValueAt(seleccion, 1) + "");
        txtIngreso_Contrasena.setText(tbUsuario.getValueAt(seleccion, 2) + "");
        String tipo = tbUsuario.getValueAt(seleccion, 3) + "";
        txtNombre.setText(tbUsuario.getValueAt(seleccion, 4) + "");
        txtAp_Paterno.setText(tbUsuario.getValueAt(seleccion, 5) + "");
        txtAp_Materno.setText(tbUsuario.getValueAt(seleccion, 4) + "");
        if (tipo.equals("A")) {
            rdAdministrador.setSelected(true);
        } else {
            rdPersonaS.setSelected(true);
        }
        validar();
    }//GEN-LAST:event_tbUsuarioMouseClicked

    private void txtId_UsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_UsuarioKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_UsuarioKeyReleased

    private void txtIngreso_UsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngreso_UsuarioKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtIngreso_UsuarioKeyReleased

    private void txtIngreso_ContrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngreso_ContrasenaKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIngreso_ContrasenaKeyPressed

    private void rdPersonaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdPersonaSActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdPersonaSActionPerformed

    private void txtIngreso_ContrasenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngreso_ContrasenaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtIngreso_ContrasenaKeyReleased

    private void rdRecepcionistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdRecepcionistaActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdRecepcionistaActionPerformed

    private void txtAp_PaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAp_PaternoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp_PaternoKeyReleased

    private void txtAp_PaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAp_PaternoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp_PaternoKeyTyped

    private void txtAp_MaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAp_MaternoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp_MaternoKeyReleased

    private void txtAp_MaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAp_MaternoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp_MaternoKeyTyped

    private void txtAp_MaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAp_MaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp_MaternoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    public void limpiar() {
        txtId_Usuario.setText("");
        txtIngreso_Usuario.setText("");
        txtIngreso_Contrasena.setText("");
        btngroupTipo.clearSelection();

        idMax();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AP_Paterno;
    private javax.swing.JLabel AP_Paterno1;
    private javax.swing.JLabel LogoP;
    private javax.swing.JLabel Nombre;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btn_MostrarM;
    private javax.swing.JButton btn_MostrarM2;
    private javax.swing.ButtonGroup btngroupTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbContraseña;
    private javax.swing.JLabel jlbIdUsuario;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JRadioButton rdAdministrador;
    private javax.swing.JRadioButton rdPersonaS;
    private javax.swing.JRadioButton rdRecepcionista;
    private javax.swing.JTable tbUsuario;
    private javax.swing.JTextField txtAp_Materno;
    private javax.swing.JTextField txtAp_Paterno;
    private javax.swing.JTextField txtId_Usuario;
    private javax.swing.JTextField txtIngreso_Contrasena;
    private javax.swing.JTextField txtIngreso_Usuario;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
