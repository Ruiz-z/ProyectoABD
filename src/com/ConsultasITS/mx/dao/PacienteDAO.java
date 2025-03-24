
package com.ConsultasITS.mx.dao;

import com.ConsultasITS.mx.entity.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
public class PacienteDAO {
    private String mensaje="";
    public String agregarPaciente(Connection conn, Paciente pacie){
        PreparedStatement pst=null;
        String sql="INSERT INTO PACIENTE(ID_PACIENTE, PESO_PACIENTE, ESTATURA_PACIENTE, ID_PERSONA, ID_USUARIO) "
                + "VALUES(?,?,?,?,?)";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, pacie.getId_Paciente());
            pst.setInt(2, pacie.getPeso_paciente());
            pst.setInt(3, pacie.getEstatura_Paciente());
            pst.setInt(4, pacie.getId_Persona());
            pst.setInt(5, pacie.getId_Usuario());
            mensaje="GUARDADO CORRECTAMENTE EN LA BASE DE DATOS";
            pst.execute();
            pst.close();
        }catch(SQLException e){
            mensaje="NO SE PUEDE GUARDAR CORRECTAMENTE" + e.getMessage();
        }
        return mensaje;
    }
    public Paciente buscarPacientePorId(Connection conn, int idPaciente){
        Paciente paciente=null;
        try {
            String sql="SELECT ID_PACIENTE, PESO_PACIENTE, ESTATURA_PACIENTE, ID_PERSONA, ID_USUARIO FROM PACIENTE WHERE ID_PACIENTE=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                paciente=new Paciente();
                paciente.setId_Paciente(rs.getInt("ID_PACIENTE"));
                paciente.setPeso_paciente(rs.getInt("PESO_PACIENTE"));
                paciente.setEstatura_Paciente(rs.getInt("ESTATURA_PACIENTE"));
                paciente.setId_Persona(rs.getInt("ID_PERSONA"));
                paciente.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al encontrar: " + e.getMessage());
        }
        return paciente;
    }
    public String modificarPaciente(Connection conn, Paciente pacie){
        PreparedStatement pst = null;
        String sql="UPDATE PACIENTE SET PESO_PACIENTE=?, ESTATURA_PACIENTE=?, ID_PERSONA=?, ID_USUARIO=?"
                + " WHERE ID_PACIENTE=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1, pacie.getPeso_paciente());
            pst.setInt(2, pacie.getEstatura_Paciente());
            pst.setInt(3, pacie.getId_Persona());
            pst.setInt(4, pacie.getId_Usuario());
            pst.setInt(5, pacie.getId_Paciente());

            int filasAfectadas=pst.executeUpdate();
            if(filasAfectadas>0){
                mensaje="ACTUALIZADO CORRECTAMENTE";
            }else{
                mensaje="NO SE PUDO ACTUALIZAR ID NO ENCONTRADO";
            }
            pst.close();
        }catch(SQLException e){
            mensaje="NO SE PUEDE ACTUALIZAR" + e.getMessage();
        }
        return mensaje;
    }
    public String eliminarPaciente(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE= ?";
        
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
    public void listarPaciente(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID_PACIENTE", "PESO_PACIENTE", "ESTATURA_PACIENTE", "ID_PERSONA", "ID_USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT * FROM PACIENTE ORDER BY ID_PACIENTE";
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
        String sql = "SELECT MAX(ID_PACIENTE)+1 FROM PACIENTE";
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
