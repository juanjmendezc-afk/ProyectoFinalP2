package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CostoLiviano implements EstrategiaCosto {

    @Override
    public double calcularCosto(String destino) {
        double costoBase = 10000;

        if (destino.equals("Nacional")) {
            costoBase *= 1.5;
        }

        return costoBase;
    }
}
