
package com.ConsultasITS.mx.dao;
import com.ConsultasITS.mx.entity.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AlumnoDAO {
    private String mensaje="";
    public String agregarAlumno(Connection conn, Alumno alum){
        PreparedStatement pst = null;
        String sql = "INSERT INTO ALUMNO(ID_ALUMNO, SEMESTRE, ID_PERSONA, ID_CARRERA, ID_USUARIO) "
                + "VALUES(?,?,?,?,?)";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, alum.getId_Alumno());
            pst.setInt(2,alum.getSemestre());
            pst.setInt(3, alum.getId_Persona());
            pst.setInt(4, alum.getId_Carrera());
            pst.setInt(5, alum.getId_Usuario());
            System.out.println("ID:" + alum.getId_Alumno());
            System.out.println("id carrera:" + alum.getId_Persona());
            System.out.println("ID persona:" + alum.getId_Persona());
            mensaje="ALUMNO GUARDADO CORRECTAMENTE";
            pst.executeQuery();
            conn.commit();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO GUARDAR EL ALUMNO CORRECTAMENTE \n ";

        }
        return mensaje;
    }
    
    public Alumno buscarAlumnoPorId(Connection conn, int idAlumno){
        Alumno alumno=null;
        try {
            String sql="SELECT ID_ALUMNO, SEMESTRE, ID_PERSONA, ID_CARRERA, ID_USUARIO  FROM ALUMNO WHERE ID_ALUMNO=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                alumno=new Alumno();
                alumno.setId_Alumno(rs.getInt("ID_ALUMNO"));
                alumno.setSemestre(rs.getInt("SEMESTRE"));
                alumno.setId_Persona(rs.getInt("ID_PERSONA"));
                alumno.setId_Carrera(rs.getInt("ID_CARRERA"));
                alumno.setId_Usuario(rs.getInt("ID_USUARIO"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR EL ALUMNO ");
        }
        return alumno;
    }
    
    public String modificarAlumno(Connection conn, Alumno alum){
         PreparedStatement pst = null;
         String sql = "UPDATE ALUMNO SET SEMESTRE=?, ID_PERSONA=?, ID_CARRERA=?, ID_USUARIO=?"
                   + "WHERE ID_ALUMNO=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, alum.getSemestre());
            pst.setInt(2, alum.getId_Persona());
            pst.setInt(3, alum.getId_Carrera());
            pst.setInt(4, alum.getId_Usuario());
            pst.setInt(5, alum.getId_Alumno());
            
            int filasAfectadas=pst.executeUpdate();
            if(filasAfectadas>0){
                mensaje="ALUMNO ACTUALIZADO CORRECTAMENTE";
            }else{
                mensaje="NO SE PUDO ACTUALIZAR EL ALUMNO, ID NO ENCONTRADO";
            }
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO ACTUALIZAR EL ALUMNO CORRECTAMENTE \n ";
        }
        return mensaje;
    }
    
    public String eliminarAlumno(Connection conn, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM ALUMNO WHERE ID_ALUMNO= ?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            mensaje="ALUMNO ELIMINADO CORRECTAMENTE";
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            mensaje="NO SE PUDO ELIMINAR EL ALUMNO CORRECTAMENTE \n ";
        }
        return mensaje;
    }
    
    public void listarAlumno(Connection conn, JTable tabla, int idCarrera){
        DefaultTableModel model;
        String [] columnas={"ID", "NOMBRE", "PATERNO", "MATERNO", "SEMESTRE"};
        model=new DefaultTableModel(null, columnas);
        
        String sql="SELECT A.ID_ALUMNO, P.NOMBRE_PERSONA, P.PATERNO_PERSONA, P.MATERNO_PERSONA, A.SEMESTRE FROM ALUMNO A, PERSONA P WHERE A.ID_PERSONA=P.ID_PERSONA AND A.ID_CARRERA=?";
        String [] filas = new String[5];
        PreparedStatement pst=null;
        Statement st= null;
        ResultSet rs=null;
        
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1, idCarrera);
            rs=pst.executeQuery();
            while(rs.next()){
                for (int i=0; i<5; i++) {
                    filas[i]=rs.getString(i+1);
                }
                model.addRow(filas);
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"NO SE PUEDE MOSTRAR LA TABLA DE ALUMNO");
        }
    }
    
     public int getMaxID(Connection conn){
        int id=0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT MAX(ID_ALUMNO)+1 FROM ALUMNO";
        try {
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL MOSTRAR MENSAJE....");
        }
        return id;
    }
}
