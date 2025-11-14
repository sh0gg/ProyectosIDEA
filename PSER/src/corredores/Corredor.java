package corredores;

import java.util.Random;

public class Corredor extends Thread {

    private final Carrera carrera;
    private final int calle;
    private final Random random = new Random();

    public Corredor(String nombre, Carrera carrera, int calle) {
        super(nombre);
        this.carrera = carrera;
        this.calle = calle;
    }

    @Override
    public void run() {

        // Esperar a que el juez dé la salida
        carrera.esperarSalida(getName(), calle);

        int distancia = carrera.getDistanciaMetros();
        int numVallas = distancia / 10; // una valla cada 10 m

        for (int i = 1; i <= numVallas; i++) {
            // Simulamos el paso de una valla con un pequeño retraso aleatorio
            int retrasoMs = random.nextInt(41) + 10; // entre 10 y 50 ms aprox.
            try {
                Thread.sleep(retrasoMs);
            } catch (InterruptedException e) {
                // Ignoramos
            }

            if (Carrera.VER_DETALLE) {
                System.out.println(getName() + " (calle " + calle + ") ha pasado la valla " + i + " / " + numVallas);
            }
        }

        long llegada = System.currentTimeMillis();
        carrera.registrarLlegada(getName(), calle, llegada);
    }
}
