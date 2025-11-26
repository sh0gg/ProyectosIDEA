package hilos.ejercicio1;

public class Fotografo extends Persona {

    private int[] foto;

    public Fotografo(Main.Buffer buffer, int numTabiques) {
        super(buffer);
        foto = new int[numTabiques];
    }

    public synchronized void trabaja() {
        buffer.entraFotografo(this);
    }

    public void setFoto(int[] foto) {
        this.foto = foto;
    }
}
