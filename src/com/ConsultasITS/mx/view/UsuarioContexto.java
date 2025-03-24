/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ConsultasITS.mx.view;

/**
 *
 * @author kevin
 */
public class UsuarioContexto {
    private static String tipoUsuario;
    private static String nombreUsuario,nombreReal,Ap_paterno,Ap_Materno;
    private static int idUser;
    public static void setId_Usuario(int id_Usuario){
        idUser=id_Usuario;
    }
    public static int getId_Usuario(){
        return idUser;
    }
    public static void setNombreUsuario(String nombre){
        nombreUsuario=nombre;
    }
    public static void setTipoUsuario(String tipo){
        tipoUsuario=tipo;
    }
    public static String getNombreUsuario(){
        return nombreUsuario;
    }
    public static String getTipoUsuario(){
        return tipoUsuario;
    }
    
    public static void setNombreReal(String nombreR){
        nombreReal = nombreR;
     }
    public static void setAp_Paterno(String Paterno){
        Ap_paterno=Paterno;
    }
    public static void setAp_Materno(String Materno) {
        Ap_Materno= Materno;
    }
    
    public static String getNombreReal(){
    return nombreReal;
    }
    
    public static String getAp_Paterno(){
    return Ap_paterno;
    }
    
    public static String getAp_Materno(){
    return Ap_Materno;
    }
}
