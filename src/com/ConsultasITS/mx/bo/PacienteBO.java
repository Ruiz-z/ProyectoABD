
package com.ConsultasITS.mx.bo;

import com.ConsultasITS.mx.dao.PacienteDAO;
import com.ConsultasITS.mx.entity.Paciente;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author kevin
 */
public class PacienteBO {
    private String mensaje="";
    private PacienteDAO paciedao= new PacienteDAO();
    
    public String agregarPaciente(Paciente pacie){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=paciedao.agregarPaciente(conn, pacie);
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
    public Paciente buscarPacientePorId(int idPaciente){
        Connection conn=Conexion.getConnection();
        Paciente paciente=null;
        try {
            paciente=paciedao.buscarPacientePorId(conn, idPaciente);
        } catch (Exception e) {
            System.out.println("Error al encontrar: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al desconectar: " + e.getMessage());
            }
        }
        return paciente;
    }
    public String modificarPaciente(Paciente pacie){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=paciedao.modificarPaciente(conn, pacie);
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
    
    public String eliminarPaciente(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=paciedao.eliminarPaciente(conn, id);
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
    
    public void listarPaciente(JTable tabla){
        Connection conn = Conexion.getConnection();
        paciedao.listarPaciente(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=paciedao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
