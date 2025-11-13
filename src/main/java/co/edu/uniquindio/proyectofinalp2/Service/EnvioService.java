package co.edu.uniquindio.proyectofinalp2.services;

import co.edu.uniquindio.proyectofinalp2.models.Envio;
import co.edu.uniquindio.proyectofinalp2.models.EstadoEnvio;
import co.edu.uniquindio.proyectofinalp2.repositories.Database;
import java.util.ArrayList;

/**
 * Servicio para gestionar los envíos del sistema.
 * Implementación sencilla orientada a objetos.
 */
public class EnvioService {

    private Database database;

    public EnvioService() {
        database = Database.getInstancia();
    }

    // Obtener todos los envíos registrados
    public ArrayList<Envio> listarEnvios() {
        return database.getListaEnvios();
    }

    // Buscar un envío por ID
    public Envio obtenerEnvioPorId(String idEnvio) {
        for (Envio envio : database.getListaEnvios()) {
            if (envio.getId().equals(idEnvio)) {
                return envio;
            }
        }
        return null;
    }

    // Cancelar un envío (cambiar estado a CANCELADO)
    public void cancelarEnvio(String idEnvio) {
        Envio envio = obtenerEnvioPorId(idEnvio);
        if (envio != null) {
            envio.setEstado(EstadoEnvio.CANCELADO);
            System.out.println("📦 Envío " + idEnvio + " cancelado correctamente.");
        } else {
            System.out.println("⚠️ No se encontró el envío con ID: " + idEnvio);
        }
    }

    // Agregar un nuevo envío
    public void agregarEnvio(Envio envio) {
        database.agregarEnvio(envio);
        System.out.println("✅ Envío agregado correctamente con ID: " + envio.getId());
    }
}
