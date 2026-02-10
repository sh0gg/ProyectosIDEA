// Implementación concreta base
public class SoldadoBase implements Soldado {
    private String nombre;

    public SoldadoBase(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getDescripcion() {
        return "Soldado " + nombre;
    }

    @Override
    public int getDanio() {
        return 10; // daño con puños
    }

    @Override
    public void atacar() {
        System.out.println(getDescripcion() + " ataca causando " + getDanio() + " de daño");
    }
}