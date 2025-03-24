/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;

import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MenuSecundarioAdm;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.ConsultaBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Consulta;
import com.ConsultasITS.mx.entity.Paciente;
import com.ConsultasITS.mx.entity.Persona;
import com.ConsultasITS.mx.entity.Personal_Salud;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author kevin
 */
public class FrmConsulta extends javax.swing.JFrame {

    /**
     * Creates new form FrmConsulta
     */
    public FrmConsulta() {
        initComponents();
        listarConsulta();
        idMax();
        setTitle("FORMULARIO DE CONSULTA");
        setLocationRelativeTo(null);
        setResizable(false);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        consultaIdPersona(cbIdPersona);
        consultaIdPaciente(cbIdPaciente);
        consultaIdPersonal(cbIdPersonal);
        
        initComponents1();
        initComponents2();
    }
   public void initComponents2() {
       
    // Llama a validar cuando se seleccione una opción en el ComboBox de Persona
    cbIdPersona.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            validar();  // Llama a validar al cambiar la selección
        }
    });

    // Llama a validar cuando se seleccione una opción en el ComboBox de Paciente
    cbIdPaciente.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            validar();  // Llama a validar al cambiar la selección
        }
    });

    // Llama a validar cuando se seleccione una opción en el ComboBox de Personal
    cbIdPersonal.addItemListener(new ItemListener() {
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
    
    public static void consultaIdPaciente(JComboBox<Paciente> cbIdPaciente) {
    Connection conn = Conexion.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        // Consulta SQL que obtiene el ID del paciente y el nombre de la persona asociada
        String sql = "SELECT P.ID_PACIENTE, PER.NOMBRE_PERSONA, PER.PATERNO_PERSONA " +
                     "FROM PACIENTE P, PERSONA PER " +
                     "WHERE PER.ID_PERSONA = P.ID_PERSONA";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        // Agrega un ítem inicial "Seleccione"
        cbIdPaciente.addItem(new Paciente(0, 0, 0, 0, "Seleccione", 0));  // Suponiendo que también quieres mostrar "Seleccione" en el ComboBox

        // Recorre los resultados y agrega los pacientes al JComboBox
        while (rs.next()) {
            int idPaciente = rs.getInt("ID_PACIENTE");
            String nombre = rs.getString("NOMBRE_PERSONA");
            String paterno = rs.getString("PATERNO_PERSONA");

            // Concatenar nombre y apellido para mostrar en el ComboBox
            String nombreCompleto = nombre + " " + paterno;

            // Crear un objeto Paciente y agregarlo al ComboBox
            cbIdPaciente.addItem(new Paciente(idPaciente, 0, 0, 0, nombreCompleto, 0));
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
    public static void consultaIdPersonal(JComboBox<Personal_Salud> cbIdPersonal){
        Connection conn=Conexion.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="SELECT ID_PERSONAL_SALUD, NOMRE_PERSONAL_SALUD, PATERNO_PERTSONAL_SALUD FROM PERSONAL_SALUD";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cbIdPersonal.addItem(new Personal_Salud(0, "Seleccione", "", "", 0, "", 0));
            while(rs.next()){
                int idPersonal_Salud=rs.getInt("ID_PERSONAL_SALUD");
                String nombre=rs.getString("NOMRE_PERSONAL_SALUD");
                String paterno=rs.getString("PATERNO_PERTSONAL_SALUD");
                cbIdPersonal.addItem(new Personal_Salud(idPersonal_Salud, nombre, paterno, "", 0, "", 0));
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
         calenderFecha.getDateEditor().addPropertyChangeListener("date", evt -> {
    validar();
});
        txtId_Consulta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarYCargarConsulta(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validarYCargarConsulta(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarYCargarConsulta(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    public void validarYCargarConsulta(){
    String idTexto = txtId_Consulta.getText().trim();  // Eliminar posibles espacios en blanco
    if (!idTexto.isEmpty() && idTexto.matches("\\d+")) {  // Solo dígitos permitidos
        int idConsulta = Integer.parseInt(idTexto);
        Consulta consulta = consbo.buscarConsultaPorId(idConsulta);
        
        if (consulta != null) {
            // Asignar la fecha directamente al JCalendarComboBox
            Date fechaCaducidad = consulta.getFecha_Consulta();
            calenderFecha.setDate(fechaCaducidad);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String horaConsulta = sdf.format(consulta.getHora_Consulta());
            txtHora_Consulta.setText(horaConsulta);
            
            
            for(int i=0; i<cbIdPersona.getItemCount(); i++){
                Persona persona=cbIdPersona.getItemAt(i);
                if(persona.getId_Persona()==consulta.getId_Persona()){
                    cbIdPersona.setSelectedIndex(i);
                    break;
                }
            }
            for(int i=0; i<cbIdPaciente.getItemCount(); i++){
                Paciente paciente=cbIdPaciente.getItemAt(i);
                if(paciente.getId_Paciente()==consulta.getId_Paciente()){
                    cbIdPaciente.setSelectedIndex(i);
                    break;
                }
            }
            for(int i=0; i<cbIdPersonal.getItemCount(); i++){
                Personal_Salud personal=cbIdPersonal.getItemAt(i);
                if(personal.getId_Personal_Salud()==consulta.getId_Personal_Salud()){
                    cbIdPersonal.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            limpiarCamposConsulta();
        }
    } else {
        limpiarCamposConsulta();
    }
}

// Método para limpiar los campos
public void limpiarCamposConsulta() {
    calenderFecha.setDate(null);
    txtHora_Consulta.setText("");
    cbIdPersona.setSelectedIndex(0);
    cbIdPaciente.setSelectedIndex(0);
    cbIdPersonal.setSelectedIndex(0);
}

    public void validar(){
        if(txtId_Consulta.getText().isEmpty()){
            jlbIdconsulta.setText("*Obligatorio");
        }else{
            jlbIdconsulta.setText("");
        }
        if(calenderFecha.getDate()==null){
            jlbFecha.setText("*Obligatorio");
        }else{
            jlbFecha.setText("");
        }
        if(txtHora_Consulta.getText().isEmpty()){
            jlbHora.setText("*Obligatorio");
        }else{
            jlbHora.setText("");
        }
        
        Persona selectedpersona=(Persona) cbIdPersona.getSelectedItem();
        Paciente selectedpaciente=(Paciente) cbIdPaciente.getSelectedItem();
        Personal_Salud selectedpersonal=(Personal_Salud) cbIdPersonal.getSelectedItem();
        if(selectedpersona==null || selectedpersona.getId_Persona()==0){
            jlbIdPersona.setText("*Obligatorio");
        }else{
            jlbIdPersona.setText("");
        }
        if(selectedpaciente==null || selectedpaciente.getId_Paciente()==0){
            jlbIdPaciente.setText("*Obligatorio");
        }else{
            jlbIdPaciente.setText("");
        }
        if(selectedpersonal==null || selectedpersonal.getId_Personal_Salud()==0){
            jlbIdPersonal.setText("*Obligatorio");
        }else{
            jlbIdPersonal.setText("");
        }
        
        if(txtId_Consulta.getText().isEmpty() || calenderFecha.getDate()==null || 
        txtHora_Consulta.getText().isEmpty() 
        || selectedpersona==null || selectedpaciente==null || selectedpersonal==null
        || selectedpersona.getId_Persona()==0 || selectedpaciente.getId_Paciente()==0 || selectedpersonal.getId_Personal_Salud()==0){
             btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        }else{
             btnAgregar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        }
    }

    private ConsultaBO consbo = new ConsultaBO();
    public void listarConsulta(){
        consbo.listarConsulta(tbConsulta);
    }
    public void idMax(){
        txtId_Consulta.setText(consbo.getMaxID()+"");
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
        txtId_Consulta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHora_Consulta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_MostrarM = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btn_MostrarM1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbConsulta = new javax.swing.JTable();
        jlbIdconsulta = new javax.swing.JLabel();
        jlbFecha = new javax.swing.JLabel();
        jlbHora = new javax.swing.JLabel();
        jlbIdPersona = new javax.swing.JLabel();
        jlbIdPaciente = new javax.swing.JLabel();
        jlbIdPersonal = new javax.swing.JLabel();
        cbIdPersonal = new javax.swing.JComboBox<>();
        cbIdPaciente = new javax.swing.JComboBox<>();
        cbIdPersona = new javax.swing.JComboBox<>();
        calenderFecha = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        LogoP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtId_Consulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId_Consulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId_Consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_ConsultaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_ConsultaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID Consulta:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("FechaConsulta:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("ID Personal Salud:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Hora Consulta:");

        txtHora_Consulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHora_Consulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHora_ConsultaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID Persona:");

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
                .addGap(16, 16, 16)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_MostrarM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_MostrarM1)
                .addGap(443, 443, 443))
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
                    .addComponent(btn_MostrarM1))
                .addGap(18, 18, 18))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("ID Paciente:");

        tbConsulta.setModel(new javax.swing.table.DefaultTableModel(
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
        tbConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbConsultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbConsulta);

        jlbIdconsulta.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdconsulta.setForeground(new java.awt.Color(204, 51, 0));

        jlbFecha.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbFecha.setForeground(new java.awt.Color(204, 51, 0));

        jlbHora.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbHora.setForeground(new java.awt.Color(204, 51, 0));

        jlbIdPersona.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdPersona.setForeground(new java.awt.Color(204, 51, 0));

        jlbIdPaciente.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdPaciente.setForeground(new java.awt.Color(204, 51, 0));

        jlbIdPersonal.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdPersonal.setForeground(new java.awt.Color(204, 51, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHora_Consulta, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtId_Consulta)
                            .addComponent(calenderFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(cbIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jlbIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbIdconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jlbIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtId_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addComponent(jlbIdconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHora_Consulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(cbIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlbIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlbIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(cbIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlbIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consulta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1322, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbConsultaMouseClicked
        int seleccion=tbConsulta.rowAtPoint(evt.getPoint());
        txtId_Consulta.setText(tbConsulta.getValueAt(seleccion, 0)+"");
        // Convertir la fecha obtenida de la tabla y asignarla al JCalendarComboBox
    String fechaTexto = tbConsulta.getValueAt(seleccion, 1) + "";
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); // Ajusta el formato si es necesario
    try {
        Date fechaCaducidad = formato.parse(fechaTexto);
        calenderFecha.setDate(fechaCaducidad); // Asigna la fecha al JCalendarComboBox
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + e.getMessage());
        calenderFecha.setDate(null); // Limpia la fecha si hay un error
    }
        txtHora_Consulta.setText(tbConsulta.getValueAt(seleccion, 2)+"");
        
        

        validar();
    }//GEN-LAST:event_tbConsultaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (txtId_Consulta.getText().isEmpty() || calenderFecha.getDate()==null || 
        txtHora_Consulta.getText().isEmpty() ) {
        
        JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
    } else {
        Date fecha_Consulta = calenderFecha.getDate();
        
        if (fecha_Consulta == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
            return;
        }

        // Convertimos la hora a Timestamp
        String hora_texto = txtHora_Consulta.getText();
        Timestamp hora_Consulta = null;
        try {
            // Cambiamos el formato a yyyy-MM-dd HH:mm:ss
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            Date hora = formatoHora.parse(hora_texto);
            // Usamos Calendar para obtener la fecha y la hora
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha_Consulta);
            calendar.set(Calendar.HOUR_OF_DAY, hora.getHours());
            calendar.set(Calendar.MINUTE, hora.getMinutes());
            hora_Consulta = new Timestamp(calendar.getTimeInMillis());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de la hora, debe ser 'HH:mm'" + e.getMessage());
            return;
        }

        int id_Usuario=UsuarioContexto.getId_Usuario();
        // Se crea objeto tipo Consulta
         Persona personaSeleccionada = (Persona) cbIdPersona.getSelectedItem();
        int idPersonaSeleccionada = personaSeleccionada.getId_Persona();
        Paciente pacienteSeleccionado =(Paciente) cbIdPaciente.getSelectedItem();
        int idPacienteSeleccionado=pacienteSeleccionado.getId_Paciente();
        Personal_Salud personalSeleccionado =(Personal_Salud) cbIdPersonal.getSelectedItem();
        int idPersonalSeleccionado =personalSeleccionado.getId_Personal_Salud();
        Consulta cons = new Consulta();
        cons.setId_Consulta(Integer.parseInt(txtId_Consulta.getText()));
        cons.setFecha_Consulta(new java.sql.Date(fecha_Consulta.getTime()));
        cons.setHora_Consulta(hora_Consulta);
        
        cons.setId_Persona(idPersonaSeleccionada);
        cons.setId_Paciente(idPacienteSeleccionado);
        cons.setId_Personal_Salud(idPersonalSeleccionado);
        cons.setId_Usuario(id_Usuario);
        String mensaje = consbo.agregarConsulta(cons);
        JOptionPane.showMessageDialog(null, mensaje);
        limpiar();
        listarConsulta();
    }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtId_Consulta.getText().isEmpty() || calenderFecha.getDate()==null || 
        txtHora_Consulta.getText().isEmpty() ) {
        
        JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
    } else {
        Date fecha_Consulta = calenderFecha.getDate();
        
        if (fecha_Consulta == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida.");
            return;
        }

        // Convertimos la hora a Timestamp
        String hora_texto = txtHora_Consulta.getText();
        Timestamp hora_Consulta = null;
        try {
            // Cambiamos el formato a yyyy-MM-dd HH:mm:ss
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            Date hora = formatoHora.parse(hora_texto);
            // Usamos Calendar para obtener la fecha y la hora
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha_Consulta);
            calendar.set(Calendar.HOUR_OF_DAY, hora.getHours());
            calendar.set(Calendar.MINUTE, hora.getMinutes());
            hora_Consulta = new Timestamp(calendar.getTimeInMillis());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de la hora, debe ser 'HH:mm'" + e.getMessage());
            return;
        }

        int id_Usuario=UsuarioContexto.getId_Usuario();
        // Se crea objeto tipo Consulta
         Persona personaSeleccionada = (Persona) cbIdPersona.getSelectedItem();
        int idPersonaSeleccionada = personaSeleccionada.getId_Persona();
        Paciente pacienteSeleccionado =(Paciente) cbIdPaciente.getSelectedItem();
        int idPacienteSeleccionado=pacienteSeleccionado.getId_Paciente();
        Personal_Salud personalSeleccionado =(Personal_Salud) cbIdPersonal.getSelectedItem();
        int idPersonalSeleccionado =personalSeleccionado.getId_Personal_Salud();
        Consulta cons = new Consulta();
        cons.setId_Consulta(Integer.parseInt(txtId_Consulta.getText()));
        cons.setFecha_Consulta(new java.sql.Date(fecha_Consulta.getTime()));
        cons.setHora_Consulta(hora_Consulta);
        
        cons.setId_Persona(idPersonaSeleccionada);
        cons.setId_Paciente(idPacienteSeleccionado);
        cons.setId_Personal_Salud(idPersonalSeleccionado);
        cons.setId_Usuario(id_Usuario);
        String mensaje = consbo.modificarConsulta(cons);
        JOptionPane.showMessageDialog(null, mensaje);
        limpiar();
        listarConsulta();
    }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(txtId_Consulta.getText().isEmpty() || calenderFecha.getDate()==null ||  txtHora_Consulta.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String mensaje=consbo.eliminarConsulta(Integer.parseInt(txtId_Consulta.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarConsulta();
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

    private void txtId_ConsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_ConsultaKeyTyped
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtId_Consulta.getText().trim().length()== 10){
            evt.consume();
        }
    }//GEN-LAST:event_txtId_ConsultaKeyTyped

    private void txtId_ConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_ConsultaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_ConsultaKeyReleased

    private void txtHora_ConsultaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHora_ConsultaKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtHora_ConsultaKeyReleased

    public void limpiar(){
        txtId_Consulta.setText("");
        calenderFecha.setDate(null);
        txtHora_Consulta.setText("");
        
        cbIdPersona.setSelectedIndex(-1);
        cbIdPaciente.setSelectedIndex(-1);
        cbIdPersonal.setSelectedIndex(-1);
        idMax();
        validar();
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
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmConsulta().setVisible(true);
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
    private javax.swing.JComboBox<Paciente> cbIdPaciente;
    private javax.swing.JComboBox<Persona> cbIdPersona;
    private javax.swing.JComboBox<Personal_Salud> cbIdPersonal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbFecha;
    private javax.swing.JLabel jlbHora;
    private javax.swing.JLabel jlbIdPaciente;
    private javax.swing.JLabel jlbIdPersona;
    private javax.swing.JLabel jlbIdPersonal;
    private javax.swing.JLabel jlbIdconsulta;
    private javax.swing.JTable tbConsulta;
    private javax.swing.JTextField txtHora_Consulta;
    private javax.swing.JTextField txtId_Consulta;
    // End of variables declaration//GEN-END:variables
}
