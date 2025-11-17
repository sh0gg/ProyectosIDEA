package ejercicio1;

public class Pintor extends Persona {
    private final int color;
    private int[] tabiques;
    private int tiempoDescanso;


    public Pintor(Main.Buffer buffer, int color, int numTabiques, int tiempoDescanso) {
        super(buffer);
        this.color = color;
        this.tabiques = new int[numTabiques];
        this.tiempoDescanso = tiempoDescanso;
    }

    public synchronized void trabaja() throws InterruptedException {
        buffer.entraPintor(this);
    }

    public int[] getTabiques() {
        return tabiques;
    }

    public int getColor() {
        return color;
    }

    public int getTiempoDescanso() {
        return tiempoDescanso;
    }
}
