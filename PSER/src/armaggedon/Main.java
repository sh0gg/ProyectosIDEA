package armaggedon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static final int NUMERO_METEORITOS = 10;
    public static ArrayList<Meteorito> meteoritos = new ArrayList<>();

    public static final int NUMERO_ARMAGGEDONES = 5;
    public static ArrayList<Armaggedon> armaggedones = new ArrayList<>();

    public static final int NUMERO_BOMBEROS = 3;
    public static ArrayList<BomberoSurtidor> bomberos = new ArrayList<>();

    private static final Random rnd = new Random();

    static synchronized Meteorito asignarMeteorito() {
        ArrayList<Meteorito> vivos = new ArrayList<>();
        for (Meteorito m : meteoritos) {
            if (!m.isExplotado()) vivos.add(m);
        }
        if (vivos.isEmpty()) return null;
        return vivos.get(rnd.nextInt(vivos.size()));
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < NUMERO_METEORITOS; i++) {
            meteoritos.add(new Meteorito(i));
        }
        for (int i = 0; i < NUMERO_ARMAGGEDONES; i++) {
            armaggedones.add(new Armaggedon(i));
        }
        for (int i = 0; i < NUMERO_BOMBEROS; i++) {
            bomberos.add(new BomberoSurtidor(i));
        }

        for (Thread t : armaggedones) t.start();
        for (Thread t : bomberos) t.start();

        for (Thread t : armaggedones)
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
        for (Thread t : bomberos)
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
    }
}
