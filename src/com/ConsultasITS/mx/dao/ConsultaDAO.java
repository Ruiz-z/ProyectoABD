
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.entity.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
public class ConsultaDAO {
    private String mensaje="";
    public String agregarConsulta(Connection conn, Consulta cons){
        PreparedStatement pst = null;
        String sql = "INSERT INTO CONSULTA(ID_CONSULTA, FECHA_CONSULTA, HORA_CONSULTA, ID_PERSONA, ID_PACIENTE, ID_PERSONAL_SALUD, ID_USUARIO) "
                + "VALUES(?,?,?,?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, cons.getId_Consulta());
            pst.setDate(2, new java.sql.Date(cons.getFecha_Consulta().getTime()));
            pst.setTimestamp(3, cons.getHora_Consulta());
            pst.setInt(4, cons.getId_Persona());
            pst.setInt(5, cons.getId_Paciente());
            pst.setInt(6, cons.getId_Personal_Salud());
            pst.setInt(7, cons.getId_Usuario());
            mensaje="GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Consulta buscarConsultaPorId(Connection conn, int idConsulta){
        Consulta consulta=null;
        try {
            String sql="SELECT ID_CONSULTA, FECHA_CONSULTA, HORA_CONSULTA, ID_PERSONA, ID_PACIENTE, ID_PERSONAL_SALUD, ID_USUARIO FROM CONSULTA WHERE ID_CONSULTA=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, idConsulta);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                consulta=new Consulta();
                consulta.setId_Consulta(rs.getInt("ID_CONSULTA"));
                consulta.setFecha_Consulta(rs.getDate("FECHA_CONSULTA"));
                consulta.setHora_Consulta(rs.getTimestamp("HORA_CONSULTA"));
                consulta.setId_Persona(rs.getInt("ID_PERSONA"));
                consulta.setId_Paciente(rs.getInt("ID_PACIENTE"));
                consulta.setId_Personal_Salud(rs.getInt("ID_PERSONAL_SALUD"));
                consulta.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error buscando la consulta: " + e.getMessage());
        }
        return consulta;
    }
    public String modificarConsulta(Connection conn, Consulta cons){
         PreparedStatement pst = null;
         String sql = "UPDATE CONSULTA SET FECHA_CONSULTA=?, HORA_CONSULTA=?, ID_PERSONA=?, ID_PACIENTE=?, ID_PERSONAL_SALUD=?, ID_USUARIO=?"
                   + "WHERE ID_CONSULTA=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(cons.getFecha_Consulta().getTime()));
            pst.setTimestamp(2, cons.getHora_Consulta());
            pst.setInt(3, cons.getId_Persona());
            pst.setInt(4, cons.getId_Paciente());
            pst.setInt(5, cons.getId_Personal_Salud());
            pst.setInt(6, cons.getId_Usuario());
            pst.setInt(7, cons.getId_Consulta());
            
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
    
    public String eliminarConsulta(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM CONSULTA WHERE ID_CONSULTA= ?";
        
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
    
    public void listarConsulta(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "FECHA", "HORA", "ID PERSONA", "ID_PACIENTE", "ID_PERSONAL_SALUD", "ID_USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql = "SELECT \n" +
"    ID_CONSULTA, \n" +
"    TO_CHAR(FECHA_CONSULTA, 'DD-MM-YYYY') AS FECHA, \n" +
"    TO_CHAR(HORA_CONSULTA, 'HH24:MI') AS HORA, \n" +
"    ID_PERSONA, \n" +
"    ID_PACIENTE, \n" +
"    ID_PERSONAL_SALUD, \n" + // Agregué la coma aquí
"    ID_USUARIO \n" +
"FROM CONSULTA \n" +
"ORDER BY ID_CONSULTA";

        
        String [] filas = new String[7];
        Statement st= null;
        ResultSet rs=null;
        
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                for (int i=0; i<7; i++) {
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
        String sql = "SELECT MAX(ID_CONSULTA)+1 FROM CONSULTA";
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
