/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;

import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MenuSecundarioAdm;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.PacienteBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Alumno;
import com.ConsultasITS.mx.entity.Carrera;
import com.ConsultasITS.mx.entity.Paciente;
import com.ConsultasITS.mx.entity.Persona;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author kevin
 */
public class FrmPaciente extends javax.swing.JFrame {

    /**
     * Creates new form FrmPaciente
     */
    public FrmPaciente() {
        initComponents();
        listarPaciente();
        idMax();
        setTitle("FORMULARIO PACIENTE");
        setLocationRelativeTo(null);
        setResizable(false);
        btnAgregar.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnAgregar.setEnabled(false);
        consultaIdPersona(cbIdPersona);
        initComponents1();
        initComponents2();
    }
     public void initComponents2(){
        // Llama a validar cuando se seleccione una opción en el ComboBox de Persona
    cbIdPersona.addItemListener(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        validar();  // Llama a validar al cambiar la selección
    }
});
    }
     public static void consultaIdPersona(JComboBox<Persona> cbIdPersona){
        Connection conn=Conexion.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="SELECT ID_PERSONA, NOMBRE_PERSONA, PATERNO_PERSONA FROM PERSONA";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cbIdPersona.addItem(new Persona(0, "Seleccione", "", "", 0, null, ' ', 0));
            while(rs.next()){
                int idPersona=rs.getInt("ID_PERSONA");
                String nombre=rs.getString("NOMBRE_PERSONA");
                String paterno=rs.getString("PATERNO_PERSONA");
                cbIdPersona.addItem(new Persona(idPersona, nombre, paterno, "", 0, null, ' ', 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
      public void initComponents1(){
        txtId_Paciente.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarYCargarPaciente(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validarYCargarPaciente(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarYCargarPaciente(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
     public void validarYCargarPaciente(){
         String idTexto=txtId_Paciente.getText();
         if(!idTexto.isEmpty() && idTexto.matches("\\d+")){
             int idPaciente=Integer.parseInt(idTexto);
             Paciente paciente = paciebo.buscarPacientePorId(idPaciente);
             if(paciente!=null){
                 txtPeso_Paciente.setText(String.valueOf(paciente.getPeso_paciente()));
                 txtEstatura_Paciente.setText(String.valueOf(paciente.getEstatura_Paciente()));
                 for (int i = 0; i < cbIdPersona.getItemCount(); i++) {
                Persona persona = cbIdPersona.getItemAt(i);
                if (persona.getId_Persona() == paciente.getId_Persona()) {
                    cbIdPersona.setSelectedIndex(i);  // Selecciona la persona correcta
                    break;
                }
            }
                 
             }else{
                 txtPeso_Paciente.setText("");
                 txtEstatura_Paciente.setText("");
                 cbIdPersona.setSelectedIndex(0);
             }
         }else{
             txtPeso_Paciente.setText("");
             txtEstatura_Paciente.setText("");
               cbIdPersona.setSelectedIndex(0);
         }
     }
    public void validar(){
        if(txtId_Paciente.getText().isEmpty()){
            jlbIdPaciente.setText("*Obligatorio");
        }else{
            jlbIdPaciente.setText("");
        }
        if(txtPeso_Paciente.getText().isEmpty()){
            jlbPeso.setText("*Obligatorio");
        }else{
            jlbPeso.setText("");
        }
        if(txtEstatura_Paciente.getText().isEmpty()){
            jlbEstatura.setText("*Obligatorio");
        }else{
            jlbEstatura.setText("");
        }
        Persona selectedPersona = (Persona) cbIdPersona.getSelectedItem();
        if(selectedPersona==null || selectedPersona.getId_Persona()==0){
            jlbIdPersona.setText("*Obligatorio");
        }else{
            jlbIdPersona.setText("");
        }
        if(txtId_Paciente.getText().isEmpty() || txtPeso_Paciente.getText().isEmpty() || txtEstatura_Paciente.getText().isEmpty() || selectedPersona==null || selectedPersona.getId_Persona()==0 ){
            btnAgregar.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnAgregar.setEnabled(false);
        }else{
            btnAgregar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        }
    }
    private PacienteBO paciebo = new PacienteBO();
    public void listarPaciente(){
        paciebo.listarPaciente(tbPaciente);
    }
    public void idMax(){
        txtId_Paciente.setText(paciebo.getMaxID()+"");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtId_Paciente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPeso_Paciente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEstatura_Paciente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_MostrarM = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btn_MostrarM1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPaciente = new javax.swing.JTable();
        jlbIdPaciente = new javax.swing.JLabel();
        jlbPeso = new javax.swing.JLabel();
        jlbEstatura = new javax.swing.JLabel();
        jlbIdPersona = new javax.swing.JLabel();
        cbIdPersona = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        LogoP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtId_Paciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId_Paciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId_Paciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_PacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_PacienteKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID Paciente");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Peso Paciente:");

        txtPeso_Paciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPeso_Paciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPeso_PacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPeso_PacienteKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Estatura Paciente:");

        txtEstatura_Paciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEstatura_Paciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstatura_PacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstatura_PacienteKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("ID Persona:");

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

        btn_MostrarM1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM1.setText("Salir");
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
                .addGap(24, 24, 24)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btn_MostrarM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_MostrarM1)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btn_MostrarM1)
                    .addComponent(btn_MostrarM))
                .addGap(17, 17, 17))
        );

        tbPaciente.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPaciente);

        jlbIdPaciente.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdPaciente.setForeground(new java.awt.Color(204, 0, 0));

        jlbPeso.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbPeso.setForeground(new java.awt.Color(204, 0, 0));

        jlbEstatura.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbEstatura.setForeground(new java.awt.Color(204, 0, 0));

        jlbIdPersona.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdPersona.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbIdPersona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstatura_Paciente, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(txtPeso_Paciente)
                            .addComponent(txtId_Paciente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbIdPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbIdPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbEstatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbPeso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId_Paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jlbIdPaciente))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPeso_Paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jlbPeso))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstatura_Paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jlbEstatura))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jlbIdPersona)
                            .addComponent(cbIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Paciente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacienteMouseClicked
        int seleccion=tbPaciente.rowAtPoint(evt.getPoint());
        txtId_Paciente.setText(tbPaciente.getValueAt(seleccion, 0)+"");
        txtPeso_Paciente.setText(tbPaciente.getValueAt(seleccion, 1)+"");
        txtEstatura_Paciente.setText(tbPaciente.getValueAt(seleccion, 2)+"");
        
        validar();
        
    }//GEN-LAST:event_tbPacienteMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtId_Paciente.getText().isEmpty() || txtPeso_Paciente.getText().isEmpty() ||  txtEstatura_Paciente.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            Persona personaSeleccionada = (Persona) cbIdPersona.getSelectedItem();
        int idPersonaSeleccionada = personaSeleccionada.getId_Persona();
        int id_Usuario=UsuarioContexto.getId_Usuario();
            Paciente pacie = new Paciente();
            pacie.setId_Paciente(Integer.parseInt(txtId_Paciente.getText()));
            pacie.setPeso_paciente(Integer.parseInt(txtPeso_Paciente.getText()));
            pacie.setEstatura_Paciente(Integer.parseInt(txtEstatura_Paciente.getText()));
            pacie.setId_Persona(idPersonaSeleccionada);
            pacie.setId_Usuario(id_Usuario);
            String mensaje=paciebo.agregarPaciente(pacie);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPaciente();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtId_Paciente.getText().isEmpty() || txtPeso_Paciente.getText().isEmpty() ||  txtEstatura_Paciente.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            Persona personaSeleccionada = (Persona) cbIdPersona.getSelectedItem();
        int idPersonaSeleccionada = personaSeleccionada.getId_Persona();
        int id_Usuario=UsuarioContexto.getId_Usuario();
            Paciente pacie = new Paciente();
            pacie.setId_Paciente(Integer.parseInt(txtId_Paciente.getText()));
            pacie.setPeso_paciente(Integer.parseInt(txtPeso_Paciente.getText()));
            pacie.setEstatura_Paciente(Integer.parseInt(txtEstatura_Paciente.getText()));
            pacie.setId_Persona(idPersonaSeleccionada);
            pacie.setId_Usuario(id_Usuario);
            String mensaje=paciebo.modificarPaciente(pacie);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPaciente();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(txtId_Paciente.getText().isEmpty() || txtPeso_Paciente.getText().isEmpty() ||  txtEstatura_Paciente.getText().isEmpty()
            ){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String mensaje=paciebo.eliminarPaciente(Integer.parseInt(txtId_Paciente.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarPaciente();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btn_MostrarMSalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarMSalirMenu
        String tipoUsuario=UsuarioContexto.getTipoUsuario();
         String nombreUsuario=UsuarioContexto.getNombreUsuario();
        if(tipoUsuario.equals("A")){
            MenuSecundarioAdm menu1 = new MenuSecundarioAdm(nombreUsuario);
            menu1.setVisible(true);
        }else if(tipoUsuario.equals("P")){
            MenuPrincipalPersonal menu = new MenuPrincipalPersonal(nombreUsuario);
            menu.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btn_MostrarMSalirMenu

    private void btn_MostrarM1SalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarM1SalirMenu
        System.exit(0);
    }//GEN-LAST:event_btn_MostrarM1SalirMenu

    private void txtPeso_PacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso_PacienteKeyTyped
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtPeso_Paciente.getText().trim().length()== 3){
            evt.consume();
        }
    }//GEN-LAST:event_txtPeso_PacienteKeyTyped

    private void txtEstatura_PacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstatura_PacienteKeyTyped
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtEstatura_Paciente.getText().trim().length()== 3){
            evt.consume();
        }
    }//GEN-LAST:event_txtEstatura_PacienteKeyTyped

    private void txtId_PacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_PacienteKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtId_Paciente.getText().trim().length()== 10){
            evt.consume();
        }
    }//GEN-LAST:event_txtId_PacienteKeyTyped

    private void txtId_PacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_PacienteKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_PacienteKeyReleased

    private void txtPeso_PacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPeso_PacienteKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtPeso_PacienteKeyReleased

    private void txtEstatura_PacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstatura_PacienteKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtEstatura_PacienteKeyReleased

    public void limpiar(){
        txtId_Paciente.setText("");
        txtPeso_Paciente.setText("");
        txtEstatura_Paciente.setText("");
        cbIdPersona.setSelectedIndex(-1);
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
            java.util.logging.Logger.getLogger(FrmPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPaciente().setVisible(true);
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
    private javax.swing.JComboBox<Persona> cbIdPersona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbEstatura;
    private javax.swing.JLabel jlbIdPaciente;
    private javax.swing.JLabel jlbIdPersona;
    private javax.swing.JLabel jlbPeso;
    private javax.swing.JTable tbPaciente;
    private javax.swing.JTextField txtEstatura_Paciente;
    private javax.swing.JTextField txtId_Paciente;
    private javax.swing.JTextField txtPeso_Paciente;
    // End of variables declaration//GEN-END:variables
}
