package co.edu.uniquindio.proyectofinalp2.comportamiento;

import co.edu.uniquindio.proyectofinalp2.models.User;

public class ClienteObservador extends User implements Observador {

    public ClienteObservador(
            String id,
            String nombreCompleto,
            String email,
            String password,
            String telefono,
            String tipoIdentificacion,
            String numeroIdentificacion
    ) {
        super(
                id,
                nombreCompleto,
                email,
                password,
                telefono,
                "USER",                // rol fijo del observador
                tipoIdentificacion,
                numeroIdentificacion,
                "ACTIVO"               // estado por defecto
        );
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación para " + getNombreCompleto() + ": " + mensaje);
    }
}

