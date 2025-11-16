package co.edu.uniquindio.proyectofinalp2.estructural;

public class SeguroDecorator extends EnvioDecorator {

    public SeguroDecorator(CostoExtra wrappee) {
        super(wrappee);
    }

    @Override
    public double aplicar(double costoBase) {
        return super.aplicar(costoBase) + 5000;
    }
}
