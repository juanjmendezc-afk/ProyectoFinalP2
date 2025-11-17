package co.edu.uniquindio.proyectofinalp2.models;

import java.time.LocalDate;

public class Incidencia {

    private String idIncidencia;
    private String descripcion;
    private LocalDate fecha;
    private String idEnvio;

    public Incidencia() {
    }

    public Incidencia(String idIncidencia, String descripcion, LocalDate fecha, String idEnvio) {
        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idEnvio = idEnvio;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String mostrarInfo() {
        return "Incidencia: " + descripcion + " (Fecha: " + fecha + ")";
    }

    @Override
    public String toString() {
        return idIncidencia + " - " + descripcion + " - " + fecha;
    }
}
