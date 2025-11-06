package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CalcularCostoEnvio {

    private EstrategiaCosto estrategia;

    public void setEstrategia(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double distancia) {
        return estrategia.calcularCosto(distancia);
    }
}
