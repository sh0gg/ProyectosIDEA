public class Cuchillo extends ArmaDecorator {
    public Cuchillo(Soldado soldado) {
        super(soldado);
    }

    @Override
    public String getDescripcion() {
        return soldado.getDescripcion() + " + Cuchillo";
    }

    @Override
    public int getDanio() {
        return soldado.getDanio() + 25;
    }
}
