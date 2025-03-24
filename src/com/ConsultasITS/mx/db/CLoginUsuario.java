/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.db;

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
public class CLoginUsuario {
    public boolean validarUsuario1(JTextField usuarioA, JPasswordField contrasena){
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            com.ConsultasITS.mx.db.Conexion objetoConexion2 = new com.ConsultasITS.mx.db.Conexion();
            String sql="SELECT * FROM USUARIO U WHERE U.INGRESO_USUARIO=? AND U.INGRESO_CONTRASENA=? AND TIPO_USUARIO='A'";
            ps=objetoConexion2.getConnection().prepareStatement(sql);
            String contra=String.valueOf(contrasena.getPassword());
            ps.setString(1, usuarioA.getText());
            ps.setString(2, contra);
            rs=ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "USUARIO ADMINISTRADOR CORRECTO");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "USUARIO ADMINISTRADOR INCORRECTO");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
            return false;
        }
    }
}
