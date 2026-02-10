public class Granada extends ArmaDecorator {
    public Granada(Soldado soldado) {
        super(soldado);
    }

    @Override
    public String getDescripcion() {
        return soldado.getDescripcion() + " + Granada";
    }

    @Override
    public int getDanio() {
        return soldado.getDanio() + 80;
    }
}