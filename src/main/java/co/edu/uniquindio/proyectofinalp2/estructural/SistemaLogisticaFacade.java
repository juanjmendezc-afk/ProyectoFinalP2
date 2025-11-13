package co.edu.uniquindio.proyectofinalp2.estructural;

import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import co.edu.uniquindio.proyectofinalp2.creacional.UserFactory;

/**
 * Patrón Facade: expone métodos simples para interactuar
 * con el sistema sin conocer la lógica interna.
 */
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

    /**
     * Inicia sesión verificando usuario y contraseña.
     * @return el usuario si existe o null si no se encuentra.
     */
    public User login(String email, String password) {
        return db.obtenerUsuario(email, password);
    }

    /**
     * Registra un nuevo usuario (usa el patrón Factory).
     * @param nombre nombre completo
     * @param email correo
     * @param telefono teléfono
     * @param password contraseña
     * @param rol rol del usuario ("ADMIN", "USER", "REPARTIDOR")
     * @return true si se registró correctamente, false si ya existe el email.
     */
    public boolean registrarUsuario(String nombre, String email, String telefono, String password, String rol) {

        // Verificar si ya existe un usuario con ese correo
        User existente = db.obtenerUsuarioPorEmail(email);
        if (existente != null) {
            return false;
        }

        //  Aquí aplicamos el patrón Factory
        User nuevo = UserFactory.crearUsuario(nombre, email, telefono, password, rol);

        db.agregarUsuario(nuevo);
        return true;
    }
}
