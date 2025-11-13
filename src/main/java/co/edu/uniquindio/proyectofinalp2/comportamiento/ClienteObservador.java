package co.edu.uniquindio.proyectofinalp2.comportamiento;

import co.edu.uniquindio.proyectofinalp2.models.User;

public class ClienteObservador extends User implements Observador {

    public ClienteObservador(String id, String nombre, String email, String password, String telefono, String rol) {
        super(id, nombre, email, password, telefono, rol);
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación para " + getNombreCompleto() + ": " + mensaje);
    }
}
