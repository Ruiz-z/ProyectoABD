
package com.ConsultasITS.mx.bo;

import com.ConsultasITS.mx.dao.Personal_SaludDAO;
import com.ConsultasITS.mx.entity.Personal_Salud;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class Personal_SaludBO {
     private String mensaje="";
    private Personal_SaludDAO persoSdao= new Personal_SaludDAO();
    
    public String agregarPersonal_Salud(Personal_Salud persoS){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=persoSdao.agregarPersonal_Salud(conn, persoS);
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
    public Personal_Salud buscarPersonalPorId(int idPersonal){
        Connection conn=Conexion.getConnection();
        Personal_Salud personal=null;
        try {
            personal=persoSdao.buscarPersonalPorId(conn, idPersonal);
        } catch (Exception e) {
            System.out.println("Error al encontrar Personal: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return personal;
    }
    public String modificarPersonal_Salud(Personal_Salud persoS){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=persoSdao.modificarPersonal_Salud(conn, persoS);
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
    
    public String eliminarPersonal_Salud(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=persoSdao.eliminarPersonal_Salud(conn, id);
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
    
    public void listarPersonal_Salud(JTable tabla){
        Connection conn = Conexion.getConnection();
        persoSdao.listarPersonal_Salud(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=persoSdao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
