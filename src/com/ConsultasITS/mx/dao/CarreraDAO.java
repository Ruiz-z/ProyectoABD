
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.db.Conexion;
import com.ConsultasITS.mx.entity.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
public class CarreraDAO {
    private String mensaje="";
    
    
    public String agregarCarrera(Connection conn, Carrera car){
        PreparedStatement pst = null;
        String sql = "INSERT INTO CARRERA(ID_CARRERA, NOMBRE_CARRERA, ID_USUARIO) "
                + "VALUES(?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, car.getId_Carrera());
            pst.setString(2, car.getNombre_Carrera());
            pst.setInt(3, car.getId_Usuario());
            
            mensaje="GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Carrera buscarCarreraPorId(Connection conn, int idCarrera) {
    Carrera carrera = null;
    try {
        String sql = "SELECT * FROM CARRERA WHERE ID_CARRERA = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idCarrera);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            carrera = new Carrera();
            carrera.setId_Carrera(rs.getInt("ID_CARRERA"));
            carrera.setNombre_Carrera(rs.getString("NOMBRE_CARRERA"));
            carrera.setId_Usuario(rs.getInt("ID_USUARIO"));
        }
        ps.close();
    } catch (SQLException e) {
        System.out.println("Error buscando la carrera: " + e.getMessage());
    }
    return carrera;
    }

    public String modificarCarrera(Connection conn, Carrera car) {
    PreparedStatement pst = null;
    String sql = "UPDATE CARRERA SET NOMBRE_CARRERA=?, ID_USUARIO=? WHERE ID_CARRERA=?";
    

    try {
        pst = conn.prepareStatement(sql);
        pst.setString(1, car.getNombre_Carrera());
        pst.setInt(2, car.getId_Usuario());
        pst.setInt(3, car.getId_Carrera());

        int filasAfectadas = pst.executeUpdate();
        if (filasAfectadas > 0) {
            mensaje = "ACTUALIZADO CORRECTAMENTE";
        } else {
            mensaje = "NO SE PUDO ACTUALIZAR ID NO ENCONTRADO";
        }

        pst.close();
    } catch (SQLException e) {
        mensaje = "NO SE PUDO ACTUALIZAR CORRECTAMENTE \n " + e.getMessage();
    }
    return mensaje;
}

   


    public String eliminarCarrera(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM CARRERA WHERE ID_CARRERA= ?";
        
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
    
    public void listarCarrera(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "NOMBRE", "USUARIO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT * FROM CARRERA ORDER BY ID_CARRERA";
        String [] filas = new String[3];
        Statement st= null;
        ResultSet rs=null;
        
        try {
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                for (int i=0; i<3; i++) {
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
        String sql = "SELECT MAX(ID_CARRERA)+1 FROM CARRERA";
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
