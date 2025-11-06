package co.edu.uniquindio.proyectofinalp2.estructural;

import co.edu.uniquindio.proyectofinalp2.models.*;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import co.edu.uniquindio.proyectofinalp2.creacional.UserFactory;
import co.edu.uniquindio.proyectofinalp2.creacional.PedidoBuilder;

public class SistemaLogisticaFacade {

    private Database db = Database.getInstancia();


    public boolean login(String email, String password) {
        return db.validarLogin(email, password);
    }

    public User registrarUsuario(String nombre, String email, String pass, String telefono, String rol) {

        String id = "U" + (db.listarUsuarios().size() + 1);
        User u = UserFactory.crearUsuario(id, nombre, email, pass, telefono, rol);

        db.agregarUsuario(u);

        return u;
    }
}
