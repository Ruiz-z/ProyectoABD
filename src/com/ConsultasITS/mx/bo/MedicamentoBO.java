
package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.MedicamentoDAO;
import com.ConsultasITS.mx.entity.Medicamento;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
public class MedicamentoBO {
    private String mensaje="";
    private MedicamentoDAO medicadao= new MedicamentoDAO();
    
    public String agregarMedicamento(Medicamento medica){
         Connection conn = Conexion.getConnection();
         try {
             mensaje=medicadao.agregarMedicamento(conn, medica);
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
    
    public Medicamento buscarMedicamentoPorId(int idMedicamento){
        Connection conn = Conexion.getConnection();
        Medicamento medica = null;
        try {
            medica = medicadao.buscarMedicamentoPorId(conn, idMedicamento);
        } catch (Exception e) {
            System.out.println("ERROR BUSCANDO EL MEDICAMENTO: " +  e.getMessage());
        }finally{
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("ERROR CERRANDO LA CONEXION: " + e.getMessage());
            }
        }
        return medica;
    }
    
    public String modificarMedicamento(Medicamento medica){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=medicadao.modificarMedicamento(conn, medica);
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
    
    public String eliminarMedicamento(int id){
        Connection conn = Conexion.getConnection();
         try {
             mensaje=medicadao.eliminarMedicamento(conn, id);
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
    
    public void listarMedicamento(JTable tabla){
        Connection conn = Conexion.getConnection();
        medicadao.listarMedicamento(conn, tabla);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getMaxID(){
        Connection conn = Conexion.getConnection();
        int id=medicadao.getMaxID(conn);
         try {
           conn.close();
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return id;
    }
}
