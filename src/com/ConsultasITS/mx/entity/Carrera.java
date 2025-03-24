
package com.ConsultasITS.mx.entity;

public class Carrera {
   private int id_Carrera;
   private String nombre_Carrera;
   private int id_Usuario;
   public Carrera(){
       
   }

    public Carrera(int id_Carrera, String nombre_Carrera, int id_Usuario) {
        this.id_Carrera = id_Carrera;
        this.nombre_Carrera = nombre_Carrera;
        this.id_Usuario=id_Usuario;
    }

    public int getId_Usuario(){
        return id_Usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public int getId_Carrera() {
        return id_Carrera;
    }

    public String getNombre_Carrera() {
        return nombre_Carrera;
    }

    public void setId_Carrera(int id_Carrera) {
        this.id_Carrera = id_Carrera;
    }

    public void setNombre_Carrera(String nombre_Carrera) {
        this.nombre_Carrera = nombre_Carrera;
    }
   public String toString(){
       return  nombre_Carrera; 
   }
}
