 package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.ConsultaDAO;
import com.ConsultasITS.mx.entity.Consulta;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class ConsultaBO {
    private String mensaje="";
    private ConsultaDAO consdao= new ConsultaDAO();
    
    public String agregarConsulta(Consulta cons){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=consdao.agregarConsulta(conn, cons);
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
    public Consulta buscarConsultaPorId(int idConsulta){
        Connection conn=Conexion.getConnection();
        Consulta consulta=null;
        try {
            consulta=consdao.buscarConsultaPorId(conn, idConsulta);
        } catch (Exception e) {
            System.out.println("Error al buscar la consulta: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al desconectar: " + e.getMessage());
            }
        }
        return consulta;
    }
    public String modificarConsulta(Consulta cons){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=consdao.modificarConsulta(conn, cons);
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
    
    public String eliminarConsulta(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=consdao.eliminarConsulta(conn, id);
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
    
    public void listarConsulta(JTable tabla){
        Connection conn = Conexion.getConnection();
        consdao.listarConsulta(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=consdao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
