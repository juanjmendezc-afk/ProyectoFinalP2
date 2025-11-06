package co.edu.uniquindio.proyectofinalp2.creacional;

import co.edu.uniquindio.proyectofinalp2.models.User;

public class UserFactory {

    public static User crearUsuario(String id, String nombreCompleto, String email, String password, String telefono, String rol) {

        return new User(id, nombreCompleto, email, password, telefono, rol);
    }
}
