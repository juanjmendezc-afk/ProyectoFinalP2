package co.edu.uniquindio.proyectofinalp2.models;

public class Direccion {

    private String calle;
    private String ciudad;


    public Direccion() {
    }

    public Direccion(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

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

    public String getDireccion() {
        return calle;
    }

    public void setDireccion(String direccion) {
        this.calle = direccion;
    }

    // direccion completa
    @Override
    public String toString() {
        return calle + ", " + ciudad;
    }
}
