/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ConsultasITS.mx.view;

import com.ConsultasITS.mx.Pruebas_Menu.MneuPrincipalAdm;

/**
 *
 * @author kevin
 */
public class FrmLoginUsuarioA extends javax.swing.JFrame {

    /**
     * Creates new form FrmLoginUsuarioA
     */
    public FrmLoginUsuarioA() {
        initComponents();
        btn_Aceptar.setEnabled(false);
    }

    public void validar(){
        if(txt_UsuarioA.getText().isEmpty()){
            jlbUser.setText("*Oblifatorio");
        }else{
            jlbUser.setText("");
        }
        if(txtContraseñaA.getText().isEmpty()){
            jlbPassword.setText("*Obligatorio");
        }else{
            jlbPassword.setText("");
        }
        if(txt_UsuarioA.getText().isEmpty() || txtContraseñaA.getText().isEmpty()){
            btn_Aceptar.setEnabled(false);
        }else{
            btn_Aceptar.setEnabled(true);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_UsuarioA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_Salir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        txtContraseñaA = new javax.swing.JPasswordField();
        btn_Salir1 = new javax.swing.JButton();
        jlbUser = new javax.swing.JLabel();
        jlbPassword = new javax.swing.JLabel();
        IniS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(30, 79, 111));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("LOGIN DE USUARIO ADMINISTRADOR");

        txt_UsuarioA.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_UsuarioA.setMaximumSize(new java.awt.Dimension(20, 20));
        txt_UsuarioA.setMinimumSize(new java.awt.Dimension(20, 20));
        txt_UsuarioA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_UsuarioAActionPerformed(evt);
            }
        });
        txt_UsuarioA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_UsuarioAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_UsuarioAKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("Usuario");

        btn_Salir.setText("SALIR");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirSalir(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setText("Contraseña");

        btn_Aceptar.setText("INGRESAR");
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarInicioS(evt);
            }
        });

        txtContraseñaA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaAKeyTyped(evt);
            }
        });

        btn_Salir1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_Salir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha_izq.png"))); // NOI18N
        btn_Salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir1Salir(evt);
            }
        });

        jlbUser.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        jlbUser.setForeground(new java.awt.Color(204, 0, 0));

        jlbPassword.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        jlbPassword.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtContraseñaA))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_UsuarioA, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_UsuarioA, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jlbUser))
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtContraseñaA, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbPassword))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(btn_Salir1)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IniS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(IniS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UsuarioAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_UsuarioAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_UsuarioAActionPerformed

    private void txt_UsuarioAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UsuarioAKeyTyped
        if(txt_UsuarioA.getText().length() >=20){
            evt.consume();
        }
    }//GEN-LAST:event_txt_UsuarioAKeyTyped

    private void btn_SalirSalir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirSalir

        System.exit(0);
    }//GEN-LAST:event_btn_SalirSalir

    private void btn_AceptarInicioS(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarInicioS
        com.ConsultasITS.mx.db.CLoginUsuario objetoLogin = new com.ConsultasITS.mx.db.CLoginUsuario();

        if(objetoLogin.validarUsuario1(txt_UsuarioA, txtContraseñaA)){
            dispose();
            FrmUsuario abrir = new FrmUsuario();
            abrir.setVisible(true);
        }else{
            limpiar();
        }

    }//GEN-LAST:event_btn_AceptarInicioS

    private void txtContraseñaAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaAKeyTyped
        if(txtContraseñaA.getPassword().length >= 20){
            evt.consume();
        }
    }//GEN-LAST:event_txtContraseñaAKeyTyped

    private void btn_Salir1Salir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir1Salir
        // TODO add your handling code here:
        String nombreUsuario=UsuarioContexto.getNombreUsuario();
        MneuPrincipalAdm abrir = new MneuPrincipalAdm(nombreUsuario);
        abrir.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_Salir1Salir

    private void txt_UsuarioAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UsuarioAKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txt_UsuarioAKeyReleased

    private void txtContraseñaAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaAKeyReleased
        // TODO add your handling code here:
        validar();
    }//GEN-LAST:event_txtContraseñaAKeyReleased

    public void limpiar(){
        txtContraseñaA.setText("");
        txt_UsuarioA.setText("");
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
            java.util.logging.Logger.getLogger(FrmLoginUsuarioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLoginUsuarioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLoginUsuarioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLoginUsuarioA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLoginUsuarioA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IniS;
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_Salir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jlbPassword;
    private javax.swing.JLabel jlbUser;
    private javax.swing.JPasswordField txtContraseñaA;
    private javax.swing.JTextField txt_UsuarioA;
    // End of variables declaration//GEN-END:variables
}
