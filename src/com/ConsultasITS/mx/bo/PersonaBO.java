package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.PersonaDAO;
import com.ConsultasITS.mx.entity.Persona;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class PersonaBO {
    private String mensaje="";
    private PersonaDAO persodao= new PersonaDAO();
    
    public String agregarPersona(Persona perso){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=persodao.agregarPersona(conn, perso);
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
    
    public Persona buscarpersonaPorId(int idPersona){
        Connection conn=Conexion.getConnection();
        Persona persona=null;
        try {
            persona=persodao.buscarPersonaPorId(conn, idPersona);
        } catch (Exception e) {
            System.out.println("Error al buscar Persona: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return persona;
    }
    
    public String modificarPersona(Persona perso){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=persodao.modificarPersona(conn, perso);
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
    
    public String eliminarPersona(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=persodao.eliminarPersona(conn, id);
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
    
    public void listarPersona(JTable tabla){
        Connection conn = Conexion.getConnection();
        persodao.listarPersona(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=persodao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         
         return id;
    }
}
