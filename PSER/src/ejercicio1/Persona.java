package ejercicio1;

public abstract class Persona extends Thread {
    protected final Main.Buffer buffer;

    public Persona(Main.Buffer buffer) {
        this.buffer = buffer;
    }

    public abstract void trabaja() throws InterruptedException;

    @Override
    public void run() {
        try {
            trabaja();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


