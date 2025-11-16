package co.edu.uniquindio.proyectofinalp2.estructural;

import co.edu.uniquindio.proyectofinalp2.creacional.UserBuilder;
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

    // login normal
    public User login(String email, String password) {
        return db.obtenerUsuario(email, password);
    }

    // ========= REGISTRO (VERSIÓN ANTIGUA - OPCIONAL) =========
    // se deja por si algún controlador viejo lo usa
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

        if (db.obtenerUsuarioPorEmail(email) != null) return false;

        String id = "U" + (db.listarUsuarios().size() + db.listarSolicitudes().size() + 1);

        User nuevo = new UserBuilder()
                .id(id)
                .nombre(nombre)
                .email(email)
                .telefono(telefono)
                .password(password)
                .rol(rol)
                .tipoId(tipoId)
                .numeroId(numeroId)
                .estado(estado)
                .build();

        db.agregarUsuario(nuevo);
        return true;
    }

    // ========= REGISTRO (VERSIÓN NUEVA CON BUILDER) =========
    // esta es la que usa tu RegistroController actual
    public boolean registrarUsuario(User nuevo) {

        // verificar email único
        if (db.obtenerUsuarioPorEmail(nuevo.getEmail()) != null) {
            return false;
        }

        // asignar ID
        String id = "U" + (db.listarUsuarios().size() + db.listarSolicitudes().size() + 1);
        nuevo.setId(id);

        db.agregarUsuario(nuevo);
        return true;
    }
}
