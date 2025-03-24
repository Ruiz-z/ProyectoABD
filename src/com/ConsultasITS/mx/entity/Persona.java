package com.ConsultasITS.mx.entity;

import java.sql.Date;

public class Persona{
    private int id_Persona;
    private String nombre_Persona;
    private String paterno_Persona;
    private String materno_Persona;
    private int edad_Persona;
    private Date fecha_nacimiento;
    private char genero_Persona;
    private int id_Usuario;
    public Persona(){
        
    }

    public Persona(int id_Persona, String nombre_Persona, String paterno_Persona, String materno_Persona, int edad_Persona, Date fecha_nacimiento, char genero_Persona, int id_Usuario) {
        this.id_Persona = id_Persona;
        this.nombre_Persona = nombre_Persona;
        this.paterno_Persona = paterno_Persona;
        this.materno_Persona = materno_Persona;
        this.edad_Persona = edad_Persona;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero_Persona = genero_Persona;
        this.id_Usuario=id_Usuario;
    }

    public int getId_Usuario(){
        return id_Usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public int getId_Persona() {
        return id_Persona;
    }

    public String getNombre_Persona() {
        return nombre_Persona;
    }

    public String getPaterno_Persona() {
        return paterno_Persona;
    }

    public String getMaterno_Persona() {
        return materno_Persona;
    }

    public int getEdad_Persona() {
        return edad_Persona;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public char getGenero_Persona() {
        return genero_Persona;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setNombre_Persona(String nombre_Persona) {
        this.nombre_Persona = nombre_Persona;
    }

    public void setPaterno_Persona(String paterno_Persona) {
        this.paterno_Persona = paterno_Persona;
    }

    public void setMaterno_Persona(String materno_Persona) {
        this.materno_Persona = materno_Persona;
    }

    public void setEdad_Persona(int edad_Persona) {
        this.edad_Persona = edad_Persona;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setGenero_Persona(char genero_Persona) {
        this.genero_Persona = genero_Persona;
    }
   
    public String toString(){
        return nombre_Persona + " " + paterno_Persona;
    }
}
