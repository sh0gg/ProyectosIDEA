package armaggedon;

import java.util.concurrent.ThreadLocalRandom;

public class Nave extends Thread {
    int id;

    public Nave(int id) {
        this.id = id;
    }

    protected Meteorito irMeteoritoAleatorio() {
        log("solicitando meteorito...");
        Meteorito m = Main.asignarMeteorito();
        if (m != null) {
            log("en ruta al meteorito " + m.getId());
        }
        return m;
    }

    protected void dormirAleatorio(int min, int max) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(min, max + 1));
        } catch (InterruptedException ignored) {
        }
    }

    protected void mensajeFin() {
        System.out.println("Nave " + id + ": *###* ¡Objetivos destruidos, regresamos a casa! Cambio y corto. *###*");
    }

    protected void log(String msg) {
        System.out.println("Nave " + id + ": " + msg);
    }
}
