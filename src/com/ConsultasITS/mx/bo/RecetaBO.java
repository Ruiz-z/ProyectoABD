
package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.RecetaDAO;
import com.ConsultasITS.mx.entity.Receta;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;


public class RecetaBO {
    private String mensaje="";
    private RecetaDAO recedao= new RecetaDAO();
    
    public String agregarReceta(Receta rece){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=recedao.agregarReceta(conn, rece);
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
    public Receta buscarRecetaPorId(int idReceta){
        Connection conn=Conexion.getConnection();
        Receta receta=null;
        try {
            receta=recedao.buscarRecetaPorId(conn, idReceta);
        } catch (Exception e) {
            System.out.println("Error al encontrar Receta: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al desconectar: " + e.getMessage());
            }
        }
        return receta;
    }
    public String modificarRceta(Receta rece){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=recedao.modificarReceta(conn, rece);
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
    
    public String eliminarReceta(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=recedao.eliminarReceta(conn, id);
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
    
    public void listarAlumno(JTable tabla){
        Connection conn = Conexion.getConnection();
        recedao.listarReceta(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=recedao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
