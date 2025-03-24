
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.entity.Medicamento_Has_Receta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
public class Medicamento_Has_RecetaDAO {
     private String mensaje="";
    public String agergarMedicamento_has_Receta(Connection conn, Medicamento_Has_Receta mhr){
        PreparedStatement pst = null;
        String sql = "INSERT INTO MEDICAMENTO_HAS_RECETA(ID_MEDICAMENTO, ID_CONSULTA, ID_RECETA, ID_USUARIO) "
                + "VALUES(?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, mhr.getId_Medicamento());
            pst.setInt(2,mhr.getId_Consulta());
            pst.setInt(3, mhr.getId_Receta());
            pst.setInt(4, mhr.getId_Usuario());
            mensaje="GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Medicamento_Has_Receta buscarM_has_RPorId(Connection conn, int idMedicamento){
        Medicamento_Has_Receta mhr=null;
        try {
            String sql="SELECT * FROM MEDICAMENTO_HAS_RECETA WHERE ID_MEDICAMENTO=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, idMedicamento);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                mhr=new Medicamento_Has_Receta();
                mhr.setId_Medicamento(rs.getInt("ID_MEDICAMENTO"));
                mhr.setId_Consulta(rs.getInt("ID_CONSULTA"));
                mhr.setId_Receta(rs.getInt("ID_RECETA"));
                mhr.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al encontrar: " + e.getMessage());
        }
        return mhr;
    }
    public String modificarMedicamento_has_Receta(Connection conn, Medicamento_Has_Receta mhr){
         PreparedStatement pst = null;
         String sql = "UPDATE MEDICAMENTO_HAS_RECETA SET ID_CONSULTA=?, ID_RECETA=?, ID_USUARIO=?"
                   + "WHERE ID_MEDICAMENTO=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, mhr.getId_Consulta());
            pst.setInt(2, mhr.getId_Receta());
            pst.setInt(3, mhr.getId_Usuario());
            pst.setInt(4, mhr.getId_Medicamento());
            
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
    
    public String eliminarMedicamento_has_Receta(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM MEDICAMENTO_HAS_RECETA WHERE ID_MEDICAMENTO= ?";
        
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
    
    public void listarMedicamento_has_Receta(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID_MEDICAMENTO", "ID_CONSULTA", "ID_RECETA", "ID USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT * FROM MEDICAMENTO_HAS_RECETA ORDER BY ID_MEDICAMENTO";
        String [] filas = new String[4];
        Statement st= null;
        ResultSet rs=null;
        
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                for (int i=0; i<4; i++) {
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
        String sql = "SELECT MAX(ID_MEDICAMENTO)+1 FROM MEDICAMENTO_HAS_RECETA";
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
