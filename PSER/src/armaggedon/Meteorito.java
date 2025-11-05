package armaggedon;

import static armaggedon.Main.NUMERO_METEORITOS;

public class Meteorito {
    int id;

    public Meteorito(int id) {
        this.id = id;
    }

    static int meteoritoAleatorio = (int) (Math.random() * NUMERO_METEORITOS);
}
