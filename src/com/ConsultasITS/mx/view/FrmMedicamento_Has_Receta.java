/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;
import com.ConsultasITS.mx.Pruebas_Menu.MenuPrincipalPersonal;
import com.ConsultasITS.mx.Pruebas_Menu.MenuSecundarioAdm;
import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;
import com.ConsultasITS.mx.bo.Medicamento_Has_RecetaBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Consulta;
import com.ConsultasITS.mx.entity.Medicamento;
import com.ConsultasITS.mx.entity.Medicamento_Has_Receta;
import com.ConsultasITS.mx.entity.Receta;
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
public class FrmMedicamento_Has_Receta extends javax.swing.JFrame {

    /**
     * Creates new form FrmMedicamento_Has_Receta
     */
    public FrmMedicamento_Has_Receta() {
        initComponents();
        listarMedicamento_has_Receta();
        idMax();
        setTitle("FORMULARIO DE MEDICAMENTO HAS RECETA");
        setLocationRelativeTo(null);
        setResizable(false);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        initComponents2();
        consultaIdMedicamento(cbIdmedicamento);
        consultaIdConsulta(cbIdConsulta);
        consultaIdReceta(cbIdReceta);
    }
    
  public void initComponents2() {
    // Llama a validar cuando se seleccione una opción en el ComboBox de Persona
    cbIdConsulta.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            validar();  // Llama a validar al cambiar la selección
        }
    });

    cbIdmedicamento.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            validar();
        }
    });

    cbIdReceta.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            validar();
        }
    });

    
}
    public static void consultaIdMedicamento(JComboBox<Medicamento> cbIdmedicamento){
        Connection conn = Conexion.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="SELECT ID_MEDICAMENTO, NOMBRE_MEDICAMENTO FROM MEDICAMENTO";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cbIdmedicamento.addItem(new Medicamento(0, "Seleccione", "", null, 0));
            while(rs.next()){
                int idMedicamento = rs.getInt("ID_MEDICAMENTO");
                String nombre=rs.getString("NOMBRE_MEDICAMENTO");
                cbIdmedicamento.addItem(new Medicamento(idMedicamento, nombre, "", null, 0));
            }
            // Verificar si hay elementos antes de seleccionar un índice
        if (cbIdmedicamento.getItemCount() > 0) {
            cbIdmedicamento.setSelectedIndex(0);  // Seleccionar el primer elemento
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
    public static void consultaIdReceta(JComboBox<Receta> cbIdReceta){
    Connection conn = Conexion.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT ID_RECETA, FECHA_RECETA, DOSIS FROM RECETA";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        cbIdReceta.addItem(new Receta(0, null, "Seleccione", "", 0, ' ', 0, "", "", 0));  // Item inicial
        while (rs.next()) {
            int idReceta = rs.getInt("ID_RECETA");
            cbIdReceta.addItem(new Receta(idReceta, null, "", "", 0, ' ', 0, "", "", 0));
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


    public static void consultaIdConsulta(JComboBox<Consulta> cbIdConsulta){
    Connection conn = Conexion.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT ID_CONSULTA FROM CONSULTA";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        // Agrega el primer ítem por defecto
        cbIdConsulta.addItem(new Consulta(0, null, null, 0, 0, 0, 0));

        
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
    
    



    public void validar() {
    // Validar el campo de ID Medicamento
    Medicamento medicamentoSeleccionado = (Medicamento) cbIdmedicamento.getSelectedItem();
    if (medicamentoSeleccionado == null || medicamentoSeleccionado.getId_Medicamento() == 0) { // Verifica si es el ítem "Seleccione"
        jlbIdMedicamento.setText("*Obligatorio");
    } else {
        jlbIdMedicamento.setText("");
    }

    // Validar el campo de ID Consulta
    Consulta consultaSeleccionada = (Consulta) cbIdConsulta.getSelectedItem();
    if (consultaSeleccionada == null || consultaSeleccionada.getId_Consulta() == 0) { // Verifica si es el ítem "Seleccione"
        jlbIdConsulta.setText("*Obligatorio");
    } else {
        jlbIdConsulta.setText("");
    }

    // Validar el campo de ID Receta
    Receta recetaSeleccionada = (Receta) cbIdReceta.getSelectedItem();
    if (recetaSeleccionada == null || recetaSeleccionada.getId_Receta() == 0) { // Verifica si es el ítem "Seleccione"
        jlbIdReceta.setText("*Obligatorio");
    } else {
        jlbIdReceta.setText("");
    }

    // Habilitar o deshabilitar botones en función de las validaciones
    if (medicamentoSeleccionado == null || medicamentoSeleccionado.getId_Medicamento() == 0 ||
        consultaSeleccionada == null || consultaSeleccionada.getId_Consulta() == 0 ||
        recetaSeleccionada == null || recetaSeleccionada.getId_Receta() == 0) {
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    } else {
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
}

    private Medicamento_Has_RecetaBO mhrbo = new Medicamento_Has_RecetaBO();
    public void listarMedicamento_has_Receta(){
        mhrbo.listarMedicamento_has_Receta(tbMedicamento_has_Receta);
    }
    public void idMax() {
    // Obtener el ID máximo de la tabla Medicamento_Has_Receta
    int maxId = mhrbo.getMaxID();
    
    
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
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_MostrarM = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btn_MostrarM1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedicamento_has_Receta = new javax.swing.JTable();
        cbIdReceta = new javax.swing.JComboBox<>();
        cbIdConsulta = new javax.swing.JComboBox<>();
        cbIdmedicamento = new javax.swing.JComboBox<>();
        jlbIdMedicamento = new javax.swing.JLabel();
        jlbIdConsulta = new javax.swing.JLabel();
        jlbIdReceta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LogoP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID Medicamento:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("ID Consulta:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("ID Receta:");

        btn_MostrarM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_MostrarM.setText("Back Menu");
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
                .addGap(14, 14, 14)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar)
                .addGap(38, 38, 38)
                .addComponent(btn_MostrarM)
                .addGap(18, 18, 18)
                .addComponent(btn_MostrarM1)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_MostrarM)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btn_MostrarM1))
                .addGap(17, 17, 17))
        );

        tbMedicamento_has_Receta.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMedicamento_has_Receta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMedicamento_has_RecetaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMedicamento_has_Receta);

        jlbIdMedicamento.setForeground(new java.awt.Color(204, 0, 0));

        jlbIdConsulta.setForeground(new java.awt.Color(204, 0, 0));

        jlbIdReceta.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbIdmedicamento, 0, 158, Short.MAX_VALUE)
                    .addComponent(cbIdConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbIdReceta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbIdMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbIdReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbIdmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbIdMedicamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbIdConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbIdConsulta))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbIdReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbIdReceta))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Medicamento_has_Receta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LogoP, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbMedicamento_has_RecetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMedicamento_has_RecetaMouseClicked
        int seleccion = tbMedicamento_has_Receta.rowAtPoint(evt.getPoint());

    // Seleccionar el Medicamento en el JComboBox
    int idMedicamento = Integer.parseInt(tbMedicamento_has_Receta.getValueAt(seleccion, 0).toString());
    for (int i = 0; i < cbIdmedicamento.getItemCount(); i++) {
        Medicamento medicamento = cbIdmedicamento.getItemAt(i);
        if (medicamento.getId_Medicamento() == idMedicamento) {
            cbIdmedicamento.setSelectedIndex(i);
            break;
        }
    }

    // Seleccionar la Consulta en el JComboBox
    int idConsulta = Integer.parseInt(tbMedicamento_has_Receta.getValueAt(seleccion, 1).toString());
    for (int i = 0; i < cbIdConsulta.getItemCount(); i++) {
        Consulta consulta = cbIdConsulta.getItemAt(i);
        if (consulta.getId_Consulta() == idConsulta) {
            cbIdConsulta.setSelectedIndex(i);
            break;
        }
    }

    // Seleccionar la Receta en el JComboBox
    int idReceta = Integer.parseInt(tbMedicamento_has_Receta.getValueAt(seleccion, 2).toString());
    for (int i = 0; i < cbIdReceta.getItemCount(); i++) {
        Receta receta = cbIdReceta.getItemAt(i);
        if (receta.getId_Receta() == idReceta) {
            cbIdReceta.setSelectedIndex(i);
            break;
        }
    }

    validar(); 
    }//GEN-LAST:event_tbMedicamento_has_RecetaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (cbIdmedicamento.getSelectedIndex() == 0 || cbIdConsulta.getSelectedIndex() == 0 || cbIdReceta.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
    } else {
        Medicamento_Has_Receta mhr = new Medicamento_Has_Receta();

        // Obtener los objetos seleccionados de los JComboBox
        Medicamento medicamento = (Medicamento) cbIdmedicamento.getSelectedItem();
        Consulta consulta = (Consulta) cbIdConsulta.getSelectedItem();
        Receta receta = (Receta) cbIdReceta.getSelectedItem();

        int id_Usuario=UsuarioContexto.getId_Usuario();
        mhr.setId_Medicamento(medicamento.getId_Medicamento());
        mhr.setId_Consulta(consulta.getId_Consulta());
        mhr.setId_Receta(receta.getId_Receta());
        mhr.setId_Usuario(id_Usuario);
        String mensaje = mhrbo.agregarMedicamento_has_Receta(mhr);
        JOptionPane.showMessageDialog(null, mensaje);
        limpiar();
        listarMedicamento_has_Receta();
    }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       if (cbIdmedicamento.getSelectedIndex() == 0 || cbIdConsulta.getSelectedIndex() == 0 || cbIdReceta.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
    } else {
        Medicamento_Has_Receta mhr = new Medicamento_Has_Receta();

        // Obtener los objetos seleccionados de los JComboBox
        Medicamento medicamento = (Medicamento) cbIdmedicamento.getSelectedItem();
        Consulta consulta = (Consulta) cbIdConsulta.getSelectedItem();
        Receta receta = (Receta) cbIdReceta.getSelectedItem();

        int id_Usuario=UsuarioContexto.getId_Usuario();
        mhr.setId_Medicamento(medicamento.getId_Medicamento());
        mhr.setId_Consulta(consulta.getId_Consulta());
        mhr.setId_Receta(receta.getId_Receta());

        mhr.setId_Usuario(id_Usuario);
        String mensaje = mhrbo.modificarMedicamento_has_receta(mhr);
        JOptionPane.showMessageDialog(null, mensaje);
        limpiar();
        listarMedicamento_has_Receta();
    }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (cbIdmedicamento.getSelectedIndex() == 0 || cbIdConsulta.getSelectedIndex() == 0 || cbIdReceta.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
    } else {
        Medicamento medicamento = (Medicamento) cbIdmedicamento.getSelectedItem();
        String mensaje = mhrbo.eliminarMedicamento_has_Receta(medicamento.getId_Medicamento());
        JOptionPane.showMessageDialog(null, mensaje);
        limpiar();
        listarMedicamento_has_Receta();
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

    
    public void limpiar(){
        cbIdmedicamento.setSelectedIndex(-1);
        cbIdConsulta.setSelectedIndex(-1);
        cbIdReceta.setSelectedIndex(-1);
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
            java.util.logging.Logger.getLogger(FrmMedicamento_Has_Receta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMedicamento_Has_Receta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMedicamento_Has_Receta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMedicamento_Has_Receta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMedicamento_Has_Receta().setVisible(true);
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
    private javax.swing.JComboBox<Consulta> cbIdConsulta;
    private javax.swing.JComboBox<Receta> cbIdReceta;
    private javax.swing.JComboBox<Medicamento> cbIdmedicamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbIdConsulta;
    private javax.swing.JLabel jlbIdMedicamento;
    private javax.swing.JLabel jlbIdReceta;
    private javax.swing.JTable tbMedicamento_has_Receta;
    // End of variables declaration//GEN-END:variables
}
