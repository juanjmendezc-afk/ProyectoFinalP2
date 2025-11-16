package co.edu.uniquindio.proyectofinalp2.estructural;



public abstract class EnvioDecorator implements CostoExtra {

    protected CostoExtra wrappee;

    public EnvioDecorator(CostoExtra wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double aplicar(double costoBase) {
        return wrappee.aplicar(costoBase);
    }
}

