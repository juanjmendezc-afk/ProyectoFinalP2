package co.edu.uniquindio.proyectofinalp2.comportamiento;

import co.edu.uniquindio.proyectofinalp2.models.Cliente;

public class ClienteObservador extends Cliente implements Observador {

    public ClienteObservador(String id, String nombre, String direccion, String telefono, String email) {
        super(id, nombre, direccion, telefono, email);
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación para " + getNombre() + ": " + mensaje);
    }
}
