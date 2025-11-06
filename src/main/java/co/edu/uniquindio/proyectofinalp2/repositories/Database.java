package co.edu.uniquindio.proyectofinalp2.repositories;

import co.edu.uniquindio.proyectofinalp2.models.User;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> usuarios;
    private User usuarioActivo; // ← SESIÓN ACTIVA
    private static Database instancia;

    // Patrón Singleton
    private Database() {
        this.usuarios = new ArrayList<>();
        cargarDatosPrueba();
    }

    public static Database getInstancia() {
        if (instancia == null) {
            instancia = new Database();
        }
        return instancia;
    }

    private void cargarDatosPrueba() {
        // Datos de prueba
        usuarios.add(new User("U001", "Juan Pérez", "juan@email.com", "1234", "3001234567", "USUARIO"));
        usuarios.add(new User("U002", "María García", "maria@email.com", "5678", "3009876543", "USUARIO"));
        usuarios.add(new User("A001", "Administrador", "admin@email.com", "admin", "3001112233", "ADMIN"));

        System.out.println("Datos cargados: " + usuarios.size() + " usuarios");
    }

    // Método para validar login (REEMPLAZA a Autenticador)
    public boolean autenticar(String email, String password) {
        for (User usuario : usuarios) {
            if (usuario.validarCredenciales(email, password)) {
                this.usuarioActivo = usuario; // Guardar sesión
                System.out.println("Login exitoso: " + usuario.getEmail());
                return true;
            }
        }
        System.out.println("Login fallido para: " + email);
        return false;
    }

    // Obtener usuario activo
    public User getUsuarioActivo() {
        return usuarioActivo;
    }

    // Cerrar sesión
    public void cerrarSesion() {
        System.out.println("Cerrando sesión de: " + (usuarioActivo != null ? usuarioActivo.getEmail() : "ninguno"));
        this.usuarioActivo = null;
    }

    // Verificar si hay sesión activa
    public boolean estaAutenticado() {
        return usuarioActivo != null;
    }

    // Verificar si es admin
    public boolean esAdmin() {
        return estaAutenticado() && "ADMIN".equals(usuarioActivo.getTipo());
    }

    // Para registro de nuevos usuarios
    public void agregarUsuario(User usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario registrado: " + usuario.getEmail());
    }

    public List<User> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}