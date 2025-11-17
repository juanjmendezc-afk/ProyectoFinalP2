package co.edu.uniquindio.proyectofinalp2.creacional;

import co.edu.uniquindio.proyectofinalp2.models.*;
import java.util.ArrayList;

public class EnvioFactory {

    public static Envio crearEnvioPagado(String id, Direccion origen, Direccion destino, User cliente, String direccionEntrega, double costo) {

        Pedido pedido = new Pedido("P" + id, cliente, new ArrayList<>(), direccionEntrega, costo, "PENDIENTE");

        Envio envio = new Envio();
        envio.setId(id);
        envio.setOrigen(origen);
        envio.setDestino(destino);
        envio.setPedido(pedido);
        envio.setPago(costo);
        envio.setEstadoPago(EstadoPago.PAGADO);
        envio.setEstado(EstadoEnvio.PENDIENTE);
        envio.setRepartidor(null);

        if (cliente != null) envio.agregarObserver(cliente);

        return envio;
    }

    public static Envio crearEnvioContraEntrega(String id, Direccion origen, Direccion destino, User cliente, String direccionEntrega, double costo) {

        Pedido pedido = new Pedido("P" + id, cliente, new ArrayList<>(), direccionEntrega, costo, "PENDIENTE"
        );

        Envio envio = new Envio();
        envio.setId(id);
        envio.setOrigen(origen);
        envio.setDestino(destino);
        envio.setPedido(pedido);
        envio.setPago(costo);
        envio.setEstadoPago(EstadoPago.CONTRA_ENTREGA);
        envio.setEstado(EstadoEnvio.PENDIENTE);
        envio.setRepartidor(null);

        if (cliente != null) envio.agregarObserver(cliente);

        return envio;
    }

    public static Envio crearEnvio(String id, Direccion origen, Direccion destino, User cliente, String direccionEntrega, double costo, boolean pagado) {

        if (pagado) {
            return crearEnvioPagado(id, origen, destino, cliente, direccionEntrega, costo);
        } else {
            return crearEnvioContraEntrega(id, origen, destino, cliente, direccionEntrega, costo);
        }
    }
}
