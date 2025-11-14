package corredores;

import java.util.Random;

public class Juez extends Thread {

    private final Carrera carrera;
    private final Random random = new Random();

    public Juez(Carrera carrera) {
        super("Juez");
        this.carrera = carrera;
    }

    @Override
    public void run() {
        System.out.println("El juez se toma un tiempecito en preparar la pistola...");

        // Simulamos el tiempo de preparación de la pistola (0.5 a 2 segundos)
        int preparacionMs = 500 + random.nextInt(1501); // 500..2000 ms
        try {
            Thread.sleep(preparacionMs);
        } catch (InterruptedException e) {
            // Ignoramos
        }

        carrera.darSalida();
    }
}
