/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.bo;
import com.ConsultasITS.mx.dao.BusquedasDao;
import com.ConsultasITS.mx.db.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;
import java.sql.Date;
/**
 *
 * @author Mauro
 */
public class BusquedasBO {
     private String mensaje="";
    private BusquedasDao busquedasdao=new BusquedasDao();
    
     public void listarPaciente(JTable tabla, int idPaciente){
        Connection conn = Conexion.getConnection();
        busquedasdao.listarRecetasPorPaciente(conn, tabla, idPaciente);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
      public void listarPacientePers(JTable tabla, int idPersonal){
        Connection conn = Conexion.getConnection();
        busquedasdao.listarRecetasPorPersonalSalud(conn, tabla, idPersonal);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void listarxFecha(JTable tabla, Date fechaDesde, Date fechaHasta){
        Connection conn = Conexion.getConnection();
        busquedasdao.listarxFecha(conn, tabla, fechaDesde, fechaHasta);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
          
    public void listarMedicamento(JTable tabla, int id_medicamento){
        Connection conn = Conexion.getConnection();
        busquedasdao.listarMedicamentosRecetas(conn, tabla,id_medicamento);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
