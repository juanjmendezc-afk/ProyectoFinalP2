package co.edu.uniquindio.proyectofinalp2.models;

public class Tarifa {

    private double costoBase;
    private double costoPorKm;

    public Tarifa() {
    }

    public Tarifa(double costoBase, double costoPorKm) {
        this.costoBase = costoBase;
        this.costoPorKm = costoPorKm;
    }

    public double getCostoBase() { return costoBase; }
    public void setCostoBase(double costoBase) { this.costoBase = costoBase; }

    public double getCostoPorKm() { return costoPorKm; }
    public void setCostoPorKm(double costoPorKm) { this.costoPorKm = costoPorKm; }

    public double calcularCosto(double distanciaKm) {
        return costoBase + (costoPorKm * distanciaKm);
    }
}
