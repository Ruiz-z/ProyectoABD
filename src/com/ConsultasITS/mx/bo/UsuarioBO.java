/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.Personal_SaludDAO;
import com.ConsultasITS.mx.dao.UsuarioDAO;
import com.ConsultasITS.mx.entity.Usuario;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Carrera;
import com.ConsultasITS.mx.entity.Personal_Salud;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
/**
 *
 * @author kevin
 */
public class UsuarioBO {
    private String mensaje="";
    private UsuarioDAO userdao= new UsuarioDAO();
    
    public String agregarUsuario(Usuario user){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=userdao.agregarUsuario(conn, user);
         } catch (Exception e) {
             mensaje=mensaje + "" + e.getMessage();
         }finally{
             try {
                 if(conn!=null){
                     conn.close();
                 }
             } catch (Exception e) {
                 mensaje=mensaje + "" + e.getMessage();
             }
         }
        return mensaje;
    }
    
    public Usuario buscarUsuarioPorId(int idUsuario) {
    Connection conn = Conexion.getConnection();
    Usuario usuario = null;
    try {
        usuario = userdao.buscarUsuarioPorId(conn, idUsuario);
    } catch (Exception e) {
        System.out.println("Error buscando el Usuario: " + e.getMessage());
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error cerrando la conexión: " + e.getMessage());
        }
    }
    return usuario;
}
    
    public String modificarUsuario(Usuario user){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=userdao.modificarUsuario(conn, user);
         } catch (Exception e) {
             mensaje=mensaje + "" + e.getMessage();
         }finally{
             try {
                 if(conn!=null){
                     conn.close();
                 }
             } catch (Exception e) {
                 mensaje=mensaje + "" + e.getMessage();
             }
         }
        return mensaje;
    }
    
    public String eliminarUsuario(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=userdao.eliminarUsuario(conn, id);
         } catch (Exception e) {
             mensaje=mensaje + "" + e.getMessage();
         }finally{
             try {
                 if(conn!=null){
                     conn.close();
                 }
             } catch (Exception e) {
                 mensaje=mensaje + "" + e.getMessage();
             }
         }
        return mensaje;
    }
    
    public void listarUsuario(JTable tabla){
        Connection conn = Conexion.getConnection();
        userdao.listarUsuario(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=userdao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
