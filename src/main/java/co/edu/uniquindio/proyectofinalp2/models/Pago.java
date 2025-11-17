package co.edu.uniquindio.proyectofinalp2.models;

public class Pago {

    private String id;
    private double monto;
    private String metodo;

    public Pago() {
    }

    public Pago(String id, double monto, String metodo) {
        this.id = id;
        this.monto = monto;
        this.metodo = metodo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public String getMetodo() {
        return metodo;
    }
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
