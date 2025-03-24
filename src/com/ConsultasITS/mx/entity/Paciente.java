package com.ConsultasITS.mx.entity;

public class Paciente {
    private int id_Paciente;
    private int peso_Paciente;
    private int estatura_Paciente;
    private int id_Persona;
    private String nombreCompleto;
    private int id_Usuario;
    public Paciente(){
        
    }
    public Paciente(int id_Paciente, int peso_Paciente, int estatura_Paciente, int id_Persona, String nombreCompleto, int id_Usuario) {
        this.id_Paciente = id_Paciente;
        this.peso_Paciente = peso_Paciente;
        this.estatura_Paciente = estatura_Paciente;
        this.id_Persona = id_Persona;
        this.nombreCompleto=nombreCompleto;
        this.id_Usuario=id_Usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }

    public int getId_Usuario(){
        return id_Usuario;
    }
    public String getNombreCompleto(){
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto=nombreCompleto;
    }
    public int getId_Paciente() {
        return id_Paciente;
    }

    public int getPeso_paciente() {
        return peso_Paciente;
    }

    public int getEstatura_Paciente() {
        return estatura_Paciente;
    }

    public int getId_Persona() {
        return id_Persona;
    }

    public void setId_Paciente(int id_Paciente) {
        this.id_Paciente = id_Paciente;
    }

    public void setPeso_paciente(int peso_Paciente) {
        this.peso_Paciente = peso_Paciente;
    }

    public void setEstatura_Paciente(int estatura_Paciente) {
        this.estatura_Paciente = estatura_Paciente;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }
    public String toString(){
        return nombreCompleto;
    }
}
