
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
public class TrabajadorDAO {
    
    private String mensaje="";
    
    public String agregarTrabajador(Connection conn, Trabajador traba){
        PreparedStatement pst = null;
        String sql = "INSERT INTO TRABAJADOR(ID_TRABAJADOR, TIPO_TRABAJADOR, CEDULA, ESTADO_CIVIL, ID_PERSONA, ID_USUARIO) "
                + "VALUES(?,?,?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, traba.getId_Trabajador());
            pst.setString(2, traba.getTipo_Trabajador()+"");
            pst.setString(3, traba.getCedula());
            pst.setString(4, traba.getEstado_Civil()+"");
            pst.setInt(5,traba.getId_Persona());
            pst.setInt(6, traba.getId_Usuario());
            mensaje="TRABAJADOR GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Trabajador buscarTrabajadorPorId(Connection conn, int idTrabajador){
        Trabajador trabajador=null;
        try {
            String sql="SELECT ID_TRABAJADOR, TIPO_TRABAJADOR, CEDULA, ESTADO_CIVIL, ID_PERSONA, ID_USUARIO FROM TRABAJADOR WHERE ID_TRABAJADOR=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTrabajador);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                trabajador=new Trabajador();
                trabajador.setId_Trabajador(rs.getInt("ID_TRABAJADOR"));
                trabajador.setTipo_Trabajador(rs.getString("TIPO_TRABAJADOR").charAt(0));
                trabajador.setCedula(rs.getString("CEDULA"));
                trabajador.setEstado_Civil(rs.getString("ESTADO_CIVIL").charAt(0));
                trabajador.setId_Persona(rs.getInt("ID_PERSONA"));
                trabajador.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al encontrar trabajador: " + e.getMessage());
        }
        return trabajador;
    }
    public String modificarTrabajador(Connection conn, Trabajador traba){
         PreparedStatement pst = null;
         String sql = "UPDATE TRABAJADOR SET TIPO_TRABAJADOR=?, CEDULA=?, ESTADO_CIVIL=?, ID_PERSONA=?, ID_USUARIO=?"
                   + "WHERE ID_TRABAJADOR=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, traba.getTipo_Trabajador()+"");
            pst.setString(2, traba.getCedula());
            pst.setString(3, traba.getEstado_Civil()+"");
            pst.setInt(4, traba.getId_Persona());
            pst.setInt(5, traba.getId_Usuario());
            pst.setInt(6, traba.getId_Trabajador());
            
            int filasAfectadas=pst.executeUpdate();
            if(filasAfectadas>0){
                mensaje="TRABAJADOR ACTUALIZADO CORRECTAMENTE";
            }else{
                mensaje="NO SE PUDO ACTUALIZAR ID NO ENCONTRADO";
            }
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO ACTUALIZAR CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }
    
    public String eliminarTrabajador(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM TRABAJADOR WHERE ID_TRABAJADOR= ?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            mensaje="TRABAJADOR ELIMINADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO ELIMINAR CORRECTAMENTE \n " + e.getMessage();
        }
        return mensaje;
    }
    
    public void listarTrabajador(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "TIPO", "CEDULA", "ESTADO CIVIL", "ID PERSONA", "ID_USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT * FROM TRABAJADOR ORDER BY ID_TRABAJADOR";
        String [] filas = new String[6];
        Statement st= null;
        ResultSet rs=null;
        
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                for (int i=0; i<6; i++) {
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
        String sql = "SELECT MAX(ID_TRABAJADOR)+1 FROM TRABAJADOR";
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
