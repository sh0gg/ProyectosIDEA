package hilos.steaks;

import java.util.Arrays;
import java.util.Random;

/**
 * EJERCICIO TIPO EXAMEN: Pintores y Fotógrafo con Monitor + Timeout
 *
 * Idea:
 * - Hay un "muro" con N tabiques.
 * - Hay P pintores. Cada pintor pinta tabiques (asigna su color) de uno en uno.
 * - Hay un fotógrafo que espera a que el muro esté COMPLETO y entonces hace una foto (copia del array).
 * - Además, hay un hilo "Timeout" que, pasado X ms, cancela todo y fuerza una salida ordenada.
 *
 * Conceptos que se evalúan:
 * - Sección crítica y exclusión mutua: synchronized sobre un monitor compartido.
 * - Coordinación: wait/notifyAll.
 * - Regla: wait SIEMPRE en while.
 * - Interrupciones y finalización limpia (sin stop()).
 * - "Cerrar main con timeout": en realidad, cancelas hilos y esperas join.
 */
public class examenLike {

    public static void main(String[] args) throws InterruptedException {
        int numTabiques = 12;
        int numPintores = 3;

        // Timeout elegido por ti (ms). Cambia este valor para practicar.
        long timeoutMs = 3500;

        Muro muro = new Muro(numTabiques);

        // Creamos pintores (hilos)
        Pintor[] pintores = new Pintor[numPintores];
        for (int i = 0; i < numPintores; i++) {
            // Color = i+1, descanso aleatorio para simular velocidad distinta
            pintores[i] = new Pintor("Pintor-" + (i + 1), muro, i + 1, 150, 450);
        }

        // Creamos fotógrafo (hilo)
        Fotografo fotografo = new Fotografo("Fotografo", muro);

        // Hilo Timeout: pasado timeoutMs cancela el sistema
        TimeoutCierre timeout = new TimeoutCierre("Timeout", muro, timeoutMs);

        // IMPORTANTE (pregunta típica): start() lanza hilos; run() NO.
        for (Pintor p : pintores) p.start();
        fotografo.start();
        timeout.start();

        // Esperamos a que el fotógrafo termine (foto hecha) O a que el timeout cancele.
        // join() sin tiempo: main queda WAITING hasta que termine el hilo.
        fotografo.join();

        // En este punto, o bien:
        // - fotógrafo hizo foto -> ordenamos parada suave
        // - o timeout canceló -> fotógrafo salió por cancelación

        // Pedimos parada global (idempotente: si ya estaba cancelado, no pasa nada)
        muro.cancelar("Main: fin (foto o timeout)");

        // Esperamos a que terminen todos los hilos (salida ordenada)
        for (Pintor p : pintores) p.join();
        timeout.join();

        // Resultado final
        System.out.println("\n--- ESTADO FINAL ---");
        System.out.println("Muro: " + Arrays.toString(muro.snapshotMuro()));
        System.out.println("Foto: " + Arrays.toString(fotografo.getFoto()));
        System.out.println("Cancelado: " + muro.estaCancelado());
        System.out.println("FIN MAIN");
    }

    /**
     * MONITOR (Recurso compartido)
     * Aquí está la sección crítica y la coordinación con wait/notifyAll.
     */
    static class Muro {
        private final int[] tabiques; // 0 = sin pintar, >0 = color del pintor
        private boolean fotoHecha = false;
        private boolean cancelado = false;

        Muro(int n) {
            this.tabiques = new int[n];
        }

        /**
         * Un pintor intenta pintar un tabique no pintado.
         * Devuelve true si pintó algo, false si no hay trabajo o si está cancelado/foto hecha.
         */
        public synchronized boolean pintarSiguiente(int color, String nombreHilo) throws InterruptedException {
            // Si ya no hay que trabajar, salimos rápido.
            if (cancelado || fotoHecha) return false;

            int idx = buscarTabiqueLibre();
            if (idx == -1) {
                // No hay tabiques libres: si está completo, despertamos al fotógrafo
                // y dejamos que este método devuelva false (ya no hay trabajo).
                notifyAll();
                return false;
            }

            // Pintamos (sección crítica protegida)
            tabiques[idx] = color;
            System.out.printf("%s pinta tabique %d con color %d -> %s%n",
                    nombreHilo, idx, color, Arrays.toString(tabiques));

            // Avisamos al resto (fotógrafo incluido) de que el estado cambió
            notifyAll();
            return true;
        }

        /**
         * El fotógrafo espera a que el muro esté completo, o a cancelación.
         * Devuelve una copia del muro cuando está completo.
         */
        public synchronized int[] esperarMuroCompletoYHacerFoto(String nombreHilo) throws InterruptedException {
            // REGLA DE ORO: wait SIEMPRE en while, nunca en if
            while (!cancelado && !fotoHecha && !estaCompleto()) {
                // Mientras no esté completo, el fotógrafo espera
                wait();
            }

            if (cancelado) {
                System.out.println(nombreHilo + " sale sin foto (cancelado).");
                return null;
            }

            // Si llegamos aquí, está completo y no cancelado
            int[] foto = Arrays.copyOf(tabiques, tabiques.length);
            fotoHecha = true;
            System.out.println(nombreHilo + " HACE FOTO -> " + Arrays.toString(foto));

            // Avisamos para que pintores salgan si estaban esperando/iterando
            notifyAll();
            return foto;
        }

        public synchronized void cancelar(String motivo) {
            if (!cancelado) {
                cancelado = true;
                System.out.println("CANCELACIÓN GLOBAL: " + motivo);
            }
            // Despertamos a cualquiera que esté en wait()
            notifyAll();
        }

        public synchronized boolean estaCancelado() {
            return cancelado;
        }

        public synchronized boolean estaCompleto() {
            for (int v : tabiques) if (v == 0) return false;
            return true;
        }

        public synchronized int[] snapshotMuro() {
            return Arrays.copyOf(tabiques, tabiques.length);
        }

        private int buscarTabiqueLibre() {
            for (int i = 0; i < tabiques.length; i++) {
                if (tabiques[i] == 0) return i;
            }
            return -1;
        }
    }

    /**
     * Pintor: intenta pintar repetidamente.
     * Observa el patrón correcto: bucle + descanso + manejo de interrupción.
     */
    static class Pintor extends Thread {
        private final Muro muro;
        private final int color;
        private final int descansoMinMs;
        private final int descansoMaxMs;
        private final Random rnd = new Random();

        Pintor(String name, Muro muro, int color, int descansoMinMs, int descansoMaxMs) {
            super(name);
            this.muro = muro;
            this.color = color;
            this.descansoMinMs = descansoMinMs;
            this.descansoMaxMs = descansoMaxMs;
        }

        @Override
        public void run() {
            try {
                while (!muro.estaCancelado()) {
                    boolean pinto = muro.pintarSiguiente(color, getName());
                    if (!pinto) {
                        // Si no hay nada que pintar, salimos.
                        // (En otros ejercicios podrías esperar, pero aquí no hace falta.)
                        break;
                    }

                    // Descanso simulando que tarda en pintar
                    Thread.sleep(descansoAleatorio());
                }
            } catch (InterruptedException e) {
                // Buen patrón: si te interrumpen, sales ordenadamente
                System.out.println(getName() + " interrumpido, sale.");
            } finally {
                System.out.println(getName() + " termina.");
            }
        }

        private int descansoAleatorio() {
            return descansoMinMs + rnd.nextInt(Math.max(1, descansoMaxMs - descansoMinMs + 1));
        }
    }

    /**
     * Fotógrafo: espera muro completo y guarda una foto.
     */
    static class Fotografo extends Thread {
        private final Muro muro;
        private volatile int[] foto; // volatile para visibilidad (lectura segura desde main)

        Fotografo(String name, Muro muro) {
            super(name);
            this.muro = muro;
        }

        @Override
        public void run() {
            try {
                foto = muro.esperarMuroCompletoYHacerFoto(getName());
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrumpido, sale.");
            } finally {
                System.out.println(getName() + " termina.");
            }
        }

        public int[] getFoto() {
            return foto;
        }
    }

    /**
     * Hilo de timeout: pasado X ms cancela todo.
     *
     * Esto es lo que te pedían con “otro hilo paralelo” que, llegado un tiempo elegido,
     * cierre el main. En Java, no “matas main”: fuerzas que los hilos no-daemon terminen
     * (con una cancelación global) y haces join desde main.
     */
    static class TimeoutCierre extends Thread {
        private final Muro muro;
        private final long timeoutMs;

        TimeoutCierre(String name, Muro muro, long timeoutMs) {
            super(name);
            this.muro = muro;
            this.timeoutMs = timeoutMs;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeoutMs);
                // Pasado el tiempo, cancelamos el sistema
                muro.cancelar(getName() + ": timeout de " + timeoutMs + " ms alcanzado");
            } catch (InterruptedException e) {
                // Si lo interrumpen, simplemente sale
            } finally {
                System.out.println(getName() + " termina.");
            }
        }
    }
}
