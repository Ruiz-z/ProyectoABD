
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.entity.Personal_Salud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
public class Personal_SaludDAO {
    private String mensaje="";
    public String agregarPersonal_Salud(Connection conn, Personal_Salud persoS){
        PreparedStatement pst = null;
        String sql = "INSERT INTO PERSONAL_SALUD(ID_PERSONAL_SALUD, NOMRE_PERSONAL_SALUD , PATERNO_PERTSONAL_SALUD, MATERNO_PERSONAL_SALUD,  EDAD_PERSONAL_SALUD, TIPO_PERSONAL_SALUD, ID_USUARIO) "
                + "VALUES(?,?,?,?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, persoS.getId_Personal_Salud());
            pst.setString(2,persoS.getNombre_Personal_Salud());
            pst.setString(3, persoS.getPaterno_Personal());
            pst.setString(4, persoS.getMaterno_Personal());
            pst.setInt(5, persoS.getEdad_Personal_Salud());
            pst.setString(6, persoS.getTipo_Personal_Salud()+"");
            pst.setInt(7, persoS.getId_Usuario());
            
            mensaje="GUARDADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR CORRECTAMENTE \n " + e.getMessage();

        }
        return mensaje;
    }
    public Personal_Salud buscarPersonalPorId(Connection conn, int idPersonal){
        Personal_Salud personal=null;
        try {
            String sql="SELECT ID_PERSONAL_SALUD, NOMRE_PERSONAL_SALUD, PATERNO_PERTSONAL_SALUD, MATERNO_PERSONAL_SALUD,"
                    + " EDAD_PERSONAL_SALUD, TIPO_PERSONAL_SALUD, ID_USUARIO FROM PERSONAL_SALUD WHERE ID_PERSONAL_SALUD=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1, idPersonal);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                personal=new Personal_Salud();
                personal.setId_Personal_Salud(rs.getInt("ID_PERSONAL_SALUD"));
                personal.setNombre_Personal_Salud(rs.getString("NOMRE_PERSONAL_SALUD"));
                personal.setPaterno_Personal(rs.getString("PATERNO_PERTSONAL_SALUD"));
                personal.setMaterno_Personal(rs.getString("MATERNO_PERSONAL_SALUD"));
                personal.setEdad_Personal_Salud(rs.getInt("EDAD_PERSONAL_SALUD"));
                personal.setTipo_Personal_Salud(rs.getString("TIPO_PERSONAL_SALUD"));
                personal.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al encontrar Personal: " + e.getMessage());
        }
        return personal;
    }
    public String modificarPersonal_Salud(Connection conn, Personal_Salud persoS){
         PreparedStatement pst = null;
         String sql = "UPDATE PERSONAL_SALUD SET NOMRE_PERSONAL_SALUD=?, PATERNO_PERTSONAL_SALUD=?, MATERNO_PERSONAL_SALUD=?, EDAD_PERSONAL_SALUD=?, TIPO_PERSONAL_SALUD=?, ID_USUARIO=?"
                   + "WHERE ID_PERSONAL_SALUD =?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, persoS.getNombre_Personal_Salud());
            pst.setString(2, persoS.getPaterno_Personal());
            pst.setString(3, persoS.getMaterno_Personal());
            pst.setInt(4, persoS.getEdad_Personal_Salud());
            pst.setString(5, persoS.getTipo_Personal_Salud()+"");
            pst.setInt(6, persoS.getId_Usuario());
            pst.setInt(7, persoS.getId_Personal_Salud());
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
    
   public String eliminarPersonal_Salud(Connection conn, int id) {
    PreparedStatement pst = null;
    String sql = "DELETE FROM PERSONAL_SALUD WHERE ID_PERSONAL_SALUD = ?";
    int affectedRows = 0;

    try {
        pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        affectedRows = pst.executeUpdate(); // Ejecuta la eliminación y retorna el número de filas afectadas
        pst.close();

        if (affectedRows > 0) {
            mensaje = "ELIMINADO CORRECTAMENTE";
        } else {
            mensaje = "NO HAY REGISTRO AGREGADO PARA ELIMINAR";
        }
    } catch (SQLException e) {
        mensaje = "NO SE PUDO ELIMINAR CORRECTAMENTE \n " + e.getMessage();
    }
    return mensaje;
}

    
    public void listarPersonal_Salud(Connection conn, JTable tabla){
        DefaultTableModel model;
        String [] columnas={"ID", "NOMBRE", "PATERNO", "MATERNO","TIPO"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT ID_PERSONAL_SALUD, NOMRE_PERSONAL_SALUD, PATERNO_PERTSONAL_SALUD, MATERNO_PERSONAL_SALUD,  TIPO_PERSONAL_SALUD FROM PERSONAL_SALUD ORDER BY ID_PERSONAL_SALUD";
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
        String sql = "SELECT MAX(ID_PERSONAL_SALUD)+1 FROM PERSONAL_SALUD";
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
