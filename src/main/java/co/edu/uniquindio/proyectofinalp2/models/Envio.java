package co.edu.uniquindio.proyectofinalp2.models;

import co.edu.uniquindio.proyectofinalp2.comportamiento.Observer;
import co.edu.uniquindio.proyectofinalp2.comportamiento.Subject;

import java.util.ArrayList;

public class Envio implements Subject {

    private String id;
    private Pedido pedido;
    private Direccion origen;
    private Direccion destino;
    private User repartidor;

    private EstadoEnvio estado;
    private double pago;

    private EstadoPago estadoPago;

    private ArrayList<ServicioAdicional> serviciosAdicionales;
    private ArrayList<Incidencia> incidencias;

    // Observadores que serán notificados
    private ArrayList<Observer> observers = new ArrayList<>();

    public Envio() {
        serviciosAdicionales = new ArrayList<>();
        incidencias = new ArrayList<>();
        estadoPago = EstadoPago.PENDIENTE;
    }

    public Envio(String id, Direccion origen, Direccion destino,
                 User repartidor, EstadoEnvio estado,
                 double pago, EstadoPago estadoPago) {

        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.repartidor = repartidor;
        this.estado = estado;
        this.pago = pago;
        this.estadoPago = estadoPago;

        this.serviciosAdicionales = new ArrayList<>();
        this.incidencias = new ArrayList<>();
    }

    // ====================== MÉTODO AGREGADO ======================
    /**
     * Indica si un envío puede ser asignado.
     * Debe estar pagado o ser contra entrega y seguir en estado PENDIENTE.
     */
    public boolean puedeSerAsignado() {
        return (estadoPago == EstadoPago.PAGADO || estadoPago == EstadoPago.CONTRA_ENTREGA)
                && estado == EstadoEnvio.PENDIENTE;
    }

    // ====================== OBSERVER ======================

    @Override
    public void agregarObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void eliminarObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservers(String mensaje) {
        for (Observer o : observers) {
            o.recibirNotificacion("Envío " + id + ": " + mensaje);
        }
    }

    /**
     * Cambia el estado del envío y notifica automáticamente.
     */
    public void cambiarEstado(EstadoEnvio nuevo) {
        this.estado = nuevo;
        notificarObservers("El estado cambió a: " + nuevo);
    }

    // ====================== GETTERS & SETTERS ======================

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Direccion getOrigen() { return origen; }
    public void setOrigen(Direccion origen) { this.origen = origen; }

    public Direccion getDestino() { return destino; }
    public void setDestino(Direccion destino) { this.destino = destino; }

    public User getRepartidor() { return repartidor; }

    /**
     * Cuando se asigna un repartidor, lo agregamos como observer.
     */
    public void setRepartidor(User repartidor) {
        this.repartidor = repartidor;
        if (repartidor != null) {
            agregarObserver(repartidor);
        }
    }

    public EstadoEnvio getEstado() { return estado; }

    /**
     * Si el estado cambia desde fuera del observer, igual notificamos.
     */
    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
        notificarObservers("El estado cambió a: " + estado);
    }

    public double getPago() { return pago; }
    public void setPago(double pago) { this.pago = pago; }

    public EstadoPago getEstadoPago() { return estadoPago; }
    public void setEstadoPago(EstadoPago estadoPago) { this.estadoPago = estadoPago; }

    public ArrayList<ServicioAdicional> getServiciosAdicionales() { return serviciosAdicionales; }
    public ArrayList<Incidencia> getIncidencias() { return incidencias; }

    public String getEstadoTexto() {
        return (estado != null) ? estado.toString() : "Sin estado";
    }

    public String getPagoTexto() {
        return estadoPago == EstadoPago.PAGADO ? "Pagado"
                : estadoPago == EstadoPago.CONTRA_ENTREGA ? "Contra entrega"
                : "Pendiente";
    }

    public String getRepartidorTexto() {
        return (repartidor != null) ? repartidor.getNombreCompleto() : "No asignado";
    }

    public String getClienteTexto() {
        if (pedido != null && pedido.getCliente() != null) {
            return pedido.getCliente().getNombreCompleto();
        }
        return "No registrado";
    }
}
