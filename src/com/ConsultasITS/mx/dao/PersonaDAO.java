
package com.ConsultasITS.mx.dao;

import com.ConsultasITS.mx.entity.Persona;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
public class PersonaDAO {
    private String mensaje="";
    public String agregarPersona(Connection conn, Persona perso){
    PreparedStatement pst = null;
    String sql = "INSERT INTO PERSONA(ID_PERSONA, NOMBRE_PERSONA, PATERNO_PERSONA, MATERNO_PERSONA, EDAD, FECHA_NACIMIENTO, GENERO_PERSONA, ID_USUARIO)"
                + " VALUES(?,?,?,?,?,?,?,?)";
    try {
        pst = conn.prepareStatement(sql);
        pst.setInt(1, perso.getId_Persona());
        pst.setString(2, perso.getNombre_Persona());
        pst.setString(3, perso.getPaterno_Persona());
        pst.setString(4, perso.getMaterno_Persona());
        pst.setInt(5, perso.getEdad_Persona());
        // Conversión correcta de java.util.Date a java.sql.Date
        pst.setDate(6, new java.sql.Date(perso.getFecha_nacimiento().getTime()));
        pst.setString(7, String.valueOf(perso.getGenero_Persona())); // Convertimos el char a String
        pst.setInt(8, perso.getId_Usuario());
        mensaje = "PERSONA GUARDADA CORRECTAMENTE";
        pst.execute();
        pst.close();
    } catch (SQLException e) {
        mensaje = "NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();
    }   
    return mensaje;
}

    public Persona buscarPersonaPorId(Connection conn, int idPersona){
        Persona persona = null;
        try{
            String sql="SELECT * FROM PERSONA WHERE ID_PERSONA=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, idPersona);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                persona=new Persona();
                persona.setId_Persona(rs.getInt("ID_PERSONA"));
                persona.setNombre_Persona(rs.getString("NOMBRE_PERSONA"));
                persona.setPaterno_Persona(rs.getString("PATERNO_PERSONA"));
                persona.setMaterno_Persona(rs.getString("MATERNO_PERSONA"));
                persona.setEdad_Persona(rs.getInt("EDAD"));
                persona.setFecha_nacimiento(rs.getDate("FECHA_NACIMIENTO"));
                persona.setGenero_Persona(rs.getString("GENERO_PERSONA").charAt(0));
                persona.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        }catch(SQLException e){
            System.out.println("Error buscando la persona: " + e.getMessage());
        }
        return persona;
    }
    
    public String modificarPersona(Connection conn, Persona perso){
        PreparedStatement pst = null;
        String sql="UPDATE PERSONA SET NOMBRE_PERSONA=?, PATERNO_PERSONA=?, MATERNO_PERSONA=?, EDAD=?, FECHA_NACIMIENTO=?, GENERO_PERSONA=?, ID_USUARIO=?"
                + "WHERE ID_PERSONA=?";
        
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, perso.getNombre_Persona());
            pst.setString(2, perso.getPaterno_Persona());
            pst.setString(3, perso.getMaterno_Persona());
            pst.setInt(4, perso.getEdad_Persona());
            pst.setDate(5, new java.sql.Date(perso.getFecha_nacimiento().getTime()));
            pst.setString(6, perso.getGenero_Persona()+"");
            pst.setInt(7, perso.getId_Usuario());
            pst.setInt(8, perso.getId_Persona());
            
            int filasAfectadas=pst.executeUpdate();
            if(filasAfectadas>0){
                mensaje="PERSONA ACTUALIZADA CORRECTAMENTE";
            }else{
                mensaje="NO SE PUDO ACTUALIZAR ID NO ENCONTRADO";
            }
            pst.close();
        }catch(SQLException e){
            mensaje="NO SE PUDO MODIFICAR CORRECTAMENTE \n" + e.getMessage();
        }
        return mensaje;
    }
    
    public String eliminarPersona(Connection conn, int id){
        PreparedStatement pst = null;
        String sql="DELETE FROM PERSONA WHERE ID_PERSONA=?";
        try{
            pst=conn.prepareStatement(sql);
            pst.setInt(1, id);
            mensaje="PERSONA ELIMINADA CORRECTAMENTE";
            pst.execute();
            pst.close();
        }catch(SQLException e){
            mensaje="NO SE PUDO ELIMINAR LA PERSONA \n" + e.getMessage();
        }
        return mensaje;
    }
    public void listarPersona(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "NOMBRE", "PATERNO","MATERNO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT T.ID_TRABAJADOR, P.NOMBRE_PERSONA, P.PATERNO_PERSONA, P.MATERNO_PERSONA FROM TRABAJADOR T, PERSONA P WHERE T.ID_PERSONA=P.ID_PERSONA ORDER BY T.ID_PERSONA";
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
            JOptionPane.showMessageDialog(null,"NO SE PUEDE MOSTRAR LA TABLA" + e.getMessage());
        }
    }
    
     public int getMaxID(Connection conn){
        int id=0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(ID_PERSONA)+1 FROM PERSONA";
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
