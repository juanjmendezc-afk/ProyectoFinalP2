package co.edu.uniquindio.proyectofinalp2.comportamiento;

public class CostoMediano implements EstrategiaCosto {

    @Override
    public double calcularCosto(String destino) {
        double costoBase = 20000;

        if (destino.equals("Nacional")) {
            costoBase *= 1.5;
        }

        return costoBase;
    }
}
