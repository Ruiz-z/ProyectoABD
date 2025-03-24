/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;

import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.Personal_SaludBO;
import com.ConsultasITS.mx.entity.Personal_Salud;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author kevin
 */
public class FrmPersonal_Sal extends javax.swing.JFrame {

    private String usuario=UsuarioContexto.getNombreReal();
    private String Paterno = UsuarioContexto.getAp_Paterno();
      private String Materno = UsuarioContexto.getAp_Materno();
    /**
     * Creates new form FrmPersonal_Sal
     */
    public FrmPersonal_Sal() {
        initComponents();
        setTitle("FORMULARIO PERSONAL SALUD");
        setLocationRelativeTo(null);
        setResizable(false);
        jlbMensaje.setText("Bienvenido: " + usuario +Paterno+Materno);
        listarPersonal_Salud();
        idMax();
        Calendar cal =  Calendar.getInstance();
         String fecha, hora;
         fecha=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
         hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE);
         this.FECHA.setText(fecha);
         this.HORA.setText(hora);
         btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            initComponents1();
            txtId_Personal_Salud.setEditable(true);
    }

    private Personal_SaludBO persoSbo = new Personal_SaludBO();
    
    public void listarPersonal_Salud(){
        persoSbo.listarPersonal_Salud(tbPersonal_Salud);
    }
    public void idMax(){
        txtId_Personal_Salud.setText(persoSbo.getMaxID()+"");
    }
    public void limpiar(){
        txtId_Personal_Salud.setText("");
        txtNombre.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtMaterno.setText("");
        groupTipo.clearSelection();
        idMax();
    }
    public void validar(){
        if(txtId_Personal_Salud.getText().isEmpty()){
            jlbIdPersonal.setText("*Obligatorio, El dato se incrementa automaticamente");
        }else{
            jlbIdPersonal.setText("");
        }
        if(txtNombre.getText().isEmpty()){
            jlbNombre.setText("*Obligatorio");
        }else{
            jlbNombre.setText("");
        }
        if(txtPaterno.getText().isEmpty()){
            jlbPaterno.setText("*Obligatorio");
        }else{
            jlbPaterno.setText("");
        }
        if(txtMaterno.getText().isEmpty()){
            jlbMaterno.setText("*Obligatorio");
        }else{
            jlbMaterno.setText("");
        }
        if(txtEdad.getText().isEmpty()){
            jlbEdad.setText("*Obligatorio");
        }else{
            jlbEdad.setText("");
        }
        
        
        if(!(rdDoctor.isSelected() || rdEnfermera.isSelected())){
            jlbTipo.setText("*Obligatorio");
        }else{
            jlbTipo.setText("");
        }
        if(txtId_Personal_Salud.getText().isEmpty() || txtNombre.getText().isEmpty() ||  txtPaterno.getText().isEmpty() || txtMaterno.getText().isEmpty() || txtEdad.getText().isEmpty()
              || !(rdDoctor.isSelected() || rdEnfermera.isSelected())){
            btnGuardar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }else{
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }
    public void initComponents1(){
        txtId_Personal_Salud.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarYCargarPersonal(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validarYCargarPersonal(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarYCargarPersonal(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
     public void validarYCargarPersonal(){
         String idTexto=txtId_Personal_Salud.getText();
         if(!idTexto.isEmpty() && idTexto.matches("\\d+")){
             int idPersonal=Integer.parseInt(idTexto);
             Personal_Salud personal = persoSbo.buscarPersonalPorId(idPersonal);
             if(personal!=null){
                 txtNombre.setText(personal.getNombre_Personal_Salud());
                 txtPaterno.setText(personal.getPaterno_Personal());
                 txtMaterno.setText(personal.getMaterno_Personal());
                 txtEdad.setText(String.valueOf(personal.getEdad_Personal_Salud()));
                 if("Doctor".equals(personal.getTipo_Personal_Salud())){
                     rdDoctor.setSelected(true);
                 }else if("Enfermero(a)".equals(personal.getTipo_Personal_Salud())){
                     rdEnfermera.setSelected(true);
                 }
             }else{
                 txtNombre.setText("");
                 txtPaterno.setText("");
                 txtMaterno.setText("");
                 txtEdad.setText("");
                 rdDoctor.setSelected(false);
                 rdEnfermera.setSelected(false);
             }
             
         }else{
             txtNombre.setText("");
                 txtPaterno.setText("");
                 txtMaterno.setText("");
                 txtEdad.setText("");
                 rdDoctor.setSelected(false);
                 rdEnfermera.setSelected(false);
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

        groupTipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtId_Personal_Salud = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtMaterno = new javax.swing.JTextField();
        txtPaterno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdDoctor = new javax.swing.JRadioButton();
        rdEnfermera = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPersonal_Salud = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jlbNombre = new javax.swing.JLabel();
        jlbPaterno = new javax.swing.JLabel();
        jlbMaterno = new javax.swing.JLabel();
        jlbIdPersonal = new javax.swing.JLabel();
        jlbEdad = new javax.swing.JLabel();
        jlbTipo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        HORA = new javax.swing.JLabel();
        FECHA = new javax.swing.JLabel();
        jlbMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel1KeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("ID Personal Salud:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nombre:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Materno:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Paterno:");

        txtId_Personal_Salud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId_Personal_SaludActionPerformed(evt);
            }
        });
        txtId_Personal_Salud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_Personal_SaludKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_Personal_SaludKeyTyped(evt);
            }
        });

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

        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        txtMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaternoActionPerformed(evt);
            }
        });
        txtMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaternoKeyTyped(evt);
            }
        });

        txtPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaternoActionPerformed(evt);
            }
        });
        txtPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaternoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Edad:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Tipo Personal:");

        groupTipo.add(rdDoctor);
        rdDoctor.setText("Doctor");
        rdDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDoctorActionPerformed(evt);
            }
        });
        rdDoctor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdDoctorKeyReleased(evt);
            }
        });

        groupTipo.add(rdEnfermera);
        rdEnfermera.setText("Enfermer@");
        rdEnfermera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdEnfermeraActionPerformed(evt);
            }
        });
        rdEnfermera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdEnfermeraKeyReleased(evt);
            }
        });

        tbPersonal_Salud.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPersonal_Salud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPersonal_SaludMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPersonal_Salud);

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setText("<--");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir)
                    .addComponent(btnLimpiar)
                    .addComponent(btnBack))
                .addGap(17, 17, 17))
        );

        jlbNombre.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbNombre.setForeground(new java.awt.Color(204, 0, 0));

        jlbPaterno.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbPaterno.setForeground(new java.awt.Color(204, 0, 0));

        jlbMaterno.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbMaterno.setForeground(new java.awt.Color(204, 0, 0));

        jlbIdPersonal.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbIdPersonal.setForeground(new java.awt.Color(204, 0, 0));

        jlbEdad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbEdad.setForeground(new java.awt.Color(204, 0, 0));

        jlbTipo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbTipo.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId_Personal_Salud, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdDoctor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdEnfermera)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                    .addComponent(jlbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPaterno, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtId_Personal_Salud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlbIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jlbPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlbEdad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(rdDoctor)
                        .addComponent(rdEnfermera))
                    .addComponent(jlbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PERSONAL SALUD");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FECHA:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("HORA:");

        HORA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        HORA.setForeground(new java.awt.Color(255, 255, 255));
        HORA.setText("00:00");

        FECHA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        FECHA.setForeground(new java.awt.Color(255, 255, 255));
        FECHA.setText("00/00/00");

        jlbMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbMensaje.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HORA)
                .addGap(35, 35, 35))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(FECHA)
                        .addComponent(jLabel3)
                        .addComponent(HORA)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtId_Personal_SaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId_Personal_SaludActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId_Personal_SaludActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaternoActionPerformed

    private void txtPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaternoActionPerformed

    private void rdDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDoctorActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdDoctorActionPerformed

    private void rdDoctorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdDoctorKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdDoctorKeyReleased

    private void rdEnfermeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdEnfermeraActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdEnfermeraActionPerformed

    private void rdEnfermeraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdEnfermeraKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdEnfermeraKeyReleased

    private void tbPersonal_SaludMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPersonal_SaludMouseClicked
        int seleccion=tbPersonal_Salud.rowAtPoint(evt.getPoint());
        txtId_Personal_Salud.setText(tbPersonal_Salud.getValueAt(seleccion, 0)+"");
        txtNombre.setText(tbPersonal_Salud.getValueAt(seleccion, 1)+"");
        txtPaterno.setText(tbPersonal_Salud.getValueAt(seleccion, 2)+"");
        txtMaterno.setText(tbPersonal_Salud.getValueAt(seleccion, 3)+"");
        String tipo=tbPersonal_Salud.getValueAt(seleccion, 4)+"";
        if(tipo.equals("Doctor")){
            rdDoctor.setSelected(true);
        }else{
            rdEnfermera.setSelected(true);
        }
        validar();        
    }//GEN-LAST:event_tbPersonal_SaludMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(txtId_Personal_Salud.getText().isEmpty() || txtNombre.getText().isEmpty() ||  txtPaterno.getText().isEmpty() || txtMaterno.getText().isEmpty() || txtEdad.getText().isEmpty()
              || !(rdDoctor.isSelected() || rdEnfermera.isSelected())){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String tipo;
            if(rdDoctor.isSelected()){
                tipo="Doctor";
            }else{
                tipo="Enfermero(a)";
            }
            int id_Usuario=UsuarioContexto.getId_Usuario();
            Personal_Salud persoS = new Personal_Salud();
            persoS.setId_Personal_Salud(Integer.parseInt(txtId_Personal_Salud.getText()));
            persoS.setNombre_Personal_Salud(txtNombre.getText());
            persoS.setPaterno_Personal(txtPaterno.getText());
            persoS.setMaterno_Personal(txtMaterno.getText());
            persoS.setEdad_Personal_Salud(Integer.parseInt(txtEdad.getText()));
            persoS.setTipo_Personal_Salud(tipo);
            persoS.setId_Usuario(id_Usuario);
           
            String mensaje=persoSbo.agregarPersonal_Salud(persoS);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPersonal_Salud();
        }        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtId_Personal_Salud.getText().isEmpty() || txtNombre.getText().isEmpty() ||  txtPaterno.getText().isEmpty() || txtMaterno.getText().isEmpty() || txtEdad.getText().isEmpty()
              || !(rdDoctor.isSelected() || rdEnfermera.isSelected())){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String tipo;
            if(rdDoctor.isSelected()){
                tipo="Doctor";
            }else{
                tipo="Enfermero(a)";
            }
            int id_Usuario=UsuarioContexto.getId_Usuario();
            Personal_Salud persoS = new Personal_Salud();
            persoS.setId_Personal_Salud(Integer.parseInt(txtId_Personal_Salud.getText()));
            persoS.setNombre_Personal_Salud(txtNombre.getText());
            persoS.setPaterno_Personal(txtPaterno.getText());
            persoS.setMaterno_Personal(txtMaterno.getText());
            persoS.setEdad_Personal_Salud(Integer.parseInt(txtEdad.getText()));
            persoS.setTipo_Personal_Salud(tipo);
            persoS.setId_Usuario(id_Usuario);
           
            String mensaje=persoSbo.modificarPersonal_Salud(persoS);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPersonal_Salud();
        } 
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       if(txtId_Personal_Salud.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE EL CAMPO ID");
        }else{
            String mensaje=persoSbo.eliminarPersonal_Salud(Integer.parseInt(txtId_Personal_Salud.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPersonal_Salud();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        String tipoUsuario=UsuarioContexto.getTipoUsuario();
        String nombreUsuario=UsuarioContexto.getNombreUsuario();
        if(tipoUsuario.equals("A")){
            MneuPrincipalAdm menu1 = new MneuPrincipalAdm(nombreUsuario);
            menu1.setVisible(true);
        }else if(tipoUsuario.equals("P")){
            MenuPrincipalPersonal menu = new MenuPrincipalPersonal(nombreUsuario);
            menu.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtId_Personal_SaludKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_Personal_SaludKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_Personal_SaludKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtPaternoKeyReleased

    private void txtMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaternoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtMaternoKeyReleased

    private void txtEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtEdadKeyReleased

    private void txtId_Personal_SaludKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_Personal_SaludKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtId_Personal_Salud.getText().trim().length()== 10){
            evt.consume();
        }
    }//GEN-LAST:event_txtId_Personal_SaludKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if(txtNombre.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoKeyTyped
        // TODO add your handling code here:
        if(txtPaterno.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtPaternoKeyTyped

    private void txtMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaternoKeyTyped
        // TODO add your handling code here:
        if(txtMaterno.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtMaternoKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtEdad.getText().trim().length()== 3){
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyTyped

    private void jPanel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1KeyTyped

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
            java.util.logging.Logger.getLogger(FrmPersonal_Sal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPersonal_Sal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPersonal_Sal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPersonal_Sal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPersonal_Sal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FECHA;
    private javax.swing.JLabel HORA;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup groupTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbEdad;
    private javax.swing.JLabel jlbIdPersonal;
    private javax.swing.JLabel jlbMaterno;
    private javax.swing.JLabel jlbMensaje;
    private javax.swing.JLabel jlbNombre;
    private javax.swing.JLabel jlbPaterno;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JRadioButton rdDoctor;
    private javax.swing.JRadioButton rdEnfermera;
    private javax.swing.JTable tbPersonal_Salud;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtId_Personal_Salud;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaterno;
    // End of variables declaration//GEN-END:variables
}
