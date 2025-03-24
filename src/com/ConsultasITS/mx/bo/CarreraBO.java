
package com.ConsultasITS.mx.bo;

import com.ConsultasITS.mx.dao.CarreraDAO;
import com.ConsultasITS.mx.entity.Carrera;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class CarreraBO {
    private String mensaje="";
    private CarreraDAO cardao= new CarreraDAO();
    
    public String agregarCarrera(Carrera car){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=cardao.agregarCarrera(conn, car);
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
    public Carrera buscarCarreraPorId(int idCarrera) {
    Connection conn = Conexion.getConnection();
    Carrera carrera = null;
    try {
        carrera = cardao.buscarCarreraPorId(conn, idCarrera);
    } catch (Exception e) {
        System.out.println("Error buscando la carrera: " + e.getMessage());
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error cerrando la conexión: " + e.getMessage());
        }
    }
    return carrera;
}

    public String modificarCarrera(Carrera car){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=cardao.modificarCarrera(conn, car);
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
    
    public String eliminarCarrera(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=cardao.eliminarCarrera(conn, id);
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
    
    public void listarCarrera(JTable tabla){
        Connection conn = Conexion.getConnection();
        cardao.listarCarrera(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=cardao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
