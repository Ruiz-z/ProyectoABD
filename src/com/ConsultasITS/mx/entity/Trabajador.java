
package com.ConsultasITS.mx.entity;
public class Trabajador {
 private int id_Trabajador;
 private char tipo_Trabajador;
 private String cedula;
 private char estado_Civil;
 private int id_Persona;
 private int id_usuario;
 public Trabajador(){
     
 }

    public Trabajador(int id_Trabajador, char tipo_Trabajador, String cedula, char estado_Civil, int id_Persona, int id_Usuario) {
        this.id_Trabajador = id_Trabajador;
        this.tipo_Trabajador = tipo_Trabajador;
        this.cedula = cedula;
        this.estado_Civil = estado_Civil;
        this.id_Persona = id_Persona;
        this.id_usuario=id_Usuario;
    }

    public int getId_Usuario(){
        return id_usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_usuario=id_Usuario;
    }
    public int getId_Trabajador() {
        return id_Trabajador;
    }

    public char getTipo_Trabajador() {
        return tipo_Trabajador;
    }

    public String getCedula() {
        return cedula;
    }

    public char getEstado_Civil() {
        return estado_Civil;
    }

    public int getId_Persona() {
        return id_Persona;
    }

    public void setId_Trabajador(int id_Trabajador) {
        this.id_Trabajador = id_Trabajador;
    }

    public void setTipo_Trabajador(char tipo_Trabajador) {
        this.tipo_Trabajador = tipo_Trabajador;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setEstado_Civil(char estado_Civil) {
        this.estado_Civil = estado_Civil;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

   
    public String toString(){
        return "ID TRABAJADOR: " + id_Trabajador +
                "\nID PERSONA: " + id_Persona +
                "\nID TIPO TRABAJADOR: "  ;
    }
}
