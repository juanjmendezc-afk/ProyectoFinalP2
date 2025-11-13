package co.edu.uniquindio.proyectofinalp2.models;

public class ServicioAdicional {

    private String nombre;      // Ej: "Seguro", "Frágil", "Prioridad"
    private double costoExtra;  // Costo adicional por el servicio

    // Constructor vacío
    public ServicioAdicional() {
    }

    // Constructor con parámetros
    public ServicioAdicional(String nombre, double costoExtra) {
        this.nombre = nombre;
        this.costoExtra = costoExtra;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoExtra() {
        return costoExtra;
    }

    public void setCostoExtra(double costoExtra) {
        this.costoExtra = costoExtra;
    }


    public String mostrarInfo() {
        return nombre + " (Costo adicional: $" + costoExtra + ")";
    }

    @Override
    public String toString() {
        return nombre + " - $" + costoExtra;
    }
}
