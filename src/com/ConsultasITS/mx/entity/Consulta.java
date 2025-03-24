
package com.ConsultasITS.mx.entity;
import java.sql.Date;
import java.sql.Timestamp;
public class Consulta {
    private int id_Consulta;
    private Date fecha_Consulta;
    private Timestamp hora_Consulta;
    private int id_Persona;
    private int id_Paciente;
    private int id_Personal_Salud;
    private int id_Usuario;
    public Consulta(){
        
    }

    public Consulta(int id_Consulta, Date fecha_Consulta, Timestamp hora_Consulta, int id_Persona, int id_Paciente, int id_Personal_Salud, int id_Usuario) {
        this.id_Consulta = id_Consulta;
        this.fecha_Consulta = fecha_Consulta;
        this.hora_Consulta = hora_Consulta;
        this.id_Persona = id_Persona;
        this.id_Paciente = id_Paciente;
        this.id_Personal_Salud = id_Personal_Salud;
        this.id_Usuario=id_Usuario;
    }

    public void setId_Usuario(int id_Usuario){
        this.id_Usuario=id_Usuario;
    }
    public int getId_Usuario(){
        return id_Usuario;
    }
    public int getId_Consulta() {
        return id_Consulta;
    }

    public Date getFecha_Consulta() {
        return fecha_Consulta;
    }

    public Timestamp getHora_Consulta() {
        return hora_Consulta;
    }

   

    public int getId_Persona() {
        return id_Persona;
    }

    public int getId_Paciente() {
        return id_Paciente;
    }

    public int getId_Personal_Salud() {
        return id_Personal_Salud;
    }

    public void setId_Consulta(int id_Consulta) {
        this.id_Consulta = id_Consulta;
    }

    public void setFecha_Consulta(Date fecha_Consulta) {
        this.fecha_Consulta = fecha_Consulta;
    }

    public void setHora_Consulta(Timestamp hora_Consulta) {
        this.hora_Consulta = hora_Consulta;
    }

    

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setId_Paciente(int id_Paciente) {
        this.id_Paciente = id_Paciente;
    }

    public void setId_Personal_Salud(int id_Personal_Salud) {
        this.id_Personal_Salud = id_Personal_Salud;
    }
    
public String toString() {
    if (this.id_Consulta == 0) {
        return "Seleccione"; // Muestra "Seleccione" para el primer ítem
    } else {
        return String.valueOf(this.id_Consulta); // Muestra el ID para los otros ítems
    }
}

}
