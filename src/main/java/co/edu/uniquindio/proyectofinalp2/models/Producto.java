package co.edu.uniquindio.proyectofinalp2.models;

public class Producto {

    private String id;
    private String nombre;
    private double peso;
    private double precio;
    private String descripcion;

    public Producto(String id, String nombre, double peso, double precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
