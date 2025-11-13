package co.edu.uniquindio.proyectofinalp2.repositories;

import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.models.EstadoEnvio;
import co.edu.uniquindio.proyectofinalp2.models.Direccion;
import java.util.ArrayList;

public class Database {

    // Instancia única (Singleton)
    private static Database instancia;

    // Listas simuladas de usuarios y envíos
    private ArrayList<User> usuarios;
    private ArrayList<Envio> listaEnvios;

    // Constructor privado
    private Database() {
        usuarios = new ArrayList<>();
        listaEnvios = new ArrayList<>();

        // ===== Usuario administrador por defecto =====
        usuarios.add(new User(
                "A1",
                "Administrador",
                "admin@gmail.com",
                "0000",
                "0000000",
                "ADMIN"
        ));

        // ===== Datos de ejemplo =====

        // Crear clientes
        User cliente1 = new User("C1", "Juan Pérez", "juan@gmail.com", "1234", "3100000000", "CLIENTE");
        User cliente2 = new User("C2", "María Gómez", "maria@gmail.com", "1234", "3200000000", "CLIENTE");

        // Crear repartidores
        User repartidor1 = new User("R1", "Carlos López", "carlos@gmail.com", "1234", "3150000000", "REPARTIDOR");
        User repartidor2 = new User("R2", "Ana Ruiz", "ana@gmail.com", "1234", "3160000000", "REPARTIDOR");

        // Agregar usuarios a la lista
        usuarios.add(cliente1);
        usuarios.add(cliente2);
        usuarios.add(repartidor1);
        usuarios.add(repartidor2);

        // Direcciones simples
        Direccion origen1 = new Direccion("Calle 10 #5-23", "Armenia");
        Direccion destino1 = new Direccion("Carrera 7 #45-10", "Cali");

        Direccion origen2 = new Direccion("Av. Bolívar 120", "Armenia");
        Direccion destino2 = new Direccion("Calle 40 #12-30", "Medellín");

        // ===== Envíos de prueba =====
        Envio envio1 = new Envio("E001", origen1, destino1, repartidor1, EstadoEnvio.PENDIENTE, 25000.0);
        Envio envio2 = new Envio("E002", origen2, destino2, repartidor2, EstadoEnvio.EN_CAMINO, 32000.0);
        Envio envio3 = new Envio("E003", origen1, destino2, repartidor1, EstadoEnvio.ENTREGADO, 40000.0);

        listaEnvios.add(envio1);
        listaEnvios.add(envio2);
        listaEnvios.add(envio3);
    }

    // ===== Singleton =====
    public static Database getInstancia() {
        if (instancia == null) {
            instancia = new Database();
        }
        return instancia;
    }

    // ===== Usuarios =====
    public User obtenerUsuario(String email, String password) {
        for (User u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public User obtenerUsuarioPorEmail(String email) {
        for (User u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public void agregarUsuario(User user) {
        usuarios.add(user);
    }

    public ArrayList<User> listarUsuarios() {
        return usuarios;
    }

    // ===== Envíos =====
    public ArrayList<Envio> getListaEnvios() {
        return listaEnvios;
    }

    public void setListaEnvios(ArrayList<Envio> listaEnvios) {
        this.listaEnvios = listaEnvios;
    }

    public void agregarEnvio(Envio envio) {
        listaEnvios.add(envio);
    }

    public Envio obtenerEnvioPorId(String idEnvio) {
        for (Envio e : listaEnvios) {
            if (e.getId().equals(idEnvio)) {
                return e;
            }
        }
        return null;
    }

    public void actualizarEnvio(Envio envioActualizado) {
        for (int i = 0; i < listaEnvios.size(); i++) {
            Envio actual = listaEnvios.get(i);
            if (actual.getId().equals(envioActualizado.getId())) {
                listaEnvios.set(i, envioActualizado);
                return;
            }
        }
    }
}
