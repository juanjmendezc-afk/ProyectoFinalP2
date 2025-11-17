package co.edu.uniquindio.proyectofinalp2.models;

public class ServicioAdicional {

    private String nombre;
    private double costoExtra;

    public ServicioAdicional() {
    }

    public ServicioAdicional(String nombre, double costoExtra) {
        this.nombre = nombre;
        this.costoExtra = costoExtra;
    }

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
