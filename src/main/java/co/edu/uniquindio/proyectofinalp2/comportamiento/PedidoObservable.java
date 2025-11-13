package co.edu.uniquindio.proyectofinalp2.comportamiento;

import co.edu.uniquindio.proyectofinalp2.models.Pedido;
import co.edu.uniquindio.proyectofinalp2.models.Producto;
import co.edu.uniquindio.proyectofinalp2.models.User;

import java.util.ArrayList;

public class PedidoObservable extends Pedido implements Sujeto {

    private ArrayList<Observador> observadores = new ArrayList<>();

    public PedidoObservable(String codigo, User cliente, ArrayList<Producto> productos,
                            String direccionEntrega, double costo, String estado) {
        super(codigo, cliente, productos, direccionEntrega, costo, estado);
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (Observador o : observadores) {
            o.actualizar(mensaje);
        }
    }

    @Override
    public void setEstado(String nuevoEstado) {
        super.setEstado(nuevoEstado);
        notificarObservadores("Su pedido pasó a estado: " + nuevoEstado);
    }
}
