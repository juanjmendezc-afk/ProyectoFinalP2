package co.edu.uniquindio.proyectofinalp2.estructural;

import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;

public class SistemaLogisticaFacade {

    private static SistemaLogisticaFacade instancia;
    private final Database db;

    private SistemaLogisticaFacade() {
        db = Database.getInstancia();
    }

    public static SistemaLogisticaFacade getInstancia() {
        if (instancia == null) {
            instancia = new SistemaLogisticaFacade();
        }
        return instancia;
    }

    // Devuelve el usuario si existe o null
    public User login(String email, String password) {
        return db.obtenerUsuario(email, password);
    }

    // Registrar un nuevo usuario
    public boolean registrarUsuario(String nombre, String email, String telefono, String password, String rol) {

        User existente = db.obtenerUsuarioPorEmail(email);
        if (existente != null) {
            return false;
        }

        String id = "U" + (db.listarUsuarios().size() + 1);

        // Constructor correcto (6 parámetros)
        User nuevo = new User(id, nombre, email, password, telefono, rol);

        db.agregarUsuario(nuevo);
        return true;
    }
}
