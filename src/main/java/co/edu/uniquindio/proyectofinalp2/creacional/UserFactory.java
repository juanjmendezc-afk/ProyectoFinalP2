package co.edu.uniquindio.proyectofinalp2.creacional;

import co.edu.uniquindio.proyectofinalp2.models.User;

public class UserFactory {

    public static User crearUsuario(
            String id,
            String nombre,
            String email,
            String password,
            String telefono,
            String rol
    ) {
        // Valores por defecto para los nuevos campos
        String tipoId = "CC";
        String numeroId = "0000000000";
        String estado = "ACTIVO";

        return new User(
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
    }

    public static User crearRepartidorPendiente(
            String id,
            String nombre,
            String email,
            String password,
            String telefono,
            String tipoId,
            String numeroId
    ) {
        return new User(
                id,
                nombre,
                email,
                password,
                telefono,
                "REPARTIDOR",
                tipoId,
                numeroId,
                "PENDIENTE_APROBACION"
        );
    }
}
