package co.edu.uniquindio.proyectofinalp2.comportamiento;

public interface Observado {
    void agregarObservador(Observador o);
    void eliminarObservador(Observador o);
    void notificarObservadores(String mensaje);
}
