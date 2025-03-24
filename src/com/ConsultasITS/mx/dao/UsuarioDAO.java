/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.dao;

import com.ConsultasITS.mx.entity.Carrera;
import com.ConsultasITS.mx.entity.Personal_Salud;
import com.ConsultasITS.mx.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author kevin
 */
public class UsuarioDAO {

    private String mensaje = "";

    public String agregarUsuario(Connection conn, Usuario user) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO USUARIO(ID_USUARIO, INGRESO_USUARIO, INGRESO_CONTRASENA, TIPO_USUARIO,Nombre,Ap_Paterno,Ap_Materno) "
                + "VALUES(?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getId_Usuario());
            pst.setString(2, user.getIngreso_usuario());
            pst.setString(3, user.getIngreso_contrasena());
            pst.setString(4, user.getTipo_usuario() + "");
            pst.setString(5, user.getNombre() + "");
            pst.setString(6, user.getAp_Paterno() + "");
            pst.setString(7, user.getAp_Materno() + "");

            mensaje = "GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje = "NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }

    public Usuario buscarUsuarioPorId(Connection conn, int idUsuario) {
        Usuario usuario = null;
        try {
            // Verifica que estás seleccionando todos los campos necesarios
            String sql = "SELECT ID_USUARIO, INGRESO_USUARIO, INGRESO_CONTRASENA, TIPO_USUARIO,Nombre, Ap_Paterno, Ap_Materno FROM USUARIO WHERE ID_USUARIO = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);  // Usamos el ID que ingresa el usuario
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId_Usuario(rs.getInt("ID_USUARIO"));  // Obtener ID_USUARIO
                usuario.setIngreso_usuario(rs.getString("INGRESO_USUARIO"));  // Obtener INGRESO_USUARIO
                usuario.setIngreso_contrasena(rs.getString("INGRESO_CONTRASENA"));  // Obtener INGRESO_CONTRASENA
                usuario.setTipo_usuario(rs.getString("TIPO_USUARIO").charAt(0));  // Obtener TIPO_USUARIO como char
                usuario.setNombre(rs.getString("INGRESO_Nombre"));
                usuario.setAp_Paterno(rs.getString("INGRESO_Paterno"));
                usuario.setAp_Materno(rs.getString("INGRESO_Materno"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error buscando el usuario: " + e.getMessage());
        }
        return usuario;
    }

    public String modificarUsuario(Connection conn, Usuario user) {
        PreparedStatement pst = null;
        String sql = "UPDATE USUARIO SET  INGRESO_USUARIO=?, INGRESO_CONTRASENA=?, TIPO_USUARIO=?,INGRESO_Nombre=?,INGRESO_Paterno=?,INGRESO_Materno=?"
                + "WHERE ID_USUARIO =?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getIngreso_usuario());
            pst.setString(2, user.getIngreso_contrasena());
            pst.setString(3, user.getTipo_usuario() + "");
            pst.setInt(4, user.getId_Usuario());
            pst.setString(5, user.getNombre() + "");
            pst.setString(6, user.getAp_Paterno() + "");
            pst.setString(7, user.getAp_Materno() + "");
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                mensaje = "ACTUALIZADO CORRECTAMENTE";
            } else {
                mensaje = "NO SE PUDO ACTUALIZAR ID NO ENCONTRADO";
            }
            pst.close();
        } catch (SQLException e) {
            mensaje = "NO SE PUDO ACTUALIZAR CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarUsuario(Connection conn, int id) {
        PreparedStatement pst = null;
        String sql = "DELETE FROM USUARIO WHERE ID_USUARIO= ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            mensaje = "ELIMINADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje = "NO SE PUDO ELIMINAR CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }

    public void listarUsuario(Connection conn, JTable tabla) {
        DefaultTableModel model;
        String[] columnas = {"ID", "INGRESO", "CONTRASEÑA", "TIPO","Nombre","Apellido Paterno","Apellido Materno"};
        model = new DefaultTableModel(null, columnas);

        String sql = "SELECT * FROM USUARIO ORDER BY ID_USUARIO";
        String[] filas = new String[7];
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 7; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDE MOSTRAR LA TABLA");
        }
    }

    public int getMaxID(Connection conn) {
        int id = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(ID_USUARIO)+1 FROM USUARIO";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL MOSTRAR MENSAJE...." + e.getMessage());
        }
        return id;
    }
}
