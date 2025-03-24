/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.entity;

/**
 *
 * @author kevin
 */
public class Usuario {
    private int id_Usuario;
    private String ingreso_usuario;
    private String ingreso_contrasena;
    private char tipo_usuario;
    private String Nombre,AP_Paterno,Ap_Materno;
    
    public Usuario(){
        
    }

    public Usuario(int id_Usuario, String ingreso_usuario, String ingreso_contrasena, char tipo_usuario,String Nombre, String Ap_Paterno, String Ap_Materno) {
        this.id_Usuario = id_Usuario;
        this.ingreso_usuario = ingreso_usuario;
        this.ingreso_contrasena = ingreso_contrasena;
        this.tipo_usuario = tipo_usuario;
        this.Nombre = Nombre;
        this.AP_Paterno = Ap_Paterno;
        this.Ap_Materno = Ap_Materno;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public String getIngreso_usuario() {
        return ingreso_usuario;
    }

    public String getIngreso_contrasena() {
        return ingreso_contrasena;
    }

    public char getTipo_usuario() {
        return tipo_usuario;
    }
    public String getNombre(){
    return Nombre;
    }
        public String getAp_Paterno(){
    return AP_Paterno;
    }
            public String getAp_Materno(){
    return Ap_Materno;
    }
    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public void setIngreso_usuario(String ingreso_usuario) {
        this.ingreso_usuario = ingreso_usuario;
    }

    public void setIngreso_contrasena(String ingreso_contrasena) {
        this.ingreso_contrasena = ingreso_contrasena;
    }

    public void setTipo_usuario(char tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    
     public void setAp_Paterno(String Ap_Paterno){
        this.AP_Paterno = Ap_Paterno;
    }
     
      public void setAp_Materno(String Ap_Materno){
        this.Ap_Materno = Ap_Materno;
    }
    
}
