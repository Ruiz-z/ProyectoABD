package com.ConsultasITS.mx.entity;

public class Alumno{
    private int id_Alumno;
    private int semestre;
    private int id_Persona;
    private int id_Carrera;
    private int id_Usuario;
    public Alumno(){
        
    }

    public Alumno(int id_Alumno, int semestre, int id_Persona, int id_Carrera, int id_Usuario) {
        this.id_Alumno = id_Alumno;
        this.semestre = semestre;
        this.id_Persona = id_Persona;
        this.id_Carrera = id_Carrera;
        this.id_Usuario=id_Usuario;
    }

    public int getId_Usuario(){
        return id_Usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public int getId_Alumno() {
        return id_Alumno;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getId_Persona() {
        return id_Persona;
    }

    public int getId_Carrera() {
        return id_Carrera;
    }

    public void setId_Alumno(int id_Alumno) {
        this.id_Alumno = id_Alumno;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setId_Carrera(int id_Carrera) {
        this.id_Carrera = id_Carrera;
    }
    public String toString(){
        return "ID_ALUMNO" + id_Alumno + 
                "\nSEMESTRE: " + semestre +
                "\nID_PERSONA: " + id_Persona + 
                "\nID_CARRERA. " + id_Carrera;
    }
}
