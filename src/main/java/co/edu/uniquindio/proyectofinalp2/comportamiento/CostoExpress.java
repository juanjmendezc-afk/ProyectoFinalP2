package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CostoExpress implements EstrategiaCosto {

    @Override
    public double calcularCosto(double distancia) {
        return distancia * 10;
    }
}
