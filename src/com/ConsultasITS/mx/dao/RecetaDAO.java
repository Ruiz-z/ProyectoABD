package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.entity.Receta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;

public class RecetaDAO {
    private String mensaje="";
    public String agregarReceta(Connection conn, Receta rece){
        PreparedStatement pst = null;
        String sql = "INSERT INTO RECETA(ID_RECETA, FECHA_RECETA, DOSIS, FRECUENCIA, DURACION, VIA_ADMINISTRACION, ID_CONSULTA, DIAGNOSTICO, ACCIONES, ID_USUARIO)"
                + "VALUES(?,?,?,?,?,?,?,?,?, ?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, rece.getId_Receta());
            pst.setDate(2, new java.sql.Date(rece.getFecha_Receta().getTime()));
            pst.setString(3, rece.getDosis());
            pst.setString(4, rece.getFrecuencia());
            pst.setInt(5, rece.getDuracion());
            pst.setString(6, rece.getVia_Admnistracion()+"");
            pst.setInt(7, rece.getId_Consulta());
            pst.setString(8, rece.getDiagnostico());
            pst.setString(9, rece.getAcciones());
            pst.setInt(10, rece.getId_Usuario());
            
            mensaje="GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Receta buscarRecetaPorId(Connection conn, int idReceta){
        Receta receta=null;
        try {
            String sql="SELECT * FROM RECETA WHERE ID_RECETA=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, idReceta);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                receta=new Receta();
                receta.setId_Consulta(rs.getInt("ID_RECETA"));
                receta.setFecha_Receta(rs.getDate("FECHA_RECETA"));
                receta.setDosis(rs.getString("DOSIS"));
                receta.setFrecuencia(rs.getString("FRECUENCIA"));
                receta.setDuracion(rs.getInt("DURACION"));
                receta.setVia_Administracion(rs.getString("VIA_ADMINISTRACION").charAt(0));
                receta.setId_Consulta(rs.getInt("ID_CONSULTA"));
                receta.setDiagnostico(rs.getString("DIAGNOSTICO"));
                receta.setAcciones(rs.getString("ACCIONES"));
                receta.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al encontrar Receta: " + e.getMessage());
        }
        return receta;
    }
    public String modificarReceta(Connection conn, Receta rece){
         PreparedStatement pst = null;
         String sql = "UPDATE RECETA SET FECHA_RECETA=?, DOSIS=?, FRECUENCIA=?, DURACION=?, VIA_ADMINISTRACION=?, ID_CONSULTA=?, DIAGNOSTICO=?, ACCIONES=?, ID_USUARIO=?"
                   + "WHERE ID_RECETA=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(rece.getFecha_Receta().getTime()));
            pst.setString(2, rece.getDosis());
            pst.setString(3, rece.getFrecuencia());
            pst.setInt(4, rece.getDuracion());
            pst.setString(5, rece.getVia_Admnistracion()+"");
            pst.setInt(6, rece.getId_Consulta());           
            pst.setString(7, rece.getDiagnostico());
            pst.setString(8, rece.getAcciones());
            pst.setInt(9, rece.getId_Usuario());
            pst.setInt(10, rece.getId_Receta());
            int filasAfectadas=pst.executeUpdate();
            if(filasAfectadas>0){
                mensaje="ACTUALIZADO CORRECTAMENTE";
            }else{
                mensaje="NO SE PUDO ACTUALIZAR ID NO ENCONTRADO";
            }
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO ACTUALIZAR CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }
    
    public String eliminarReceta(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM RECETA WHERE ID_RECETA= ?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            mensaje="ELIMINADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO ELIMINAR CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }
    
    public void listarReceta(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "FECHA", "DOSIS", "FRECUENCIA", "DURACION", "VIA_ADMINISTRACION","DIAGNOSTICO", "ACCIONES", "ID_CONSULTA", "ID USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT ID_RECETA, TO_CHAR(FECHA_RECETA, 'DD-MM-YYYY') AS FECHA, DOSIS, FRECUENCIA, DURACION, VIA_ADMINISTRACION, DIAGNOSTICO, ACCIONES, ID_CONSULTA, ID_USUARIO FROM RECETA ORDER BY ID_RECETA";
        String [] filas = new String[10];
        Statement st= null;
        ResultSet rs=null;
        
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                for (int i=0; i<10; i++) {
                    filas[i]=rs.getString(i+1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"NO SE PUEDE MOSTRAR LA TABLA");
        }
    }
    
     public int getMaxID(Connection conn){
        int id=0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(ID_RECETA)+1 FROM RECETA";
        try {
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL MOSTRAR MENSAJE...." + e.getMessage());
        }
        return id;
    }
}
