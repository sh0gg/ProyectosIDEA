// Decoradores concretos
public class Rifle extends ArmaDecorator {
    public Rifle(Soldado soldado) {
        super(soldado);
    }

    @Override
    public String getDescripcion() {
        return soldado.getDescripcion() + " + Rifle";
    }

    @Override
    public int getDanio() {
        return soldado.getDanio() + 50;
    }
}
