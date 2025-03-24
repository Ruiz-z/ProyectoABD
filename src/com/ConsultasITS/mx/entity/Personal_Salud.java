package com.ConsultasITS.mx.entity;

public class Personal_Salud {
    private int id_Personal_Salud;
    private String nombre_Personal_Salud;
    private String paterno_Personal;
    private String materno_Personal;
    private int edad_Personal_Salud;
    private String tipo_Personal_Salud;
    private int id_Usuario;
   public Personal_Salud(){
       
   }   

    public Personal_Salud(int id_Personal_Salud, String nombre_Personal_Salud, String paterno_Personal, String materno_Personal, int edad_Personal_Salud, String tipo_Personal_Salud, int id_Usuario) {
        this.id_Personal_Salud = id_Personal_Salud;
        this.nombre_Personal_Salud = nombre_Personal_Salud;
        this.paterno_Personal = paterno_Personal;
        this.materno_Personal = materno_Personal;
        this.edad_Personal_Salud = edad_Personal_Salud;
        this.tipo_Personal_Salud = tipo_Personal_Salud;
    }

    public int getId_Usuario(){
        return id_Usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public int getId_Personal_Salud() {
        return id_Personal_Salud;
    }

    public String getNombre_Personal_Salud() {
        return nombre_Personal_Salud;
    }

    public String getPaterno_Personal() {
        return paterno_Personal;
    }

    public String getMaterno_Personal() {
        return materno_Personal;
    }

    public int getEdad_Personal_Salud() {
        return edad_Personal_Salud;
    }

    public String getTipo_Personal_Salud() {
        return tipo_Personal_Salud;
    }

    public void setId_Personal_Salud(int id_Personal_Salud) {
        this.id_Personal_Salud = id_Personal_Salud;
    }

    public void setNombre_Personal_Salud(String nombre_Personal_Salud) {
        this.nombre_Personal_Salud = nombre_Personal_Salud;
    }

    public void setPaterno_Personal(String paterno_Personal) {
        this.paterno_Personal = paterno_Personal;
    }

    public void setMaterno_Personal(String materno_Personal) {
        this.materno_Personal = materno_Personal;
    }

    public void setEdad_Personal_Salud(int edad_Personal_Salud) {
        this.edad_Personal_Salud = edad_Personal_Salud;
    }

    public void setTipo_Personal_Salud(String tipo_Personal_Salud) {
        this.tipo_Personal_Salud = tipo_Personal_Salud;
    }

   

    public String toString() {
        return nombre_Personal_Salud + " " + paterno_Personal;  // Mostrar nombre y apellido en el ComboBox
    }
   
}
