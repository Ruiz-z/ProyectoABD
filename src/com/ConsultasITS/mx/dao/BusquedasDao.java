/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
/**
 *
 * @author Mauro
 */
public class BusquedasDao {
public void listarRecetasPorPaciente(Connection conn, JTable tabla, int idPaciente) {
    DefaultTableModel model;
    String[] columnas = {"ID_RECETA", "FECHA RECETA", "NOMBRE_MEDICAMENTO"};
    model = new DefaultTableModel(null, columnas);
    String sql = "SELECT R.ID_RECETA, TO_CHAR(R.FECHA_RECETA, 'DD-MM-YYYY') AS FECHA_RECETA, M.NOMBRE_MEDICAMENTO " +
                 "FROM RECETA R " +
                 "JOIN MEDICAMENTO_HAS_RECETA MHR ON R.ID_RECETA = MHR.ID_RECETA AND R.ID_CONSULTA = MHR.ID_CONSULTA " +
                 "JOIN MEDICAMENTO M ON MHR.ID_MEDICAMENTO = M.ID_MEDICAMENTO " +
                 "JOIN CONSULTA C ON R.ID_CONSULTA = C.ID_CONSULTA " +
                 "WHERE C.ID_PACIENTE = ?";
    String[] filas = new String[3];
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        pst = conn.prepareStatement(sql);
        pst.setInt(1, idPaciente);
        rs = pst.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < 3; i++) {
                filas[i] = rs.getString(i + 1);
            }
            model.addRow(filas);
        }
        tabla.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "NO SE PUEDE MOSTRAR LA TABLA");
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public void listarRecetasPorPersonalSalud(Connection conn, JTable tabla, int idPersonalSalud) {
    DefaultTableModel model;
    String[] columnas = {"ID_RECETA", "FECHA RECETA", "NOMBRE_PACIENTE"};
    model = new DefaultTableModel(null, columnas);
    String sql = "SELECT R.ID_RECETA, TO_CHAR(R.FECHA_RECETA, 'DD-MM-YYYY') AS FECHA_RECETA, " +
                 "PE.NOMBRE_PERSONA || ' ' || PE.PATERNO_PERSONA AS NOMBRE_PACIENTE " +
                 "FROM RECETA R " +
                 "JOIN CONSULTA C ON R.ID_CONSULTA = C.ID_CONSULTA " +
                 "JOIN PACIENTE P ON C.ID_PACIENTE = P.ID_PACIENTE " +
                 "JOIN PERSONA PE ON P.ID_PERSONA = PE.ID_PERSONA " +
                 "WHERE C.ID_PERSONAL_SALUD = ?";
    String[] filas = new String[3];
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        pst = conn.prepareStatement(sql);
        pst.setInt(1, idPersonalSalud);
        rs = pst.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < 3; i++) {
                filas[i] = rs.getString(i + 1);
            }
            model.addRow(filas);
        }
        tabla.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "NO SE PUEDE MOSTRAR LA TABLA");
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public void listarxFecha(Connection conn, JTable tabla, Date fechaDesde, Date fechaHasta) {
    DefaultTableModel model;
    String[] columnas = {"ID_RECETA", "FECHA RECETA", "NOMBRE_PACIENTE", "NOMBRE_PERSONAL_SALUD"};
    model = new DefaultTableModel(null, columnas);
    
    // Consulta SQL con las fechas como parámetros
    String sql = "SELECT R.ID_RECETA, TO_CHAR(R.FECHA_RECETA, 'DD-MM-YYYY') AS FECHA_RECETA, " +
                 "PE.NOMBRE_PERSONA || ' ' || PE.PATERNO_PERSONA AS NOMBRE_PACIENTE, " +
                 "PS.NOMRE_PERSONAL_SALUD || ' ' || PS.PATERNO_PERTSONAL_SALUD AS NOMBRE_PERSONAL_SALUD " +
                 "FROM RECETA R " +
                 "JOIN CONSULTA C ON R.ID_CONSULTA = C.ID_CONSULTA " +
                 "JOIN PACIENTE P ON C.ID_PACIENTE = P.ID_PACIENTE " +
                 "JOIN PERSONA PE ON P.ID_PERSONA = PE.ID_PERSONA " +
                 "JOIN PERSONAL_SALUD PS ON C.ID_PERSONAL_SALUD = PS.ID_PERSONAL_SALUD " +
                 "WHERE R.FECHA_RECETA BETWEEN ? AND ?";
    
    String[] filas = new String[4];
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        pst = conn.prepareStatement(sql);
        
        // Convertir fechas a java.sql.Date
        pst.setDate(1, new java.sql.Date(fechaDesde.getTime())); // Fecha desde
        pst.setDate(2, new java.sql.Date(fechaHasta.getTime())); // Fecha hasta
        rs = pst.executeQuery();

        // Verificación para ver si el ResultSet tiene filas
        if (!rs.isBeforeFirst()) {
            System.out.println("No hay registros para el rango de fechas proporcionado.");
        } 

        while (rs.next()) {
            filas[0] = rs.getString("ID_RECETA");
            filas[1] = rs.getString("FECHA_RECETA");
            filas[2] = rs.getString("NOMBRE_PACIENTE");
            filas[3] = rs.getString("NOMBRE_PERSONAL_SALUD");
            model.addRow(filas);
        }

        tabla.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "NO SE PUEDE MOSTRAR LA TABLA: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public void listarMedicamentosRecetas(Connection conn, JTable tabla, int idMedicamento) {
    DefaultTableModel model;
    String[] columnas = {"ID_MEDICAMENTO", "NOMBRE MEDICAMENTO", "ID_RECETA"};
    model = new DefaultTableModel(null, columnas);
    String sql = "SELECT MHR.ID_MEDICAMENTO, M.NOMBRE_MEDICAMENTO, MHR.ID_RECETA " +
                 "FROM MEDICAMENTO_HAS_RECETA MHR " +
                 "JOIN MEDICAMENTO M ON MHR.ID_MEDICAMENTO = M.ID_MEDICAMENTO " +
                 "WHERE MHR.ID_MEDICAMENTO = ?";
    
    String[] filas = new String[3];
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        pst = conn.prepareStatement(sql);
        pst.setInt(1, idMedicamento);
        rs = pst.executeQuery();

        while (rs.next()) {
            filas[0] = rs.getString("ID_MEDICAMENTO");
            filas[1] = rs.getString("NOMBRE_MEDICAMENTO");
            filas[2] = rs.getString("ID_RECETA");
            model.addRow(filas);
        }

        tabla.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "NO SE PUEDE MOSTRAR LA TABLA: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




    }

