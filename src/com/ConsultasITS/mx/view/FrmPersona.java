
package com.ConsultasITS.mx.view;

import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.PersonaBO;
import com.ConsultasITS.mx.entity.Persona;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class FrmPersona extends javax.swing.JFrame {

   
    
    public FrmPersona() {
        initComponents();
        listarPersona();
        idMax();
        setTitle("FORMULARIO PERSONA");
        setLocationRelativeTo(null);
        setResizable(false);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
       initComponents1();
    }
    public void initComponents1(){
        calenderFecha.getDateEditor().addPropertyChangeListener("date", evt -> {
    validar();
});
        txtId_Persona.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarYCargarPersona(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validarYCargarPersona(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarYCargarPersona(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    public void validarYCargarPersona(){
        String idTexto=txtId_Persona.getText();
        if(!idTexto.isEmpty() && idTexto.matches("\\d+")){
            int idPersona=Integer.parseInt(idTexto);
            Persona persona = persobo.buscarpersonaPorId(idPersona);
            if(persona!=null){
                txtNombre_Persona.setText(persona.getNombre_Persona());
                txtPaterno_Persona.setText(persona.getPaterno_Persona());
                txtMetrno_Persona.setText(persona.getMaterno_Persona());
                txtEdad_Persona.setText(String.valueOf(persona.getEdad_Persona()));
                // Asignar la fecha directamente al JCalendarComboBox
            Date fecha_Nacimiento = persona.getFecha_nacimiento();
            calenderFecha.setDate(fecha_Nacimiento);
                if(persona.getGenero_Persona()=='M'){
                    rdMasculino.setSelected(true);
                }else if(persona.getGenero_Persona()=='F'){
                    rdFemenino.setSelected(true);
                }
            }else{
                txtNombre_Persona.setText("");
                txtPaterno_Persona.setText("");
                txtMetrno_Persona.setText("");
                txtEdad_Persona.setText("");
                calenderFecha.setDate(null);
                rdMasculino.setSelected(false);
                rdFemenino.setSelected(false);
            }
        }else{
            txtNombre_Persona.setText("");
                txtPaterno_Persona.setText("");
                txtMetrno_Persona.setText("");
                txtEdad_Persona.setText("");
                calenderFecha.setDate(null);
                rdMasculino.setSelected(false);
                rdFemenino.setSelected(false);
        }
    }
    public void validar(){
        if(txtId_Persona.getText().isEmpty()){
            jlbId.setText("*Obligatorio");
        }else{
            jlbId.setText("");
        }
        if(txtNombre_Persona.getText().isEmpty()){
            jlbNombre.setText("*Obligatorio");
        }else{
            jlbNombre.setText("");
        }
        if(txtPaterno_Persona.getText().isEmpty()){
            jlbPaterno.setText("*Obligatorio");
        }else{
            jlbPaterno.setText("");
        }
        if(txtMetrno_Persona.getText().isEmpty()){
            jlbMaterno.setText("*Obligatorio");
        }else{
            jlbMaterno.setText("");
        }
        if(txtEdad_Persona.getText().isEmpty()){
            jlbEdad.setText("*Obligatorio");
        }else{
            jlbEdad.setText("");
        }
        if(calenderFecha.getDate() == null){
            jlbFecha.setText("*Obligatorio");
        }else{
            jlbFecha.setText("");
        }
        if(rdMasculino.isSelected() || rdFemenino.isSelected()){
            jlbGenero.setText("");
        }else{
            jlbGenero.setText("*Obligatorio");
        }
        
        if(txtId_Persona.getText().isEmpty() || txtNombre_Persona.getText().isEmpty() || txtPaterno_Persona.getText().isEmpty() || txtMetrno_Persona.getText().isEmpty()
                 || txtEdad_Persona.getText().isEmpty() || calenderFecha.getDate() == null || !(rdMasculino.isSelected() || rdFemenino.isSelected()))
               {
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }else{
            btnAgregar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }


    private PersonaBO persobo = new PersonaBO();
    
    public void listarPersona(){
        persobo.listarPersona(tbPersona);
    }
    public void idMax(){
        txtId_Persona.setText(persobo.getMaxID()+"");
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupGenero = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtId_Persona = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEdad_Persona = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPaterno_Persona = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNombre_Persona = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMetrno_Persona = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_MostrarM = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btn_MostrarM1 = new javax.swing.JButton();
        rdFemenino = new javax.swing.JRadioButton();
        rdMasculino = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPersona = new javax.swing.JTable();
        jlbId = new javax.swing.JLabel();
        jlbNombre = new javax.swing.JLabel();
        jlbPaterno = new javax.swing.JLabel();
        jlbMaterno = new javax.swing.JLabel();
        jlbEdad = new javax.swing.JLabel();
        jlbFecha = new javax.swing.JLabel();
        jlbGenero = new javax.swing.JLabel();
        calenderFecha = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        LogoP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtId_Persona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId_Persona.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId_Persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_PersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_PersonaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("ID Persona:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nombre");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Paterno");

        txtEdad_Persona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEdad_Persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdad_PersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdad_PersonaKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Materno");

        txtPaterno_Persona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPaterno_Persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaterno_PersonaActionPerformed(evt);
            }
        });
        txtPaterno_Persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaterno_PersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPaterno_PersonaKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Edad");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Genero");

        txtNombre_Persona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre_Persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombre_PersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre_PersonaKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Fecha Nacimiento");

        txtMetrno_Persona.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMetrno_Persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMetrno_PersonaActionPerformed(evt);
            }
        });
        txtMetrno_Persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMetrno_PersonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMetrno_PersonaKeyTyped(evt);
            }
        });

        btn_MostrarM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM.setText("Back Menu");
        btn_MostrarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MostrarMSalirMenu(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
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

        btn_MostrarM1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM1.setText("SALIR");
        btn_MostrarM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MostrarM1SalirMenu(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(65, 65, 65)
                .addComponent(btn_MostrarM)
                .addGap(18, 18, 18)
                .addComponent(btn_MostrarM1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_MostrarM)
                    .addComponent(btnLimpiar)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btn_MostrarM1))
                .addGap(17, 17, 17))
        );

        groupGenero.add(rdFemenino);
        rdFemenino.setText("Femenino");
        rdFemenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdFemeninoActionPerformed(evt);
            }
        });
        rdFemenino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdFemeninoKeyReleased(evt);
            }
        });

        groupGenero.add(rdMasculino);
        rdMasculino.setText("Masculino");
        rdMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMasculinoActionPerformed(evt);
            }
        });
        rdMasculino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rdMasculinoKeyReleased(evt);
            }
        });

        tbPersona.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPersonaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPersona);

        jlbId.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbId.setForeground(new java.awt.Color(204, 0, 0));

        jlbNombre.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbNombre.setForeground(new java.awt.Color(204, 0, 0));

        jlbPaterno.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbPaterno.setForeground(new java.awt.Color(204, 0, 0));

        jlbMaterno.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbMaterno.setForeground(new java.awt.Color(204, 0, 0));

        jlbEdad.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbEdad.setForeground(new java.awt.Color(204, 0, 0));

        jlbFecha.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbFecha.setForeground(new java.awt.Color(204, 0, 0));

        jlbGenero.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbGenero.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtId_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMetrno_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPaterno_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEdad_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbId, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdMasculino)
                        .addGap(27, 27, 27)
                        .addComponent(rdFemenino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtId_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNombre_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbNombre)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPaterno_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbPaterno)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMetrno_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbMaterno)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtEdad_Persona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbEdad)))
                            .addComponent(jlbId))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(rdMasculino)
                                .addComponent(rdFemenino))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Persona");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPersonaMouseClicked
        int seleccion=tbPersona.rowAtPoint(evt.getPoint());
        txtId_Persona.setText(tbPersona.getValueAt(seleccion, 0)+"");
        txtNombre_Persona.setText(tbPersona.getValueAt(seleccion, 1)+"");
        txtPaterno_Persona.setText(tbPersona.getValueAt(seleccion, 2)+"");
        txtMetrno_Persona.setText(tbPersona.getValueAt(seleccion, 3)+"");
        txtEdad_Persona.setText(tbPersona.getValueAt(seleccion, 4)+"");
        // Convertir la fecha obtenida de la tabla y asignarla al JCalendarComboBox
    String fechaTexto = tbPersona.getValueAt(seleccion, 5) + "";
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); // Ajusta el formato si es necesario
    try {
        Date fecha_Nacimiento = formato.parse(fechaTexto);
        calenderFecha.setDate(fecha_Nacimiento); // Asigna la fecha al JCalendarComboBox
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + e.getMessage());
        calenderFecha.setDate(null); // Limpia la fecha si hay un error
    }
        String genero=tbPersona.getValueAt(seleccion, 6)+"";
        if(genero.equals("M")){
            rdMasculino.setSelected(true);
        }else{
            rdFemenino.setSelected(true);
        }
        
        validar();
    }//GEN-LAST:event_tbPersonaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtId_Persona.getText().isEmpty() || txtNombre_Persona.getText().isEmpty() ||  txtPaterno_Persona.getText().isEmpty() || txtMetrno_Persona.getText().isEmpty() ||txtEdad_Persona.getText().isEmpty()
            || calenderFecha.getDate()==null){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            char genero;
            if(rdMasculino.isSelected()){
                genero='M';
            }else{
                genero='F';
            }
            Date fecha_Nacimiento = calenderFecha.getDate();
        if (fecha_Nacimiento == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
            return;
        }
            int id_Usuario=UsuarioContexto.getId_Usuario();
            Persona perso = new Persona();
            perso.setId_Persona(Integer.parseInt(txtId_Persona.getText()));
            perso.setNombre_Persona(txtNombre_Persona.getText());
            perso.setPaterno_Persona(txtPaterno_Persona.getText());
            perso.setMaterno_Persona(txtMetrno_Persona.getText());
            perso.setEdad_Persona(Integer.parseInt(txtEdad_Persona.getText()));
            perso.setFecha_nacimiento(new java.sql.Date(fecha_Nacimiento.getTime()));
            perso.setGenero_Persona((char)genero);
            perso.setId_Usuario(id_Usuario);
            String mensaje=persobo.agregarPersona(perso);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPersona();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtId_Persona.getText().isEmpty() || txtNombre_Persona.getText().isEmpty() ||  txtPaterno_Persona.getText().isEmpty() || txtMetrno_Persona.getText().isEmpty() ||txtEdad_Persona.getText().isEmpty()
            || calenderFecha.getDate()==null){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            char genero;
            if(rdMasculino.isSelected()){
                genero='M';
            }else{
                genero='F';
            }
            Date fecha_Nacimiento = calenderFecha.getDate();
        if (fecha_Nacimiento == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
            return;
        }
            int id_Usuario=UsuarioContexto.getId_Usuario();
            Persona perso = new Persona();
            perso.setId_Persona(Integer.parseInt(txtId_Persona.getText()));
            perso.setNombre_Persona(txtNombre_Persona.getText());
            perso.setPaterno_Persona(txtPaterno_Persona.getText());
            perso.setMaterno_Persona(txtMetrno_Persona.getText());
            perso.setEdad_Persona(Integer.parseInt(txtEdad_Persona.getText()));
            perso.setFecha_nacimiento(new java.sql.Date(fecha_Nacimiento.getTime()));
            perso.setGenero_Persona((char)genero);
            perso.setId_Usuario(id_Usuario);
            String mensaje=persobo.modificarPersona(perso);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPersona();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(txtId_Persona.getText().isEmpty() || calenderFecha.getDate()==null ||  txtPaterno_Persona.getText().isEmpty() || txtEdad_Persona.getText().isEmpty()
            || txtNombre_Persona.getText().isEmpty() || txtMetrno_Persona.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String mensaje=persobo.eliminarPersona(Integer.parseInt(txtId_Persona.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPersona();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void rdMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMasculinoActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdMasculinoActionPerformed

    private void rdFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdFemeninoActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdFemeninoActionPerformed

    private void btn_MostrarMSalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarMSalirMenu
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
    }//GEN-LAST:event_btn_MostrarMSalirMenu

    private void txtPaterno_PersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaterno_PersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaterno_PersonaActionPerformed

    private void btn_MostrarM1SalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarM1SalirMenu
        System.exit(0);
    }//GEN-LAST:event_btn_MostrarM1SalirMenu

    private void txtMetrno_PersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMetrno_PersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMetrno_PersonaActionPerformed

    private void txtId_PersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_PersonaKeyTyped
       int key = evt.getKeyChar();
       boolean numero=key>=48&&key<=57;
       if(!numero){
           evt.consume();
       }
       if(txtId_Persona.getText().trim().length()==10){
           evt.consume();
       }
    }//GEN-LAST:event_txtId_PersonaKeyTyped

    private void txtNombre_PersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_PersonaKeyTyped
        if(txtNombre_Persona.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombre_PersonaKeyTyped

    private void txtPaterno_PersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaterno_PersonaKeyTyped
        if(txtPaterno_Persona.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtPaterno_PersonaKeyTyped

    private void txtMetrno_PersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMetrno_PersonaKeyTyped
        if(txtMetrno_Persona.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtMetrno_PersonaKeyTyped

    private void txtEdad_PersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdad_PersonaKeyTyped
        int key = evt.getKeyChar();
        boolean numero= key>=48&&key<=57;
        if(!numero){
            evt.consume();
        }
        if(txtEdad_Persona.getText().trim().length()==3){
            evt.consume();
        }
    }//GEN-LAST:event_txtEdad_PersonaKeyTyped

    private void txtId_PersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_PersonaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_PersonaKeyReleased

    private void txtNombre_PersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_PersonaKeyReleased

        validar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre_PersonaKeyReleased

    private void txtPaterno_PersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaterno_PersonaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtPaterno_PersonaKeyReleased

    private void txtMetrno_PersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMetrno_PersonaKeyReleased
validar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtMetrno_PersonaKeyReleased

    
    private void txtEdad_PersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdad_PersonaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtEdad_PersonaKeyReleased

    private void rdMasculinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdMasculinoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdMasculinoKeyReleased

    private void rdFemeninoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdFemeninoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdFemeninoKeyReleased
    public void limpiar(){
        txtId_Persona.setText("");
        calenderFecha.setDate(null);
        txtPaterno_Persona.setText("");
        txtMetrno_Persona.setText("");
        txtEdad_Persona.setText("");
        txtNombre_Persona.setText("");
        groupGenero.clearSelection();
        idMax();
        validar();
    }
 
    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPersona().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LogoP;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btn_MostrarM;
    private javax.swing.JButton btn_MostrarM1;
    private com.toedter.calendar.JDateChooser calenderFecha;
    private javax.swing.ButtonGroup groupGenero;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbEdad;
    private javax.swing.JLabel jlbFecha;
    private javax.swing.JLabel jlbGenero;
    private javax.swing.JLabel jlbId;
    private javax.swing.JLabel jlbMaterno;
    private javax.swing.JLabel jlbNombre;
    private javax.swing.JLabel jlbPaterno;
    private javax.swing.JRadioButton rdFemenino;
    private javax.swing.JRadioButton rdMasculino;
    private javax.swing.JTable tbPersona;
    private javax.swing.JTextField txtEdad_Persona;
    private javax.swing.JTextField txtId_Persona;
    private javax.swing.JTextField txtMetrno_Persona;
    private javax.swing.JTextField txtNombre_Persona;
    private javax.swing.JTextField txtPaterno_Persona;
    // End of variables declaration//GEN-END:variables
}
