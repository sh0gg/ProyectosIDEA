package hilos.ejercicio1;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static Random rnd = new Random();
    private static ArrayList<Persona> trabajadores = new ArrayList<>();
    private static final int NUM_TABIQUES = 25;
    private static final int NUM_PINTORES = 10;
    private static final int TIEMPO_EJECUCION = 10000;
    private static final int TIEMPO_DESCANSO = rnd.nextInt(300);
    private static final Buffer buffer = new Buffer();

    public static void main(String[] args) throws InterruptedException {

        // CREAR CASA
        Casa casa = new Casa(NUM_TABIQUES);
        int[] numTabiques = Casa.getTabiques();

        // CREAR PERSONAS
        // elejir fotografo
        int numFotografo = rnd.nextInt(NUM_PINTORES+1);

        for (int i = 0; i < NUM_PINTORES+1 ; i++) {
            if (numFotografo == i) {
                Persona fotografo = new Fotografo(buffer, numTabiques.length);
                trabajadores.add(fotografo);
            } else {
                Persona pintor = new Pintor(buffer, i, numTabiques.length, TIEMPO_DESCANSO);
                trabajadores.add(pintor);
            }
        }

        // entrada aleatorio
        java.util.Collections.shuffle(trabajadores);
        for (Persona persona : trabajadores) {
            Thread.sleep(50);
            persona.start();
        }

        Thread tiempoEjecucion = new Thread(() -> {
            try {
                Thread.sleep(TIEMPO_EJECUCION);
                // alguna forma de interrumpir el main, no se me ocurre.
            } catch (InterruptedException e) {}
        });

    }

    public static class Buffer {
        private boolean estaOcupada = false;

        public synchronized void entraFotografo(Fotografo fotografo) {
            estaOcupada = true;
            int[] foto = Casa.getTabiques();
            fotografo.setFoto(foto);
            int color = foto[0];
            for (int j : foto) {
                if (j != color) {
                    System.out.println("LOS COLORES NO COINCIDEN!!");
                }
            }
            buffer.saleAlguien(fotografo);
        }

        public synchronized void entraPintor(Pintor pintor) throws InterruptedException {
            estaOcupada = true;
            int[] tabiques = pintor.getTabiques();
            for (int i = 0; i < tabiques.length; i++) {
                tabiques[i] = pintor.getColor();
                System.out.println("Pintor " + pintor.threadId() + " - Pinto de " + pintor.getColor());
                this.wait(pintor.getTiempoDescanso());
                System.out.println("Voy a esperar " + pintor.getTiempoDescanso() + " a que seque...");
            }
            Casa.setTabiques(tabiques);
            buffer.saleAlguien(pintor);
        }

        public synchronized void saleAlguien(Persona p) {
            estaOcupada = false;
            notifyAll();
        }

        public synchronized boolean isOcupada() {
            return estaOcupada;
        }
    }
}
