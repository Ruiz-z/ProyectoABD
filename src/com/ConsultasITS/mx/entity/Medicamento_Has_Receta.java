
package com.ConsultasITS.mx.entity;
public class Medicamento_Has_Receta {
    private int id_Medicamento;
    private int id_Consulta;
    private int id_Receta;
    private int id_usuario;
    public Medicamento_Has_Receta(){
        
    }

    public Medicamento_Has_Receta(int id_Medicamento, int id_Consulta, int id_Receta, int id_Usuario) {
        this.id_Medicamento = id_Medicamento;
        this.id_Consulta = id_Consulta;
        this.id_Receta = id_Receta;
        this.id_usuario=id_Usuario;
    }
    public int getId_Usuario(){
        return  id_usuario;
    }
    public void setId_Usuario(int id_Usuario){
        this.id_usuario=id_Usuario;
    }

    public int getId_Medicamento() {
        return id_Medicamento;
    }

    public int getId_Consulta() {
        return id_Consulta;
    }

    public int getId_Receta() {
        return id_Receta;
    }

    public void setId_Medicamento(int id_Medicamento) {
        this.id_Medicamento = id_Medicamento;
    }

    public void setId_Consulta(int id_Consulta) {
        this.id_Consulta = id_Consulta;
    }

    public void setId_Receta(int id_Receta) {
        this.id_Receta = id_Receta;
    }
    public String toString(){
        return "ID MEDICAMENTO: " + id_Medicamento +
                "\nID CONSULTA: " + id_Consulta + 
                "\nID RECETA: " + id_Receta;
    }
}
