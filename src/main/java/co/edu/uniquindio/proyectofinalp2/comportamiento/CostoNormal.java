package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CostoNormal implements EstrategiaCosto {

    @Override
    public double calcularCosto(double distancia) {
        return distancia * 5;
    }
}

