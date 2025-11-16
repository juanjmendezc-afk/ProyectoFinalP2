package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CalculadoraCostoEnvio {

    private EstrategiaCosto estrategia;

    public void setEstrategia(EstrategiaCosto estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(String destino) {
        return estrategia.calcularCosto(destino);
    }
}
