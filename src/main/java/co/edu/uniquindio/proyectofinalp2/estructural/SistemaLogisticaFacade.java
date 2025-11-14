package co.edu.uniquindio.proyectofinalp2.estructural;

import co.edu.uniquindio.proyectofinalp2.models.User;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;

public class SistemaLogisticaFacade {

    private static SistemaLogisticaFacade instancia;
    private final Database db = Database.getInstancia();

    private SistemaLogisticaFacade() {}

    public static SistemaLogisticaFacade getInstancia() {
        if (instancia == null) instancia = new SistemaLogisticaFacade();
        return instancia;
    }

    // =============================
    // LOGIN
    // =============================
    public User login(String email, String password) {
        return db.obtenerUsuario(email, password);
    }

    // =============================
    // REGISTRO DE USUARIO (ACTUALIZADO)
    // =============================
    public boolean registrarUsuario(
            String nombre,
            String email,
            String telefono,
            String password,
            String rol,
            String tipoId,
            String numeroId,
            String estado
    ) {

        // Verificar si el email ya existe
        if (db.obtenerUsuarioPorEmail(email) != null) {
            return false;
        }

        // Generar ID automático
        String id = "U" + (db.listarUsuarios().size() + db.listarSolicitudes().size() + 1);

        User nuevo = new User(
                id,
                nombre,
                email,
                password,
                telefono,
                rol,
                tipoId,
                numeroId,
                estado
        );

        db.agregarUsuario(nuevo);

        return true;
    }
}
