package co.edu.uniquindio.proyectofinalp2.comportamiento;

import java.util.ArrayList;
import java.util.List;

public class EnvioObservable {

    private List<Observador> observadores = new ArrayList<>();

    // Agregar un observador (cliente, por ejemplo)
    public void agregarObservador(Observador obs) {
        observadores.add(obs);
    }

    // Notificar a todos los observadores
    public void notificarObservadores(String mensaje) {
        for (Observador obs : observadores) {
            obs.actualizar(mensaje);
        }
    }
}
