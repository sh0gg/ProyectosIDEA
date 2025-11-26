package hilos.corredores;

import java.util.Random;

public class Main {

    public static final int NUM_CALLES = 8;

    public static void main(String[] args) {

        Random r = new Random();

        // 50% carrera masculina 110m, 50% femenina 100m
        boolean masculina = r.nextBoolean();
        int distancia = masculina ? 110 : 100;

        System.out.println("========================================");
        System.out.println("Simulación de carrera de vallas");
        System.out.println("Prueba: " + (masculina ? "Masculina 110m" : "Femenina 100m"));
        System.out.println("Número de calles: " + NUM_CALLES);
        System.out.println("========================================\n");

        Carrera carrera = new Carrera(distancia, NUM_CALLES);

        // Hilo juez
        Juez juez = new Juez(carrera);
        juez.start();

        // Hilos corredores (uno por calle)
        Corredor[] corredores = new Corredor[NUM_CALLES];
        for (int i = 0; i < NUM_CALLES; i++) {
            String nombre = "Corredor_" + (i + 1);
            corredores[i] = new Corredor(nombre, carrera, i + 1);
            corredores[i].start();
        }

        // Esperamos a que todos los corredores acaben
        for (int i = 0; i < NUM_CALLES; i++) {
            try {
                corredores[i].join();
            } catch (InterruptedException e) {
                // Ignoramos
            }
        }

        // (Opcional) Esperar también al juez
        try {
            juez.join();
        } catch (InterruptedException e) {
            // Ignoramos
        }

        // Mostrar clasificación final
        carrera.mostrarResultados();
    }
}
