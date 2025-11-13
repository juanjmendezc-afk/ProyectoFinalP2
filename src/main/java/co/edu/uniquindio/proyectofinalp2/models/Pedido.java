package co.edu.uniquindio.proyectofinalp2.models;

import java.util.ArrayList;

public class Pedido {

    private String codigo;
    private User cliente;
    private ArrayList<Producto> productos;
    private String direccionEntrega;
    private double costo;
    private String estado;

    public Pedido(String codigo, User cliente, ArrayList<Producto> productos,
                  String direccionEntrega, double costo, String estado) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.productos = productos;
        this.direccionEntrega = direccionEntrega;
        this.costo = costo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public User getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getCosto() {
        return costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
