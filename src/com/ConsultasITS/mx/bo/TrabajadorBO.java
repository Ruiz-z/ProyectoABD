package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.TrabajadorDAO;
import com.ConsultasITS.mx.entity.Trabajador;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class TrabajadorBO {
     private String mensaje="";
    private TrabajadorDAO trabadao= new TrabajadorDAO();
    
    public String agregarTrabajador(Trabajador traba){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=trabadao.agregarTrabajador(conn, traba);
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
    public Trabajador buscarTrabajadorPorId(int idTrabajador){
        Connection conn=Conexion.getConnection();
        Trabajador trabajador=null;
        try {
            trabajador=trabadao.buscarTrabajadorPorId(conn, idTrabajador);
        } catch (Exception e) {
            System.out.println("Error al encontrar trabajador: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return trabajador;
    }
    public String modificarTrabajador(Trabajador traba){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=trabadao.modificarTrabajador(conn, traba);
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
    
    public String eliminarTrabajador(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=trabadao.eliminarTrabajador(conn, id);
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
    
    public void listarTrabajador(JTable tabla){
        Connection conn = Conexion.getConnection();
        trabadao.listarTrabajador(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=trabadao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
