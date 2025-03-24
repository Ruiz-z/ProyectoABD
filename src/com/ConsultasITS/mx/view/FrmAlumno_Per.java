/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;


import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MenuSecundarioAdm;
import com.ConsultasITS.mx.bo.AlumnoBO;
import com.ConsultasITS.mx.bo.PersonaBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Alumno;
import com.ConsultasITS.mx.entity.Carrera;
import com.ConsultasITS.mx.entity.Persona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author kevin
 */
public class FrmAlumno_Per extends javax.swing.JFrame {

    private String usuario=UsuarioContexto.getNombreUsuario();
    /**
     * Creates new form FrmAlumno_Per
     */
    public FrmAlumno_Per() {
        initComponents();
        initComponents2();
        setTitle("FORMULARIO ALUMNO");
        setLocationRelativeTo(null);
        setResizable(false);
        jlbMensaje.setText("USUARIO: " + usuario);
         
         idMaxA();
        idMaxP();
        Calendar cal =  Calendar.getInstance();
         String fecha, hora;
         fecha=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
         hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE);
         this.FECHA.setText(fecha);
         this.HORA.setText(hora);
         consultaIdCarrera(cbIdCarrera);
         txtIdAlumno.setEditable(false);
         txtIdPersona.setEditable(false);
         btnGuardar.setEnabled(false);
         btnModificar.setEnabled(false);
         btnEliminar.setEnabled(false);
          }
    private PersonaBO personabo = new PersonaBO();
    public void listarPersona(){
        personabo.listarPersona(tbAlumno);
    }
    public void idMaxP(){
        txtIdPersona.setText(personabo.getMaxID()+"");
    }
    private AlumnoBO alumnobo = new AlumnoBO();
    public void listarAlumno(JTable tabla, int idCarrera){
        
        alumnobo.listarAlumno(tbAlumno, idCarrera);
    }
    public void idMaxA(){
        txtIdAlumno.setText(personabo.getMaxID()+"");
    }
    public void limpiar(){
        txtIdAlumno.setText("");
        txtIdPersona.setText("");
        txtNombre.setText("");
        txtPaterno.setText("");
        txtMaterno.setText("");
        txtEdad.setText("");
        txtSemestre.setText("");
        calenderFecha.setDate(null);
        groupGenero.clearSelection();
        idMaxP();
        idMaxA();
        
    }
    public void validar(){
        if(txtIdAlumno.getText().isEmpty()){
            jlbIdAlumno.setText("*Obligatorio");
        }else{
            jlbIdAlumno.setText("");
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
        if(txtEdad.getText().isEmpty()){
            jlbEdad.setText("*Obligatorio");
        }else{
            jlbEdad.setText("");
        }
        if(txtSemestre.getText().isEmpty()){
            jlbSemestre.setText("*Obligatorio");
        }else{
            jlbSemestre.setText("");
        }
        if(calenderFecha.getDate()==null){
            jlbFecha.setText("*Obligatorio");
        }else{
            jlbFecha.setText("");
        }
        if(!(rdMasculino.isSelected() || rdFemenino.isSelected())){
            jlbGenero.setText("*Obligatorio");
        }else{
            jlbGenero.setText("");
        }
        Carrera selectedCarrera=(Carrera) cbIdCarrera.getSelectedItem();
        if(selectedCarrera==null || selectedCarrera.getId_Carrera()==0){
            jlbIdCarrera.setText("*Obligatorio");
        }else{
            jlbIdCarrera.setText("");
        }
        if(selectedCarrera==null ||txtIdAlumno.getText().isEmpty() || txtSemestre.getText().isEmpty()
                || txtNombre.getText().isEmpty() || txtPaterno.getText().isEmpty() || txtMaterno.getText().isEmpty()
                || txtEdad.getText().isEmpty() || calenderFecha.getDate()==null || !(rdMasculino.isSelected() || rdFemenino.isSelected())){
            btnGuardar.setEnabled(false);
         btnModificar.setEnabled(false);
         btnEliminar.setEnabled(false);
        }else{
            btnGuardar.setEnabled(true);
         btnModificar.setEnabled(true);
         btnEliminar.setEnabled(true);
        }
    }
    public void initComponents2(){
        // Llama a validar cuando se seleccione una opción en el ComboBox de Carrera
        cbIdCarrera.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
        validar();  // Llama a validar al cambiar la selección
        }
        });
        calenderFecha.getDateEditor().addPropertyChangeListener("date", evt -> {
    validar();
  });
        
        cbIdCarrera.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtén el ID de la carrera seleccionada
        Carrera carreraSeleccionada = (Carrera) cbIdCarrera.getSelectedItem();
        int idCarrera = carreraSeleccionada.getId_Carrera();
        
        // Llama a listarAlumno con el ID de la carrera seleccionada
        listarAlumno(tbAlumno, idCarrera); // `tbAlumno` es el JTable donde muestras los alumnos
    }
});

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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupGenero = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbIdCarrera = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdAlumno = new javax.swing.JTextField();
        txtSemestre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPaterno = new javax.swing.JTextField();
        txtMaterno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        calenderFecha = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        rdMasculino = new javax.swing.JRadioButton();
        rdFemenino = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlumno = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtIdPersona = new javax.swing.JTextField();
        jlbIdCarrera = new javax.swing.JLabel();
        jlbIdAlumno = new javax.swing.JLabel();
        jlbSemestre = new javax.swing.JLabel();
        jlbEdad = new javax.swing.JLabel();
        jlbPaterno = new javax.swing.JLabel();
        jlbMaterno = new javax.swing.JLabel();
        jlbNombre = new javax.swing.JLabel();
        jlbFecha = new javax.swing.JLabel();
        jlbGenero = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlbMensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FECHA = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        HORA = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(30, 79, 111));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cbIdCarrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbIdCarreraKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Carrera:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("ID Alumno:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Semestre:");

        txtIdAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAlumnoActionPerformed(evt);
            }
        });
        txtIdAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdAlumnoKeyReleased(evt);
            }
        });

        txtSemestre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSemestreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSemestreKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nombre:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Paterno:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Materno:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
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

        txtMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaternoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Edad:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Fecha Nacimiento:");

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        calenderFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calenderFechaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Genero:");

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

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("ID Persona:");

        jlbIdCarrera.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbIdCarrera.setForeground(new java.awt.Color(153, 0, 0));

        jlbIdAlumno.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbIdAlumno.setForeground(new java.awt.Color(153, 0, 0));

        jlbSemestre.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbSemestre.setForeground(new java.awt.Color(153, 0, 0));

        jlbEdad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbEdad.setForeground(new java.awt.Color(153, 0, 0));

        jlbPaterno.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbPaterno.setForeground(new java.awt.Color(153, 0, 0));

        jlbMaterno.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbMaterno.setForeground(new java.awt.Color(153, 0, 0));

        jlbNombre.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbNombre.setForeground(new java.awt.Color(153, 0, 0));

        jlbFecha.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbFecha.setForeground(new java.awt.Color(153, 0, 0));

        jlbGenero.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbGenero.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(rdMasculino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdFemenino))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jlbIdAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtIdAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlbPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jlbIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbIdCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jlbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbIdCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtIdAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbIdAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calenderFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(rdMasculino)
                    .addComponent(rdFemenino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ALUMNO");

        jlbMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlbMensaje.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FECHA:");

        FECHA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        FECHA.setForeground(new java.awt.Color(255, 255, 255));
        FECHA.setText("00/00/00");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("HORA:");

        HORA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        HORA.setForeground(new java.awt.Color(255, 255, 255));
        HORA.setText("00:00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jlbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHA)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HORA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbMensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(FECHA)
                            .addComponent(jLabel4)
                            .addComponent(HORA))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbIdCarreraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbIdCarreraKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_cbIdCarreraKeyReleased

    private void calenderFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calenderFechaKeyReleased
        // TODO add your handling code here:
        validar();

    }//GEN-LAST:event_calenderFechaKeyReleased

    private void rdMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMasculinoActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdMasculinoActionPerformed

    private void rdMasculinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdMasculinoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdMasculinoKeyReleased

    private void rdFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdFemeninoActionPerformed
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdFemeninoActionPerformed

    private void rdFemeninoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rdFemeninoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_rdFemeninoKeyReleased

    private void tbAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlumnoMouseClicked
        int seleccion=tbAlumno.rowAtPoint(evt.getPoint());
        txtIdAlumno.setText(tbAlumno.getValueAt(seleccion, 0)+"");
        txtNombre.setText(tbAlumno.getValueAt(seleccion, 1)+"");
        txtPaterno.setText(tbAlumno.getValueAt(seleccion, 2)+"");
        txtMaterno.setText(tbAlumno.getValueAt(seleccion, 3)+"");
        txtSemestre.setText(tbAlumno.getValueAt(seleccion, 4)+"");
        txtIdPersona.setText(txtIdAlumno.getText());
        validar();
    }//GEN-LAST:event_tbAlumnoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Carrera selectedCarrera = (Carrera) cbIdCarrera.getSelectedItem();
        if(selectedCarrera==null ||txtIdAlumno.getText().isEmpty() || txtSemestre.getText().isEmpty()
                || txtNombre.getText().isEmpty() || txtPaterno.getText().isEmpty() || txtMaterno.getText().isEmpty()
                || txtEdad.getText().isEmpty() || calenderFecha.getDate()==null || !(rdMasculino.isSelected() || rdFemenino.isSelected())){
            JOptionPane.showMessageDialog(null, "LLENE LOS CAMPOS");
        }else{
            char genero;
            if(rdMasculino.isSelected()){
                genero='M';
            }else{
                genero='F';
            }
            Date fecha_Nacimiento=calenderFecha.getDate();
            if(fecha_Nacimiento==null){
                JOptionPane.showMessageDialog(null, "Seleccione una fecha valida");
                return;
            }
            int id_Usuario=UsuarioContexto.getId_Usuario();
            int idCarreraSeleccionada=selectedCarrera.getId_Carrera();
            Persona persona = new Persona();
            persona.setId_Persona(Integer.parseInt(txtIdPersona.getText()));
            persona.setNombre_Persona(txtNombre.getText());
            persona.setPaterno_Persona(txtPaterno.getText());
            persona.setMaterno_Persona(txtMaterno.getText());
            persona.setEdad_Persona(Integer.parseInt(txtEdad.getText()));
            persona.setFecha_nacimiento(new java.sql.Date(fecha_Nacimiento.getTime()));
            persona.setGenero_Persona((char)genero);
            persona.setId_Usuario(id_Usuario);
            Alumno alumno =new Alumno();
            alumno.setId_Alumno(Integer.parseInt(txtIdAlumno.getText()));
            alumno.setSemestre(Integer.parseInt(txtSemestre.getText()));
            alumno.setId_Persona(Integer.parseInt(txtIdPersona.getText()));
            alumno.setId_Carrera(idCarreraSeleccionada);
            alumno.setId_Usuario(id_Usuario);
            String mensajeP=personabo.agregarPersona(persona);
            String mensajeA=alumnobo.agregarAlumno(alumno);
            if(mensajeP.contains("PERSONA GUARDADA CORRECTAMENTE") && mensajeA.contains("ALUMNO GUARDADO CORRECTAMENTE")){
                JOptionPane.showMessageDialog(null, "ALUMNO GUARDADO CORRECTAMENTE");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR UN ALUMNO");
            }
            limpiar();
            listarAlumno(tbAlumno, idCarreraSeleccionada);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Carrera selectedCarrera = (Carrera) cbIdCarrera.getSelectedItem();
        if(selectedCarrera==null  ||txtIdAlumno.getText().isEmpty() || txtSemestre.getText().isEmpty()
                || txtNombre.getText().isEmpty() || txtPaterno.getText().isEmpty() || txtMaterno.getText().isEmpty()
                || txtEdad.getText().isEmpty() || calenderFecha.getDate()==null || !(rdMasculino.isSelected() || rdFemenino.isSelected())){
            JOptionPane.showMessageDialog(null, "LLENE LOS CAMPOS");
        }else{
            char genero;
            if(rdMasculino.isSelected()){
                genero='M';
            }else{
                genero='F';
            }
            Date fecha_Nacimiento=calenderFecha.getDate();
            if(fecha_Nacimiento==null){
                JOptionPane.showMessageDialog(null, "Seleccione una fecha valida");
                return;
            }
            int id_Usuario=UsuarioContexto.getId_Usuario();
            int idCarreraSeleccionada=selectedCarrera.getId_Carrera();
            Persona persona = new Persona();
            persona.setId_Persona(Integer.parseInt(txtIdPersona.getText()));
            persona.setNombre_Persona(txtNombre.getText());
            persona.setPaterno_Persona(txtPaterno.getText());
            persona.setMaterno_Persona(txtMaterno.getText());
            persona.setEdad_Persona(Integer.parseInt(txtEdad.getText()));
            persona.setFecha_nacimiento(new java.sql.Date(fecha_Nacimiento.getTime()));
            persona.setGenero_Persona((char)genero);
            persona.setId_Usuario(id_Usuario);
            Alumno alumno =new Alumno();
            alumno.setId_Alumno(Integer.parseInt(txtIdAlumno.getText()));
            alumno.setSemestre(Integer.parseInt(txtSemestre.getText()));
            alumno.setId_Persona(Integer.parseInt(txtIdPersona.getText()));
            alumno.setId_Carrera(idCarreraSeleccionada);
            alumno.setId_Usuario(id_Usuario);
            String mensajeP=personabo.modificarPersona(persona);
            String mensajeA=alumnobo.modificarAlumno(alumno);
            if(mensajeP.contains("PERSONA GUARDADA CORRECTAMENTE") && mensajeA.contains("ALUMNO GUARDADO CORRECTAMENTE")){
                JOptionPane.showMessageDialog(null, "ALUMNO GUARDADO CORRECTAMENTE");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR UN ALUMNO");
            }
            limpiar();
            listarAlumno(tbAlumno, idCarreraSeleccionada);
        }        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Carrera selectedCarrera = (Carrera) cbIdCarrera.getSelectedItem();
        int idCarreraSeleccionada=selectedCarrera.getId_Carrera();
        if(txtIdAlumno.getText().isEmpty() || txtIdPersona.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "LLENE LOS CAMPOS DE ID");
        }else{
            String mensajeA=alumnobo.eliminarAlumno(Integer.parseInt(txtIdAlumno.getText()));
            String mensajeP=personabo.eliminarPersona(Integer.parseInt(txtIdAlumno.getText()));
            if(mensajeA.contains("ALUMNO ELIMINADO CORRECTAMENTE") && mensajeP.contains("PERSONA ELIMINADA CORRECTAMENTE")){
                JOptionPane.showMessageDialog(null, "ALUMNO ELIMINADO CORRECTAMENTE");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMININAR EL ALUMNO");
            }
            limpiar();
            listarAlumno(tbAlumno, idCarreraSeleccionada);
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
            MenuSecundarioAdm menu1 = new MenuSecundarioAdm(nombreUsuario);
            menu1.setVisible(true);
        }else if(tipoUsuario.equals("P")){
            MenuPrincipalPersonal menu = new MenuPrincipalPersonal(nombreUsuario);
            menu.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtIdAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAlumnoActionPerformed

    private void txtIdAlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdAlumnoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtIdAlumnoKeyReleased

    private void txtSemestreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemestreKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtSemestreKeyReleased

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

    private void txtSemestreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSemestreKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >=48 && key  <=57;
        
        if(!numero){
            evt.consume();
        }
        
        if(txtSemestre.getText().trim().length()== 2){
            evt.consume();
        }
    }//GEN-LAST:event_txtSemestreKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if(txtNombre.getText().length() >=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaternoKeyTyped
        // TODO add your handling code here:
        if(txtPaterno.getText().length() >=20){
            evt.consume();
        }
    }//GEN-LAST:event_txtPaternoKeyTyped

    private void txtMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaternoKeyTyped
        // TODO add your handling code here:
        if(txtMaterno.getText().length() >=20){
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
            java.util.logging.Logger.getLogger(FrmAlumno_Per.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAlumno_Per.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAlumno_Per.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAlumno_Per.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAlumno_Per().setVisible(true);
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
    private com.toedter.calendar.JDateChooser calenderFecha;
    private javax.swing.JComboBox<Carrera> cbIdCarrera;
    private javax.swing.ButtonGroup groupGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbEdad;
    private javax.swing.JLabel jlbFecha;
    private javax.swing.JLabel jlbGenero;
    private javax.swing.JLabel jlbIdAlumno;
    private javax.swing.JLabel jlbIdCarrera;
    private javax.swing.JLabel jlbMaterno;
    private javax.swing.JLabel jlbMensaje;
    private javax.swing.JLabel jlbNombre;
    private javax.swing.JLabel jlbPaterno;
    private javax.swing.JLabel jlbSemestre;
    private javax.swing.JRadioButton rdFemenino;
    private javax.swing.JRadioButton rdMasculino;
    private javax.swing.JTable tbAlumno;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtIdAlumno;
    private javax.swing.JTextField txtIdPersona;
    private javax.swing.JTextField txtMaterno;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPaterno;
    private javax.swing.JTextField txtSemestre;
    // End of variables declaration//GEN-END:variables
}
