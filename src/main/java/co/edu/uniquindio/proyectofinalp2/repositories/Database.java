package co.edu.uniquindio.proyectofinalp2.repositories;

import co.edu.uniquindio.proyectofinalp2.models.User;
import java.util.ArrayList;

public class Database {

    private static Database instancia;

    private ArrayList<User> usuarios;

    private Database() {
        usuarios = new ArrayList<>();

        // Usuario administrador por defecto
        usuarios.add(new User(
                "A1",
                "Administrador",
                "admin@gmail.com",
                "0000",
                "0000000",
                "ADMIN"
        ));
    }

    public static Database getInstancia() {
        if (instancia == null) {
            instancia = new Database();
        }
        return instancia;
    }

    // Buscar usuario por email y contraseña
    public User obtenerUsuario(String email, String password) {
        for (User u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) &&
                    u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // Buscar usuario por email
    public User obtenerUsuarioPorEmail(String email) {
        for (User u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // Agregar usuario
    public void agregarUsuario(User user) {
        usuarios.add(user);
    }

    // Listar usuarios
    public ArrayList<User> listarUsuarios() {
        return usuarios;
    }
}
