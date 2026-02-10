// Decorador abstracto
public abstract class ArmaDecorator implements Soldado {
    protected Soldado soldado;

    public ArmaDecorator(Soldado soldado) {
        this.soldado = soldado;
    }

    @Override
    public String getDescripcion() {
        return soldado.getDescripcion();
    }

    @Override
    public int getDanio() {
        return soldado.getDanio();
    }

    @Override
    public void atacar() {
        // Usar this.getDescripcion() y this.getDanio(), no delegar
        System.out.println(getDescripcion() + " ataca causando " + getDanio() + " de daño");
    }
}