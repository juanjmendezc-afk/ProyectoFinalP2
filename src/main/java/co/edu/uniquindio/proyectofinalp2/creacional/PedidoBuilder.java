package co.edu.uniquindio.proyectofinalp2.creacional;

import co.edu.uniquindio.proyectofinalp2.models.Pedido;
import co.edu.uniquindio.proyectofinalp2.models.Producto;
import co.edu.uniquindio.proyectofinalp2.models.User;

import java.util.ArrayList;

/**
 * Patrón Builder: permite construir objetos Pedido paso a paso
 * sin necesidad de usar un constructor con muchos parámetros.
 */
public class PedidoBuilder {

    private String codigo;
    private ArrayList<Producto> productos = new ArrayList<>();
    private String direccionEntrega;
    private double costo;
    private String estado;
    private User cliente;

    // --- Métodos encadenados (fluentes) ---
    public PedidoBuilder codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public PedidoBuilder cliente(User cliente) {
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

    // --- Método final que construye el objeto Pedido ---
    public Pedido build() {

        // Validaciones básicas para evitar errores
        if (cliente == null) {
            throw new IllegalStateException("Debe asignar un cliente al pedido.");
        }
        if (productos.isEmpty()) {
            throw new IllegalStateException("Debe agregar al menos un producto al pedido.");
        }

        // Si el código no fue asignado, generar uno automáticamente
        if (codigo == null || codigo.isEmpty()) {
            codigo = "PED-" + System.currentTimeMillis();
        }

        // Crea el pedido con los datos configurados
        return new Pedido(codigo, cliente, productos, direccionEntrega, costo, estado);
    }
}
