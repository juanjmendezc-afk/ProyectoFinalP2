package co.edu.uniquindio.proyectofinalp2.comportamiento;

/**
 * Interfaz que representa un observador.
 * Cualquier clase que quiera recibir notificaciones la implementa.
 */
public interface Observer {
    void recibirNotificacion(String mensaje);
}
