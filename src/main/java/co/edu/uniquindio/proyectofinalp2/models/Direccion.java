package co.edu.uniquindio.proyectofinalp2.models;

public class Direccion {

    private String calle;
    private String ciudad;

    // Constructor vacío
    public Direccion() {
    }

    // Constructor con parámetros
    public Direccion(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

    // ===== Getters y Setters =====
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    // ===== Métodos compatibles con controladores =====
    // (algunos controladores usan getDireccion() en lugar de getCalle())
    public String getDireccion() {
        return calle;
    }

    public void setDireccion(String direccion) {
        this.calle = direccion;
    }

    // Método para mostrar dirección completa en texto
    @Override
    public String toString() {
        return calle + ", " + ciudad;
    }
}
