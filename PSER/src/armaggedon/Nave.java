package armaggedon;

import static armaggedon.Main.*;
import static armaggedon.Meteorito.*;

public class Nave extends Thread{
    int id;
    Meteorito meteorito;

    public Nave(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        if (!meteoritos.isEmpty()) {
            irMeteorito(meteoritoAleatorio);
        } else  {
            System.out.println("Nave " + id + ": *###* ¡Objetivos destruidos, regresamos a casa! Cambio y corto. *###*");
        }
    }


}
