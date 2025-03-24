
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.entity.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
public class MedicamentoDAO {
    private String mensaje="";
    public String agregarMedicamento(Connection conn, Medicamento medica){
        PreparedStatement pst = null;
        String sql = "INSERT INTO MEDICAMENTO(ID_MEDICAMENTO, NOMBRE_MEDICAMENTO, PRESENTACION, FECHA_CADUCIDAD, ID_USUARIO) "
                + "VALUES(?,?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, medica.getId_Medicamento());
            pst.setString(2,medica.getNombre_Medicamento());
            pst.setString(3, medica.getPresentacion());
            
            pst.setDate(4, new java.sql.Date(medica.getFecha_Caducidad().getTime()));
            pst.setInt(5, medica.getId_Usuario());
            
            mensaje="GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Medicamento buscarMedicamentoPorId(Connection conn, int idMedicamento){
        Medicamento medica = null;
        try {
            String sql="SELECT * FROM MEDICAMENTO WHERE ID_MEDICAMENTO=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idMedicamento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                medica=new Medicamento();
                medica.setId_Medicamento(rs.getInt("ID_MEDICAMENTO"));
                medica.setNombre_Medicamento(rs.getString("NOMBRE_MEDICAMENTO"));
                medica.setPresentacion(rs.getString("PRESENTACION"));
                medica.setFecha_Caducidad(rs.getDate("FECHA_CADUCIDAD"));
                medica.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR BUSCANDO EL MEDICAMENTO: " + e.getMessage());
        }
        return medica;
    }
    public String modificarMedicamento(Connection conn, Medicamento medica){
         PreparedStatement pst = null;
         String sql = "UPDATE MEDICAMENTO SET NOMBRE_MEDICAMENTO=?, PRESENTACION=?, FECHA_CADUCIDAD=?, ID_USUARIO=?"
                   + "WHERE ID_MEDICAMENTO=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, medica.getNombre_Medicamento());
            pst.setString(2, medica.getPresentacion());
            pst.setDate(3, new java.sql.Date(medica.getFecha_Caducidad().getTime()));
            pst.setInt(4, medica.getId_Usuario());
            pst.setInt(5, medica.getId_Medicamento());
            
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
    
    public String eliminarMedicamento(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM MEDICAMENTO WHERE ID_MEDICAMENTO= ?";
        
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
    
    public void listarMedicamento(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "NOMBRE", "PRESENTACION", "CADUCIDAD", "ID_USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT ID_MEDICAMENTO, NOMBRE_MEDICAMENTO, PRESENTACION, TO_CHAR(FECHA_CADUCIDAD, 'DD-MM-YYYY') AS FECHA, ID_USUARIO FROM MEDICAMENTO ORDER BY ID_MEDICAMENTO";
        String [] filas = new String[5];
        Statement st= null;
        ResultSet rs=null;
        
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                for (int i=0; i<5; i++) {
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
        String sql = "SELECT MAX(ID_MEDICAMENTO)+1 FROM MEDICAMENTO";
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
