package com.ConsultasITS.mx.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Conexion {
    private static Connection conn = null;
    private static  String login = "CONSULTAS";
    private static  String clave = "CONSULTAS";
    private static  String url = "jdbc:oracle:thin:@localhost:1521:xe";
    
    public static Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection(url,login,clave);
            conn.setAutoCommit(false);
            if(conn!=null){
                System.out.println("CONEXION A LA BASE DE DATOS EXITOSA");
            }else{
                System.out.println("CONEXION A LA BASE DE DATOS ERRONEA");
            }
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "CONEXION ERRONEA A LA BASE DE DATOS" + e.getMessage());
        }
        return conn;
    }
    
    public static void desconexion(){
        try{
            conn.close();
        }catch(Exception e){
            System.out.println("ERROR AL DESCONECTAR" + e.getMessage());
        }
    }
    
    public static void main(String[]args){
        Conexion c = new Conexion();
        c.getConnection();
    }
}
