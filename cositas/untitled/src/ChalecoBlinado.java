public class ChalecoBlinado extends ArmaDecorator {
    public ChalecoBlinado(Soldado soldado) {
        super(soldado);
    }

    @Override
    public String getDescripcion() {
        return soldado.getDescripcion() + " + Chaleco blindado";
    }

    @Override
    public int getDanio() {
        return soldado.getDanio(); // no añade daño, pero podría añadir defensa
    }
}