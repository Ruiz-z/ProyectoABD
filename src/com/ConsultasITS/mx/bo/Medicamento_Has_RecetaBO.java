
package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.Medicamento_Has_RecetaDAO;
import com.ConsultasITS.mx.entity.Medicamento_Has_Receta;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class Medicamento_Has_RecetaBO {
     private String mensaje="";
    private Medicamento_Has_RecetaDAO mhrdao= new Medicamento_Has_RecetaDAO();
    
    public String agregarMedicamento_has_Receta(Medicamento_Has_Receta mhr){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=mhrdao.agergarMedicamento_has_Receta(conn, mhr);
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
    
    public String modificarMedicamento_has_receta(Medicamento_Has_Receta mhr){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=mhrdao.modificarMedicamento_has_Receta(conn, mhr);
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
    public Medicamento_Has_Receta buscarM_has_RPorId(int idMedicamento){
        Medicamento_Has_Receta mhr=null;
        Connection conn=Conexion.getConnection();
        try {
            mhr=mhrdao.buscarM_has_RPorId(conn, idMedicamento);
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
        return mhr;
    }
    public String eliminarMedicamento_has_Receta(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=mhrdao.eliminarMedicamento_has_Receta(conn, id);
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
    
    public void listarMedicamento_has_Receta(JTable tabla){
        Connection conn = Conexion.getConnection();
        mhrdao.listarMedicamento_has_Receta(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=mhrdao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
