/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.Pruebas_Menu;

import com.ConsultasITS.mx.bo.BusquedasBO;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Medicamento;
import com.ConsultasITS.mx.entity.Medicamento_Has_Receta;
import com.ConsultasITS.mx.entity.Personal_Salud;
import com.ConsultasITS.mx.view.UsuarioContexto;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author kevin
 */
public class FrmReporteAlumnoxCarrera extends javax.swing.JFrame {

    private String usuario=UsuarioContexto.getNombreReal();
    private String paterno=UsuarioContexto.getAp_Paterno();
    private String materno=UsuarioContexto.getAp_Materno();
    /**
     * Creates new form FrmReporteAlumnoxCarrera
     */
    public FrmReporteAlumnoxCarrera() {
        initComponents();
        setTitle("FORMULARIO Busqueda por Medicamento");
        setLocationRelativeTo(null);
        setResizable(false);
        jlbMensaje.setText("USUARIO: " + usuario +paterno+materno);
        Calendar cal =  Calendar.getInstance();
         String fecha, hora;
         fecha=cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
         hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE);
         this.FECHA.setText(fecha);
         this.HORA.setText(hora);
         initcomponents1();
         consultaIdMedicamento(cbIdmedicamento);
         
    }
    private BusquedasBO busquedasbo = new BusquedasBO();
    public void listarMedicamento(JTable tabla, int id_medicamento){
        busquedasbo.listarMedicamento(tabla, id_medicamento);
    }
   public void validar() {
       Medicamento selectedMedicamento = (Medicamento) cbIdmedicamento.getSelectedItem();
    if (selectedMedicamento == null || selectedMedicamento.getId_Medicamento() == 0) {
        jlbIdmedicamento.setText("*Obligatorio");
    } else {
        jlbIdmedicamento.setText("");
    }
}
public static void consultaIdMedicamento(JComboBox<Medicamento> cbIdMedicamento) { Connection conn = Conexion.getConnection(); PreparedStatement ps = null; ResultSet rs = null; try { String sql = "SELECT ID_MEDICAMENTO, NOMBRE_MEDICAMENTO FROM MEDICAMENTO"; ps = conn.prepareStatement(sql); rs = ps.executeQuery(); cbIdMedicamento.addItem(new Medicamento(0, "Seleccione", null, null, 0)); while (rs.next()) { int idMedicamento = rs.getInt("ID_MEDICAMENTO"); String nombre = rs.getString("NOMBRE_MEDICAMENTO"); cbIdMedicamento.addItem(new Medicamento(idMedicamento, nombre, null, null, 0)); } } catch (Exception e) { e.printStackTrace(); } finally { try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); } } } // Incluye el método initcomponents1 si existe
    public void initcomponents1(){
      // Llama a validar cuando se seleccione una opción en el ComboBox de Carrera
        cbIdmedicamento.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
        validar();  // Llama a validar al cambiar la selección
        }
        });
        
        cbIdmedicamento.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtén el ID de la carrera seleccionada
        Medicamento MedicamentoSeleccionada = (Medicamento) cbIdmedicamento.getSelectedItem();
        int id_medicamento = MedicamentoSeleccionada.getId_Medicamento();
         listarMedicamento(tbMedicamento, id_medicamento);
        
        // Llama a listarAlumno con el ID de la carrera seleccionada
          // `tbAlumno` es el JTable donde muestras los alumnos
    }
});
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
        jLabel1 = new javax.swing.JLabel();
        jlbMensaje = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FECHA = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        HORA = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMedicamento = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnModificar2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cbIdmedicamento = new javax.swing.JComboBox<>();
        jlbIdmedicamento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(30, 79, 111));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Busqueda");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Busqueda por Medicamento");

        tbMedicamento.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMedicamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMedicamentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMedicamento);

        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnModificar2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar2.setText("<--");
        btnModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificar2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Medicamento");

        cbIdmedicamento.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cbIdmedicamentoInputMethodTextChanged(evt);
            }
        });
        cbIdmedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbIdmedicamentoKeyReleased(evt);
            }
        });

        jlbIdmedicamento.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlbIdmedicamento.setForeground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 447, Short.MAX_VALUE)
                .addComponent(btnModificar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir)
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbIdmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbIdmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel3)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbIdmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbIdmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar2)
                    .addComponent(btnSalir))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jlbMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FECHA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HORA)
                .addGap(17, 17, 17))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlbMensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(FECHA)
                        .addComponent(jLabel4)
                        .addComponent(HORA)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMedicamentoMouseClicked

    }//GEN-LAST:event_tbMedicamentoMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificar2ActionPerformed
        // TODO add your handling code here:
        FrmMenuReportes abrir = new FrmMenuReportes(usuario);
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnModificar2ActionPerformed

    private void cbIdmedicamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbIdmedicamentoKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_cbIdmedicamentoKeyReleased

    private void cbIdmedicamentoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbIdmedicamentoInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIdmedicamentoInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(FrmReporteAlumnoxCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReporteAlumnoxCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReporteAlumnoxCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReporteAlumnoxCarrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReporteAlumnoxCarrera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FECHA;
    private javax.swing.JLabel HORA;
    private javax.swing.JButton btnModificar2;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<Medicamento> cbIdmedicamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbIdmedicamento;
    private javax.swing.JLabel jlbMensaje;
    private javax.swing.JTable tbMedicamento;
    // End of variables declaration//GEN-END:variables

}
