
package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.AlumnoDAO;
import com.ConsultasITS.mx.entity.Alumno;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class AlumnoBO {
    private String mensaje="";
    private AlumnoDAO adao= new AlumnoDAO();
    
    public String agregarAlumno(Alumno alum){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=adao.agregarAlumno(conn, alum);
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
    public Alumno buscarAlumnoPorId(int idAlumno){
        Connection conn = Conexion.getConnection();
        Alumno alumno=null;
        try {
            alumno=adao.buscarAlumnoPorId(conn, idAlumno);
        } catch (Exception e) {
            System.out.println("Error al buscar alumno: " + e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }
        }
        return alumno;
    }
    public String modificarAlumno(Alumno alum){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=adao.modificarAlumno(conn, alum);
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
    
    public String eliminarAlumno(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=adao.eliminarAlumno(conn, id);
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
    
    public void listarAlumno(JTable tabla, int idCarrera){
        Connection conn = Conexion.getConnection();
        adao.listarAlumno(conn, tabla, idCarrera);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=adao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
