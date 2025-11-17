package co.edu.uniquindio.proyectofinalp2.repositories;

import co.edu.uniquindio.proyectofinalp2.models.*;

import java.util.ArrayList;

public class Database {

    private static Database instancia;

    private ArrayList<User> usuarios;
    private ArrayList<User> solicitudesRepartidor;

    private ArrayList<Envio> listaEnvios;
    private ArrayList<Pedido> listaPedidos;

    private Database() {

        usuarios = new ArrayList<>();
        solicitudesRepartidor = new ArrayList<>();
        listaEnvios = new ArrayList<>();
        listaPedidos = new ArrayList<>();

        // Admin por defecto
        usuarios.add(new User(
                "A1",
                "Administrador del sistema",
                "admin@gmail.com",
                "0000",
                "0000000",
                "ADMIN",
                "CC",
                "1111111",
                "ACTIVO"
        ));
    }

    public static Database getInstancia() {
        if (instancia == null) instancia = new Database();
        return instancia;
    }
    public User obtenerUsuario(String email, String password) {
        for (User u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)
                    && u.getPassword().equals(password)
                    && "ACTIVO".equals(u.getEstado())) {
                return u;
            }
        }
        return null;
    }

    public User obtenerUsuarioPorEmail(String email) {

        for (User u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }

        for (User u : solicitudesRepartidor) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }

        return null;
    }

    public void agregarUsuario(User user) {

        if ("PENDIENTE_APROBACION".equals(user.getEstado())) {
            solicitudesRepartidor.add(user);
        } else {
            usuarios.add(user);
        }
    }

    public ArrayList<User> listarUsuarios() { return usuarios; }

    public ArrayList<User> listarSolicitudes() { return solicitudesRepartidor; }

    public void aprobarRepartidor(User user) {
        user.setEstado("ACTIVO");
        solicitudesRepartidor.remove(user);
        usuarios.add(user);
    }

    public void rechazarRepartidor(User user) {
        solicitudesRepartidor.remove(user);
    }

    // Pedidos y envios

    public void agregarPedido(Pedido p) {
        listaPedidos.add(p);
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void agregarEnvio(Envio e) {
        listaEnvios.add(e);

        if (e.getPedido() != null) {
            listaPedidos.add(e.getPedido());
        }
    }

    public ArrayList<Envio> getListaEnvios() {
        return listaEnvios;
    }

    public boolean asignarRepartidorAEnvio(Envio envio, User repartidor) {

        if (envio == null || repartidor == null) return false;

        if (!envio.puedeSerAsignado()) {
            return false;
        }

        envio.setRepartidor(repartidor);
        envio.setEstado(EstadoEnvio.EN_CAMINO);

        return true;
    }

    public void actualizarEstadoEnvio(Envio envio, EstadoEnvio nuevoEstado) {
        if (envio != null) envio.setEstado(nuevoEstado);
    }

    public void marcarPagoRealizado(Envio envio) {
        if (envio != null) envio.setEstadoPago(EstadoPago.PAGADO);
    }

    public void marcarPagoContraEntrega(Envio envio) {
        if (envio != null) envio.setEstadoPago(EstadoPago.CONTRA_ENTREGA);
    }
}

