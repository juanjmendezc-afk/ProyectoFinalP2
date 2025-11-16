package co.edu.uniquindio.proyectofinalp2.estructural;

public class EmpaqueEspecialDecorator extends EnvioDecorator {

    public EmpaqueEspecialDecorator(CostoExtra wrappee) {
        super(wrappee);
    }

    @Override
    public double aplicar(double costoBase) {
        return super.aplicar(costoBase) + 8000;
    }
}
