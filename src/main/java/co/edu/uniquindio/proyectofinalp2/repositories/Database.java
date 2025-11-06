package co.edu.uniquindio.proyectofinalp2.repositories;

import co.edu.uniquindio.proyectofinalp2.models.Cliente;
import co.edu.uniquindio.proyectofinalp2.models.Pedido;
import co.edu.uniquindio.proyectofinalp2.models.Producto;
import co.edu.uniquindio.proyectofinalp2.models.User;

import java.util.ArrayList;

public class Database {

    private ArrayList<User> listaUsuarios = new ArrayList<>();
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();

    private static Database instancia;

    private Database() {

        User admin = new User(
                "U1",
                "Admin",
                "admin@gmail.com",
                "12345",
                "3000000000",
                "ADMIN"
        );

        listaUsuarios.add(admin);
    }

    public static Database getInstancia() {
        if (instancia == null) {
            instancia = new Database();
        }
        return instancia;
    }

    public void agregarUsuario(User u) {
        listaUsuarios.add(u);
    }

    public ArrayList<User> listarUsuarios() {
        return listaUsuarios;
    }

    public boolean validarLogin(String email, String password) {
        for (User u : listaUsuarios) {
            if (u.getEmail().equalsIgnoreCase(email)
                    && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void agregarCliente(Cliente c) {
        listaClientes.add(c);
    }

    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }

    public void agregarProducto(Producto p) {
        listaProductos.add(p);
    }

    public ArrayList<Producto> listarProductos() {
        return listaProductos;
    }

    public void agregarPedido(Pedido p) {
        listaPedidos.add(p);
    }

    public ArrayList<Pedido> listarPedidos() {
        return listaPedidos;
    }
}
