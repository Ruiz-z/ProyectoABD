
package com.ConsultasITS.mx.entity;
import java.sql.Date;
public class Receta {
    private int id_Receta;
    private Date fecha_Receta;
    private String dosis;
    private String frecuencia;
    private int duracion;
    private char via_Administracion;
    private int id_Consulta;
    private String diagnostico;
    private String acciones;
    private int id_Usuario;
    
    public Receta(){
        
    }

    public Receta(int id_Receta, Date fecha_Receta, String dosis, String frecuencia, int duracion, char via_Admnistracion, int id_Consulta, String diagnostico, String acciones, int id_Usuario) {
        this.id_Receta = id_Receta;
        this.fecha_Receta = fecha_Receta;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.duracion=duracion;
        this.via_Administracion=via_Admnistracion;
        this.id_Consulta = id_Consulta;
        this.diagnostico=diagnostico;
        this.acciones=acciones;
        this.id_Usuario=id_Usuario;
    }
    public int getId_Usuario(){
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public String getDiagnostico(){
        return diagnostico;
    }
    public String getAcciones(){
        return acciones;
    }
    public void setDiagnostico(String diagnostico){
        this.diagnostico=diagnostico;
        
    }
    public void setAcciones(String acciones){
        this.acciones=acciones;
    }
    public int getDuracion(){
        return duracion;
    }
    public char getVia_Admnistracion(){
        return via_Administracion;
    }
    public int getId_Receta() {
        return id_Receta;
    }

    public Date getFecha_Receta() {
        return fecha_Receta;
    }

    public String getDosis() {
        return dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public int getId_Consulta() {
        return id_Consulta;
    }

    public void setId_Receta(int id_Receta) {
        this.id_Receta = id_Receta;
    }

    public void setFecha_Receta(Date fecha_Receta) {
        this.fecha_Receta = fecha_Receta;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setDuracion(int duracion){
        this.duracion=duracion;
    }
    public void setVia_Administracion(char via_Administracion){
        this.via_Administracion=via_Administracion;
    }
    public void setId_Consulta(int id_Consulta) {
        this.id_Consulta = id_Consulta;
    }
    public String toString() {
        return String.valueOf(id_Receta);
    }
}
