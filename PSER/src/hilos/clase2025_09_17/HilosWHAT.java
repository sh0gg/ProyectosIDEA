package hilos.clase2025_09_17;

import java.util.concurrent.atomic.AtomicInteger;

public class HilosWHAT {

    // DATOS
    public static final int RONDAS = 10;
    public static final int CONTARHASTA = 1000;

    static final int[] wins = new int[RONDAS];


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Vamos a probar hilos");

        for (int i = 0; i < RONDAS; i++) {
            System.out.println("PRUEBA " + i);

            AtomicInteger winner = new AtomicInteger(0); // 0 = nadie ganó aún

            Thread h1 = new Thread(() -> {
                for (int j = 0; j < CONTARHASTA; j++) {
                    System.out.println("Hilo 1: " + j);
                }
                winner.compareAndSet(0, 1);
            });

            Thread h2 = new Thread(() -> {
                for (int j = 0; j < CONTARHASTA; j++) {
                    System.out.println("Hilo 2: " + j);
                }
                winner.compareAndSet(0, 2);
            });

            Thread h3 = new Thread(() -> {
                for (int j = 0; j < CONTARHASTA; j++) {
                    System.out.println("Hilo 3: " + j);
                }
                winner.compareAndSet(0, 3);
            });

            Thread h4 = new Thread(() -> {
                for (int j = 0; j < CONTARHASTA; j++) {
                    System.out.println("Hilo 4: " + j);
                }
                winner.compareAndSet(0, 4);
            });

            h1.start();
            h2.start();
            h3.start();
            h4.start();

            h1.join();
            h2.join();
            h3.join();
            h4.join();

            int ganador = winner.get();
            System.out.println("Ganó el hilo " + ganador);
            wins[i] = ganador;
        }

        for (int i = 0; i < RONDAS; i++) {
            System.out.print(wins[i] + ", ");
        }
    }
}
