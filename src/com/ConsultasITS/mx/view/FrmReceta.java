/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;
import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MenuSecundarioAdm;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.RecetaBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Consulta;
import com.ConsultasITS.mx.entity.Receta;
import static com.ConsultasITS.mx.view.FrmReceta.consultaIdConsulta;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;



/**
 *
 * @author kevin
 */
public class FrmReceta extends javax.swing.JFrame {

    
    /**
     * Creates new form FrmReceta1
     */
    public FrmReceta() {
        initComponents();
        listarReceta();
        idMax();
        setTitle("FORMULARIO DE RECETA");
        setLocationRelativeTo(null);
        setResizable(false);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        consultaIdConsulta(cbIdConsulta);
        initComponents1();
        initComponents2();
    }
public void initComponents2(){
        // Llama a validar cuando se seleccione una opción en el ComboBox de Persona
    cbIdConsulta.addItemListener(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        validar();  // Llama a validar al cambiar la selección
    }
});
    }
    public static void consultaIdConsulta(JComboBox<Consulta> cbIdConsulta){
    Connection conn = Conexion.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT ID_CONSULTA FROM CONSULTA";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        // Agrega el primer ítem por defecto
        cbIdConsulta.addItem(new Consulta(0, null, null, 0, 0, 0,0));

        
        // Recorre el resultado y agrega las consultas
        while (rs.next()) {
            int idConsulta = rs.getInt("ID_CONSULTA");
            cbIdConsulta.addItem(new Consulta(idConsulta, null, null, 0, 0, 0, 0));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
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
        calenderFecha.getDateEditor().addPropertyChangeListener("date", evt -> {
    validar();
});
        txtId_Receta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarYCargarReceta(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validarYCargarReceta(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarYCargarReceta(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    public void validarYCargarReceta(){
        String idTexto=txtId_Receta.getText();
        if(!idTexto.isEmpty() && idTexto.matches("\\d+")){
            int idReceta=Integer.parseInt(idTexto);
            Receta receta=recebo.buscarRecetaPorId(idReceta);
            if(receta!=null){
                // Asignar la fecha directamente al JCalendarComboBox
            Date fecha_Receta = receta.getFecha_Receta();
            calenderFecha.setDate(fecha_Receta);
                txtDosis.setText(receta.getDosis());
                txtAcciones.setText(receta.getFrecuencia());
                txtDuracion.setText(String.valueOf(receta.getDuracion()));
                if(receta.getVia_Admnistracion()=='O'){
                    rdOral.setSelected(true);
                }else if(receta.getVia_Admnistracion()=='I'){
                    rdInyectable.setSelected(true);
                }
                for(int i=0; i<cbIdConsulta.getItemCount(); i++){
                    Consulta consulta=cbIdConsulta.getItemAt(i);
                    if(consulta.getId_Consulta()==receta.getId_Consulta()){
                        cbIdConsulta.setSelectedIndex(i);
                        break;
                    }
                }
            }else{
                limpiarCampos();
            }
        }else{
            limpiarCampos();
        }
    }
    public void limpiarCampos(){
        calenderFecha.setDate(null);
        txtDosis.setText("");
        txtAcciones.setText("");
        txtDuracion.setText("");
        rdOral.setSelected(false);
        rdInyectable.setSelected(false);
        cbIdConsulta.setSelectedIndex(0);
    }
    public void validar(){
        if(txtId_Receta.getText().isEmpty()){
            jlbIdReceta.setText("*Obligatorio");
        }else{
            jlbIdReceta.setText("");
        }
        if(calenderFecha.getDate()==null){
            jlbFecha.setText("*Obligatorio");
        }else{
            jlbFecha.setText("");
        }
        if(txtDosis.getText().isEmpty()){
            jlbDosis.setText("*Obligatorio");
        }else{
            jlbDosis.setText("");
        }
        if(txtFrecuencia.getText().isEmpty()){
            jlbFrecuencia.setText("*Obligatorio");
        }else{
            jlbFrecuencia.setText("");
        }
        if(txtDuracion.getText().isEmpty()){
            jlbDuracion.setText("*Obligatorio");
        }else{
            jlbDuracion.setText("");
        }
        Consulta selectedConsulta=(Consulta) cbIdConsulta.getSelectedItem();
        if(selectedConsulta==null || selectedConsulta.getId_Consulta()==0){
            jlbIdConsulta.setText("*Obligatorio");
        }else{
            jlbIdConsulta.setText("");
        }
        if(rdOral.isSelected() || rdInyectable.isSelected()){
            jlbVia.setText("");
        }else{
            jlbVia.setText("*Obligatorio");
        }
        if(txtDiagnostico.getText().isEmpty()){
            jlbDiagnostico.setText("*Obligatorio");
        }else{
            jlbDiagnostico.setText("");
        }
        if(txtAcciones.getText().isEmpty()){
            jlbAcciones.setText("*Obligatorio");
        }else{
            jlbAcciones.setText("");
        }
        if(txtId_Receta.getText().isEmpty() || calenderFecha.getDate()==null || txtDosis.getText().isEmpty()
                || txtAcciones.getText().isEmpty() || txtDuracion.getText().isEmpty()
                ||  !(rdOral.isSelected() || rdInyectable.isSelected())
                || selectedConsulta==null || selectedConsulta.getId_Consulta()==0){
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }else{
            btnAgregar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
        
    }

    private RecetaBO recebo = new RecetaBO();
    public void listarReceta(){
        recebo.listarAlumno(tbReceta);
    }
    public void idMax(){
        txtId_Receta.setText(recebo.getMaxID()+"");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupVia = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtId_Receta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDosis = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAcciones = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btn_MostrarM1 = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btn_MostrarM2 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        rdOral = new javax.swing.JRadioButton();
        rdInyectable = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbReceta = new javax.swing.JTable();
        cbIdConsulta = new javax.swing.JComboBox<>();
        txtDiagnostico = new javax.swing.JTextField();
        txtFrecuencia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jlbIdReceta = new javax.swing.JLabel();
        jlbFecha = new javax.swing.JLabel();
        jlbDosis = new javax.swing.JLabel();
        jlbFrecuencia = new javax.swing.JLabel();
        jlbDuracion = new javax.swing.JLabel();
        jlbVia = new javax.swing.JLabel();
        jlbDiagnostico = new javax.swing.JLabel();
        jlbAcciones = new javax.swing.JLabel();
        jlbIdConsulta = new javax.swing.JLabel();
        calenderFecha = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        LogoP1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(30, 79, 111));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtId_Receta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId_Receta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId_Receta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_RecetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_RecetaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("ID Receta:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Fecha Receta:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("ID Consulta:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Dosis:");

        txtDosis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDosisKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDosisKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Frecuencia:");

        txtAcciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAcciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAccionesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAccionesKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Via Administracion:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Duracion:");

        txtDuracion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDuracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDuracionKeyReleased(evt);
            }
        });

        btn_MostrarM1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM1.setText("Back Menu ");
        btn_MostrarM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MostrarM1SalirMenu(evt);
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

        btn_MostrarM2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM2.setText("Salir");
        btn_MostrarM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MostrarM2SalirMenu(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(167, 167, 167)
                .addComponent(btn_MostrarM1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_MostrarM2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_MostrarM1)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btn_MostrarM2)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addGap(17, 17, 17))
        );

        btnGroupVia.add(rdOral);
        rdOral.setText("Oral");
        rdOral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdOralActionPerformed(evt);
            }
        });

        btnGroupVia.add(rdInyectable);
        rdInyectable.setText("Inyectable");
        rdInyectable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdInyectableActionPerformed(evt);
            }
        });

        tbReceta.setModel(new javax.swing.table.DefaultTableModel(
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
        tbReceta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRecetaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbReceta);

        txtDiagnostico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyReleased(evt);
            }
        });

        txtFrecuencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFrecuencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFrecuenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFrecuenciaKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Diagnostico:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Acciones:");

        jlbIdReceta.setForeground(new java.awt.Color(204, 0, 0));

        jlbFecha.setForeground(new java.awt.Color(204, 0, 0));

        jlbDosis.setForeground(new java.awt.Color(204, 0, 0));

        jlbFrecuencia.setForeground(new java.awt.Color(204, 0, 0));

        jlbDuracion.setForeground(new java.awt.Color(204, 0, 0));

        jlbVia.setForeground(new java.awt.Color(204, 0, 0));

        jlbDiagnostico.setForeground(new java.awt.Color(204, 0, 0));

        jlbAcciones.setForeground(new java.awt.Color(204, 0, 0));

        jlbIdConsulta.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdOral)
                                .addGap(45, 45, 45)
                                .addComponent(rdInyectable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlbVia, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlbDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlbAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlbDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlbFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlbDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtId_Receta, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlbIdReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(288, 288, 288))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId_Receta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbIdReceta)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jlbDosis))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jlbFrecuencia))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jlbDuracion)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdOral)
                    .addComponent(rdInyectable)
                    .addComponent(jLabel14)
                    .addComponent(jlbVia))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbDiagnostico)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)))
                .addGap(17, 21, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jlbAcciones))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jlbIdConsulta))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Receta");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1071, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(LogoP1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LogoP1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtId_RecetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_RecetaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_RecetaKeyReleased

    private void txtId_RecetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_RecetaKeyTyped
        int key = evt.getKeyChar();

        boolean numero = key >=48 && key  <=57;

        if(!numero){
            evt.consume();
        }

        if(txtId_Receta.getText().trim().length()== 10){
            evt.consume();
        }
    }//GEN-LAST:event_txtId_RecetaKeyTyped

    private void txtDosisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosisKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtDosisKeyReleased

    private void txtDosisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosisKeyTyped
        if(txtDosis.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtDosisKeyTyped

    private void txtAccionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAccionesKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtAccionesKeyReleased

    private void txtAccionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAccionesKeyTyped
        if(txtAcciones.getText().length()>=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtAccionesKeyTyped

    private void txtDuracionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuracionKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtDuracionKeyReleased

    private void btn_MostrarM1SalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarM1SalirMenu
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
    }//GEN-LAST:event_btn_MostrarM1SalirMenu

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtId_Receta.getText().isEmpty() || calenderFecha.getDate()==null ||  txtDosis.getText().isEmpty() || txtFrecuencia.getText().isEmpty()
            || txtDuracion.getText().isEmpty() || txtDiagnostico.getText().isEmpty() || txtAcciones.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            char via;
            if(rdOral.isSelected()){
                via='O';
            }else{
                via='I';
            }
            Date fecha_Receta=calenderFecha.getDate();
            if(fecha_Receta==null){
                JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
            return;
            }
            Consulta consultaSeleccionada = (Consulta) cbIdConsulta.getSelectedItem();
            int idConsultaSeleccionada=consultaSeleccionada.getId_Consulta();
            int id_Usuario=UsuarioContexto.getId_Usuario();
            Receta rece = new Receta();
            rece.setId_Receta(Integer.parseInt(txtId_Receta.getText()));
            rece.setFecha_Receta(new java.sql.Date(fecha_Receta.getTime()));
            rece.setDosis(txtDosis.getText());
            rece.setFrecuencia(txtFrecuencia.getText());
            rece.setDuracion(Integer.parseInt(txtDuracion.getText()));
            rece.setVia_Administracion((char)via);
            rece.setId_Consulta(idConsultaSeleccionada);
            rece.setDiagnostico(txtDiagnostico.getText());
            rece.setAcciones(txtAcciones.getText());
            rece.setId_Usuario(id_Usuario);
            String mensaje=recebo.agregarReceta(rece);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarReceta();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    public void limpiar(){
        txtId_Receta.setText("");
        txtFrecuencia.setText("");
        calenderFecha.setDate(null);
        txtDosis.setText("");
        txtAcciones.setText("");
        txtDuracion.setText("");
        btnGroupVia.clearSelection();
        cbIdConsulta.setSelectedIndex(-1);
        idMax();
        validar();
    }
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtId_Receta.getText().isEmpty() || calenderFecha.getDate()==null ||  txtDosis.getText().isEmpty() || txtFrecuencia.getText().isEmpty()
            || txtDuracion.getText().isEmpty() || txtDiagnostico.getText().isEmpty() || txtAcciones.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            char via;
            if(rdOral.isSelected()){
                via='O';
            }else{
                via='I';
            }
            Date fecha_Receta=calenderFecha.getDate();
            if(fecha_Receta==null){
                JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
            return;
            }
            Consulta consultaSeleccionada = (Consulta) cbIdConsulta.getSelectedItem();
            int idConsultaSeleccionada=consultaSeleccionada.getId_Consulta();
            int id_Usuario=UsuarioContexto.getId_Usuario();
            Receta rece = new Receta();
            rece.setId_Receta(Integer.parseInt(txtId_Receta.getText()));
            rece.setFecha_Receta(new java.sql.Date(fecha_Receta.getTime()));
            rece.setDosis(txtDosis.getText());
            rece.setFrecuencia(txtFrecuencia.getText());
            rece.setDuracion(Integer.parseInt(txtDuracion.getText()));
            rece.setVia_Administracion((char)via);
            rece.setId_Consulta(idConsultaSeleccionada);
            rece.setDiagnostico(txtDiagnostico.getText());
            rece.setAcciones(txtAcciones.getText());
            rece.setId_Usuario(id_Usuario);
            String mensaje=recebo.agregarReceta(rece);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarReceta();
        }
    }//GEN-LAST:event_btnModificarActionPerformed
    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tbRecetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRecetaMouseClicked
        int seleccion=tbReceta.rowAtPoint(evt.getPoint());
        txtId_Receta.setText(tbReceta.getValueAt(seleccion, 0)+"");
        // Convertir la fecha obtenida de la tabla y asignarla al JCalendarComboBox
    String fechaTexto = tbReceta.getValueAt(seleccion, 1) + "";
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); // Ajusta el formato si es necesario
    try {
        Date fecha_receta = formato.parse(fechaTexto);
        calenderFecha.setDate(fecha_receta); // Asigna la fecha al JCalendarComboBox
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + e.getMessage());
        calenderFecha.setDate(null); // Limpia la fecha si hay un error
    }
        txtDosis.setText(tbReceta.getValueAt(seleccion, 2)+"");
        txtFrecuencia.setText(tbReceta.getValueAt(seleccion, 3)+"");
        txtAcciones.setText(tbReceta.getValueAt(seleccion, 4)+"");
        txtDuracion.setText(tbReceta.getValueAt(seleccion, 5)+"");
        String via=tbReceta.getValueAt(seleccion, 6)+"";
        if(via.equals("O")){
            rdOral.setSelected(true);
        }else{
            rdInyectable.setSelected(true);
        }
        
        validar();
    }//GEN-LAST:event_tbRecetaMouseClicked

    private void txtDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtDiagnosticoKeyReleased

    private void txtFrecuenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuenciaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtFrecuenciaKeyReleased

    private void txtFrecuenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFrecuenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFrecuenciaKeyTyped

    private void btn_MostrarM2SalirMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MostrarM2SalirMenu
        System.exit(0);
    }//GEN-LAST:event_btn_MostrarM2SalirMenu

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(txtId_Receta.getText().isEmpty() || calenderFecha.getDate()==null ||  txtDosis.getText().isEmpty() || txtFrecuencia.getText().isEmpty() || txtDiagnostico.getText().isEmpty() ||
            txtAcciones.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String mensaje=recebo.eliminarReceta(Integer.parseInt(txtId_Receta.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarReceta();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void rdOralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdOralActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdOralActionPerformed

    private void rdInyectableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdInyectableActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdInyectableActionPerformed

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
            java.util.logging.Logger.getLogger(FrmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReceta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReceta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LogoP1;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.ButtonGroup btnGroupVia;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btn_MostrarM1;
    private javax.swing.JButton btn_MostrarM2;
    private com.toedter.calendar.JDateChooser calenderFecha;
    private javax.swing.JComboBox<Consulta> cbIdConsulta;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbAcciones;
    private javax.swing.JLabel jlbDiagnostico;
    private javax.swing.JLabel jlbDosis;
    private javax.swing.JLabel jlbDuracion;
    private javax.swing.JLabel jlbFecha;
    private javax.swing.JLabel jlbFrecuencia;
    private javax.swing.JLabel jlbIdConsulta;
    private javax.swing.JLabel jlbIdReceta;
    private javax.swing.JLabel jlbVia;
    private javax.swing.JRadioButton rdInyectable;
    private javax.swing.JRadioButton rdOral;
    private javax.swing.JTable tbReceta;
    private javax.swing.JTextField txtAcciones;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtFrecuencia;
    private javax.swing.JTextField txtId_Receta;
    // End of variables declaration//GEN-END:variables
}
