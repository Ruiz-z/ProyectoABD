/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;
import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MenuSecundarioAdm;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.AlumnoBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Alumno;
import com.ConsultasITS.mx.entity.Carrera;
import com.ConsultasITS.mx.entity.Persona;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author kevin
 */
public class FrmAlumno extends javax.swing.JFrame {

    /**
     * Creates new form FrmAlumno
     */
    public FrmAlumno() {
        initComponents();
        listarAlumno();
        idMax();
        setTitle("FORMULARIO DE ALUMNO");
        setLocationRelativeTo(null);
        setResizable(false);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        initComponents1();
        consultaIdPersona(cbIdPersona);
        consultaIdCarrera(cbIdCarrera);
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

// Llama a validar cuando se seleccione una opción en el ComboBox de Carrera
cbIdCarrera.addItemListener(new ItemListener() {
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
    public static void consultaIdCarrera(JComboBox<Carrera> cbIdCarrera){
        Connection conn=Conexion.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="SELECT ID_CARRERA, NOMBRE_CARRERA FROM CARRERA";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cbIdCarrera.addItem(new Carrera(0, "Seleccione", 0));
            while(rs.next()){
                int idCarrera=rs.getInt("ID_CARRERA");
                String nombre=rs.getString("NOMBRE_CARRERA");
                cbIdCarrera.addItem(new Carrera(idCarrera, nombre, 0));
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
        txtId_Alumno.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarYCargarAlumno(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               validarYCargarAlumno(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarYCargarAlumno(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
     public void validarYCargarAlumno(){
         String idTexto=txtId_Alumno.getText();
         if(!idTexto.isEmpty() && idTexto.matches("\\d+")){
             int idAlumno=Integer.parseInt(idTexto);
             Alumno alumno = albo.buscarAlumnoPorId(idAlumno);
             if(alumno!=null){
                 txtSemestre.setText(String.valueOf(alumno.getSemestre()));
                 for (int i = 0; i < cbIdPersona.getItemCount(); i++) {
                Persona persona = cbIdPersona.getItemAt(i);
                if (persona.getId_Persona() == alumno.getId_Persona()) {
                    cbIdPersona.setSelectedIndex(i);  // Selecciona la persona correcta
                    break;
                }
            }
            for(int i=0; i<cbIdCarrera.getItemCount(); i++){
                Carrera carrera=cbIdCarrera.getItemAt(i);
                if(carrera.getId_Carrera()==alumno.getId_Carrera()){
                    cbIdCarrera.setSelectedIndex(i);
                    break;
                }
            }     
             }else{
                 txtSemestre.setText("");
                 cbIdPersona.setSelectedIndex(0);
                 cbIdCarrera.setSelectedIndex(0);
             }
         }else{
             txtSemestre.setText("");
               cbIdPersona.setSelectedIndex(0);
                 cbIdCarrera.setSelectedIndex(0);  
         }
     }
   public  void validar(){
    // Validar el campo Id Alumno
    if(txtId_Alumno.getText().isEmpty()){
        jlbIdAlumnos.setText("*Obligatorio");
    } else {
        jlbIdAlumnos.setText("");
    }

    // Validar el campo Semestre
    if(txtSemestre.getText().isEmpty()){
        jlbSemstre.setText("*Obligatorio");
    } else {
        jlbSemstre.setText("");
    }

    // Validar el ComboBox de Persona
    Persona selectedPersona = (Persona) cbIdPersona.getSelectedItem();
    if (selectedPersona == null || selectedPersona.getId_Persona() == 0) {
        jlbIdPersona.setText("*Obligatorio");
    } else {
        jlbIdPersona.setText("");
    }

    // Validar el ComboBox de Carrera
    Carrera selectedCarrera = (Carrera) cbIdCarrera.getSelectedItem();
    if (selectedCarrera == null || selectedCarrera.getId_Carrera() == 0) {
        jlbIdCarrera.setText("*Obligatorio");
    } else {
        jlbIdCarrera.setText("");
    }

    // Validar si los botones deben estar habilitados o deshabilitados
    if (txtId_Alumno.getText().isEmpty() || txtSemestre.getText().isEmpty()
        || selectedPersona == null || selectedCarrera == null
        || selectedPersona.getId_Persona() == 0 || selectedCarrera.getId_Carrera() == 0) {
        // Si alguno de los campos está vacío o no se ha seleccionado un valor válido en los ComboBoxes, deshabilitamos los botones
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    } else {
        // Si todos los campos son válidos, habilitamos los botones
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
}


 private AlumnoBO albo = new AlumnoBO();
    
    public void listarAlumno(){
        int idCarrera=((Carrera) cbIdCarrera.getSelectedItem()).getId_Carrera();
        albo.listarAlumno(tbAlumno, idCarrera);
    }
    
    public void idMax(){
        txtId_Alumno.setText(albo.getMaxID()+"");
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
        txtId_Alumno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSemestre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_MostrarM = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btn_MostrarM1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlumno = new javax.swing.JTable();
        jlbIdAlumnos = new javax.swing.JLabel();
        jlbSemstre = new javax.swing.JLabel();
        jlbIdPersona = new javax.swing.JLabel();
        jlbIdCarrera = new javax.swing.JLabel();
        cbIdCarrera = new javax.swing.JComboBox<>();
        cbIdPersona = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        LogoP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtId_Alumno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId_Alumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtId_Alumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtId_AlumnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtId_AlumnoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID Alumno:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Semestre:");

        txtSemestre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSemestre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSemestreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSemestreKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("ID Persona:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("ID Carrera:");

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
                .addGap(29, 29, 29)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addGap(46, 46, 46)
                .addComponent(btn_MostrarM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_MostrarM1)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_MostrarM)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btn_MostrarM1))
                .addGap(39, 39, 39))
        );

        tbAlumno.setModel(new javax.swing.table.DefaultTableModel(
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
        tbAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAlumnoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAlumno);

        jlbIdAlumnos.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdAlumnos.setForeground(new java.awt.Color(153, 0, 0));

        jlbSemstre.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbSemstre.setForeground(new java.awt.Color(153, 0, 0));

        jlbIdPersona.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdPersona.setForeground(new java.awt.Color(153, 0, 0));

        jlbIdCarrera.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jlbIdCarrera.setForeground(new java.awt.Color(153, 0, 0));

        cbIdCarrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbIdCarreraKeyReleased(evt);
            }
        });

        cbIdPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbIdPersonaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbIdPersona, 0, 128, Short.MAX_VALUE)
                    .addComponent(cbIdCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSemestre)
                    .addComponent(txtId_Alumno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbIdCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbIdPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbSemstre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlbIdAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId_Alumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jlbIdAlumnos))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jlbSemstre))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jlbIdCarrera)
                            .addComponent(cbIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jlbIdPersona)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Alumno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addContainerGap())
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

    private void tbAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlumnoMouseClicked
        int seleccion=tbAlumno.rowAtPoint(evt.getPoint());
        txtId_Alumno.setText(tbAlumno.getValueAt(seleccion, 0)+"");
        txtSemestre.setText(tbAlumno.getValueAt(seleccion, 1)+"");
        
        validar();
    }//GEN-LAST:event_tbAlumnoMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtId_Alumno.getText().isEmpty() || txtSemestre.getText().isEmpty()
            ){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            // Obtén la persona seleccionada del JComboBox
            int id_Usuario=UsuarioContexto.getId_Usuario();
        Persona personaSeleccionada = (Persona) cbIdPersona.getSelectedItem();
        int idPersonaSeleccionada = personaSeleccionada.getId_Persona();
        Carrera carreraSeleccionada = (Carrera) cbIdCarrera.getSelectedItem();
        int idCarreraSeleccionada=carreraSeleccionada.getId_Carrera();
            Alumno alum = new Alumno();
            alum.setId_Alumno(Integer.parseInt(txtId_Alumno.getText()));
            alum.setSemestre(Integer.parseInt(txtSemestre.getText()));
            alum.setId_Persona(idPersonaSeleccionada);
            alum.setId_Carrera(idCarreraSeleccionada);
            alum.setId_Usuario(id_Usuario);
            String mensaje=albo.agregarAlumno(alum);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarAlumno();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtId_Alumno.getText().isEmpty() || txtSemestre.getText().isEmpty()
            ){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            Persona personaSeleccionada = (Persona) cbIdPersona.getSelectedItem();
        int idPersonaSeleccionada = personaSeleccionada.getId_Persona();
        Carrera carreraSeleccionada = (Carrera) cbIdCarrera.getSelectedItem();
        int idCarreraSeleccionada=carreraSeleccionada.getId_Carrera();
        int id_Usuario=UsuarioContexto.getId_Usuario();
            Alumno alum = new Alumno();
            alum.setId_Alumno(Integer.parseInt(txtId_Alumno.getText()));
            alum.setSemestre(Integer.parseInt(txtSemestre.getText()));
            alum.setId_Persona(idPersonaSeleccionada);
            alum.setId_Carrera(idCarreraSeleccionada);
            alum.setId_Usuario(id_Usuario);
            String mensaje=albo.modificarAlumno(alum);
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarAlumno();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(txtId_Alumno.getText().isEmpty() || txtSemestre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
        }else{
            String mensaje=albo.eliminarAlumno(Integer.parseInt(txtId_Alumno.getText()));
            JOptionPane.showMessageDialog(null, mensaje);
            limpiar();
            listarAlumno();
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

    private void txtId_AlumnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_AlumnoKeyTyped
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtId_Alumno.getText().trim().length()== 10){
            evt.consume();
        }
    }//GEN-LAST:event_txtId_AlumnoKeyTyped

    private void txtSemestreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemestreKeyTyped
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtSemestre.getText().trim().length()== 2){
            evt.consume();
        }
    }//GEN-LAST:event_txtSemestreKeyTyped

    private void txtId_AlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtId_AlumnoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtId_AlumnoKeyReleased

    private void txtSemestreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemestreKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtSemestreKeyReleased

    private void cbIdPersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbIdPersonaKeyReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cbIdPersonaKeyReleased

    private void cbIdCarreraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbIdCarreraKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbIdCarreraKeyReleased

    public void limpiar(){
        txtId_Alumno.setText("");
        txtSemestre.setText("");
        cbIdPersona.setSelectedIndex(-1);
        cbIdCarrera.setSelectedIndex(-1);
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
            java.util.logging.Logger.getLogger(FrmAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAlumno().setVisible(true);
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
    private javax.swing.JComboBox<Carrera> cbIdCarrera;
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
    private javax.swing.JLabel jlbIdAlumnos;
    private javax.swing.JLabel jlbIdCarrera;
    private javax.swing.JLabel jlbIdPersona;
    private javax.swing.JLabel jlbSemstre;
    private javax.swing.JTable tbAlumno;
    private javax.swing.JTextField txtId_Alumno;
    private javax.swing.JTextField txtSemestre;
    // End of variables declaration//GEN-END:variables
}
