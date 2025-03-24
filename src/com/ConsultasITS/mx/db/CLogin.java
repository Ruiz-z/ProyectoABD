/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.db;

import com.ConsultasITS.mx.view.UsuarioContexto;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kevin
 */
public class CLogin {

    public String validarUsuario(JTextField usuario, JPasswordField contrasenia) {

        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            com.ConsultasITS.mx.db.Conexion objetoConexion1 = new com.ConsultasITS.mx.db.Conexion();
            String sql = "SELECT ID_USUARIO, TIPO_USUARIO,Nombre,Ap_Paterno,Ap_Materno FROM USUARIO U WHERE U.INGRESO_USUARIO=? AND U.INGRESO_CONTRASENA=?";
            ps = objetoConexion1.getConnection().prepareStatement(sql);
            String contra = String.valueOf(contrasenia.getPassword());
            ps.setString(1, usuario.getText());
            ps.setString(2, contra);
            rs = ps.executeQuery();
            if (rs.next()) {
                String tipo_usuario = rs.getString("TIPO_USUARIO");
                int id_Usuario = rs.getInt("ID_USUARIO");
                String nombreR = rs.getString("Nombre");
                String Ap_Paterno = rs.getString("Ap_Paterno");
                String Ap_Materno = rs.getString("Ap_Materno");
                UsuarioContexto.setId_Usuario(id_Usuario);
                UsuarioContexto.setTipoUsuario(tipo_usuario);
                UsuarioContexto.setNombreReal(nombreR);
                UsuarioContexto.setAp_Paterno(Ap_Paterno);
                UsuarioContexto.setAp_Materno(Ap_Materno);
                if (tipo_usuario.equals("A")) {
                    JOptionPane.showMessageDialog(null, "USUARIO TIPO ADMINISTRADOR CORRECTO");
                } else if (tipo_usuario.equals("P")) {
                    JOptionPane.showMessageDialog(null, "USUARIO TIPO PERSONAL CORRECTO");
                } else if (tipo_usuario.equals("R")) {
                    JOptionPane.showMessageDialog(null, "USUARIO TIPO RECEPCIONISTA CORRECTO");
                }
                return tipo_usuario;
            } else {
                JOptionPane.showMessageDialog(null, "USUARIO INCORRECTO RRELLENE LOS CAMPOS DE NUEVO");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return null;
        }
    }
}
