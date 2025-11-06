package co.edu.uniquindio.proyectofinalp2.creacional;


import co.edu.uniquindio.proyectofinalp2.models.Cliente;
import co.edu.uniquindio.proyectofinalp2.models.Pedido;
import co.edu.uniquindio.proyectofinalp2.models.Producto;

import java.util.ArrayList;

public class PedidoBuilder {

    private String codigo;
    private Cliente cliente;
    private ArrayList<Producto> productos = new ArrayList<>();
    private String direccionEntrega;
    private double costo;
    private String estado;

    public PedidoBuilder codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public PedidoBuilder cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder agregarProducto(Producto p) {
        this.productos.add(p);
        return this;
    }

    public PedidoBuilder direccion(String direccion) {
        this.direccionEntrega = direccion;
        return this;
    }

    public PedidoBuilder costo(double costo) {
        this.costo = costo;
        return this;
    }

    public PedidoBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public Pedido build() {
        return new Pedido(codigo, cliente, productos, direccionEntrega, costo, estado);
    }
}
