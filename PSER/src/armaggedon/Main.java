package armaggedon;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static final int NUMERO_METEORITOS = 10;
    public static ArrayList<Meteorito> meteoritos  = new ArrayList<>();

    public static final int NUMERO_ARMAGGEDONES = 5;
    public static ArrayList<Armaggedon> armaggedones = new ArrayList<>();

    public static final int NUMERO_BOMBEROS = 3;
    public static ArrayList<BomberoSurtidor> bomberos = new ArrayList<>();

    static Meteorito asignarMeteorito();

    public static void main(String[] args) throws IOException {

        // CREAMOS LA ESCENA

        for (int i = 0; i < NUMERO_METEORITOS; i++) {
            meteoritos.add(new Meteorito(i));
        }
        for (int i = 0; i < NUMERO_ARMAGGEDONES; i++) {
            armaggedones.add(new Armaggedon(i));
        }
        for (int i = 0; i < NUMERO_BOMBEROS; i++) {
            bomberos.add(new BomberoSurtidor(i));
        }

        // AHORA DEJAMOS QUE SE MATEN
    }


}
