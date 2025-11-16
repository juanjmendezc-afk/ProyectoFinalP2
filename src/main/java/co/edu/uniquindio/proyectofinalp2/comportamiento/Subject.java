package co.edu.uniquindio.proyectofinalp2.comportamiento;

public interface Subject {

    void agregarObserver(Observer o);

    void eliminarObserver(Observer o);

    void notificarObservers(String mensaje);
}
