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

    public User login(String email, String password) {
        return db.obtenerUsuario(email, password);
    }

    public boolean registrarUsuario(User nuevo) {

        if (db.obtenerUsuarioPorEmail(nuevo.getEmail()) != null) {
            return false;
        }

        String id = "U" + (db.listarUsuarios().size() + db.listarSolicitudes().size() + 1);
        nuevo.setId(id);

        db.agregarUsuario(nuevo);
        return true;
    }
}

