package co.edu.uniquindio.proyectofinalp2.models;

import java.time.LocalDate;

public class Incidencia {

    private String idIncidencia;   // Identificador único de la incidencia
    private String descripcion;    // Detalle del problema
    private LocalDate fecha;       // Fecha en que ocurrió la incidencia
    private String idEnvio;        // Id del envío asociado

    // Constructor vacío
    public Incidencia() {
    }

    // Constructor con parámetros
    public Incidencia(String idIncidencia, String descripcion, LocalDate fecha, String idEnvio) {
        this.idIncidencia = idIncidencia;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idEnvio = idEnvio;
    }

    // Getters y Setters
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

    // mostrar incidencia
    public String mostrarInfo() {
        return "Incidencia: " + descripcion + " (Fecha: " + fecha + ")";
    }

    @Override
    public String toString() {
        return idIncidencia + " - " + descripcion + " - " + fecha;
    }
}
