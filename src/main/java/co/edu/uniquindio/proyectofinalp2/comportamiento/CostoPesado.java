package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CostoPesado implements EstrategiaCosto {

    @Override
    public double calcularCosto(String destino) {
        double costoBase = 35000;

        if (destino.equals("Nacional")) {
            costoBase *= 1.5;
        }

        return costoBase;
    }
}
