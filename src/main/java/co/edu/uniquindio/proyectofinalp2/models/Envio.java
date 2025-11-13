package co.edu.uniquindio.proyectofinalp2.models;

import java.util.ArrayList;

public class Envio {

    private String id;
    private Pedido pedido;
    private Direccion origen;
    private Direccion destino;
    private User repartidor;
    private EstadoEnvio estado;
    private double pago;

    private ArrayList<ServicioAdicional> serviciosAdicionales;
    private ArrayList<Incidencia> incidencias;

    // Constructor vacío
    public Envio() {
        serviciosAdicionales = new ArrayList<>();
        incidencias = new ArrayList<>();
    }

    // Constructor principal
    public Envio(String id, Direccion origen, Direccion destino,
                 User repartidor, EstadoEnvio estado, double pago) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.repartidor = repartidor;
        this.estado = estado;
        this.pago = pago;
        this.serviciosAdicionales = new ArrayList<>();
        this.incidencias = new ArrayList<>();
    }

    // ===== Métodos útiles =====
    public void agregarServicioAdicional(ServicioAdicional servicio) {
        serviciosAdicionales.add(servicio);
    }

    public void registrarIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        this.estado = EstadoEnvio.INCIDENCIA;
    }

    // ===== Getters y Setters =====
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Direccion getOrigen() { return origen; }
    public void setOrigen(Direccion origen) { this.origen = origen; }

    public Direccion getDestino() { return destino; }
    public void setDestino(Direccion destino) { this.destino = destino; }

    public User getRepartidor() { return repartidor; }
    public void setRepartidor(User repartidor) { this.repartidor = repartidor; }

    public EstadoEnvio getEstado() { return estado; }
    public void setEstado(EstadoEnvio estado) { this.estado = estado; }

    public double getPago() { return pago; }
    public void setPago(double pago) { this.pago = pago; }

    // ===== Alias de compatibilidad =====
    // Algunos controladores usan getCosto() en lugar de getPago()
    public double getCosto() {
        return this.pago;
    }

    public Direccion getDireccionOrigen() {
        return origen;
    }

    public Direccion getDireccionDestino() {
        return destino;
    }

    // ===== Métodos auxiliares =====
    public ArrayList<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public ArrayList<Incidencia> getIncidencias() {
        return incidencias;
    }

    public String getEstadoTexto() {
        return (estado != null) ? estado.toString() : "Sin estado";
    }

    public String getPagoTexto() {
        return (pago > 0) ? "$" + pago : "Sin pago";
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
