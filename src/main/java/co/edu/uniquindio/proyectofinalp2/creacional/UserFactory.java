package co.edu.uniquindio.proyectofinalp2.creacional;

import co.edu.uniquindio.proyectofinalp2.models.User;
import java.util.UUID;

/**
 * Patrón Factory Method: centraliza la creación de usuarios según su rol.
 */
public class UserFactory {

    public static User crearUsuario(String nombre, String email, String telefono, String password, String rol) {
        // Generar ID automático
        String id = "U" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        String rolFinal = rol.toUpperCase();

        return new User(id, nombre, email, password, telefono, rolFinal);
    }
}
