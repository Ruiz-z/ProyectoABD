
package com.ConsultasITS.mx.entity;
import java.sql.Date;
public class Medicamento {
    private int id_Medicamento;
    private String nombre_Medicamento;
    private String presentacion;
    private Date fecha_Caducidad;
    private int id_Usuario;
    
    public Medicamento(){
        
    }

    public Medicamento(int id_Medicamento, String nombre_Medicamento, String presentacion, Date fecha_Caducidad, int id_Usuario) {
        this.id_Medicamento = id_Medicamento;
        this.nombre_Medicamento = nombre_Medicamento;
        this.presentacion = presentacion;
        this.fecha_Caducidad = fecha_Caducidad;
        this.id_Usuario=id_Usuario;
    }

    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public int getId_Usuario(){
        return id_Usuario;
    }
    public void setId_Medicamento(int id_Medicamento) {
        this.id_Medicamento = id_Medicamento;
    }

    public void setNombre_Medicamento(String nombre_Medicamento) {
        this.nombre_Medicamento = nombre_Medicamento;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public void setFecha_Caducidad(Date fecha_Caducidad) {
        this.fecha_Caducidad = fecha_Caducidad;
    }

    public int getId_Medicamento() {
        return id_Medicamento;
    }

    public String getNombre_Medicamento() {
        return nombre_Medicamento;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public Date getFecha_Caducidad() {
        return fecha_Caducidad;
    }

    
    public String toString() {
        return nombre_Medicamento;
    }
}
