package hilos.steaks;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Ejercicio modelo: Venta de coches (concurrencia con monitor + wait/notifyAll)
 *
 * - 50 clientes (hilos) intentan comprar como máximo 1 coche.
 * - Hay stock: 10 León, 7 Ibiza, 3 Ateca (cada unidad es un "Coche" distinto).
 * - Un coche solo puede estar siendo probado por 1 cliente a la vez.
 * - Cada vez que un cliente prueba un coche, incrementa visitas.
 * - Probabilidad de compra = visitas% (si visitas=12 -> 12%).
 * - Al final se imprime qué coches se vendieron y a qué cliente.
 * - VER_DETALLE muestra trazas durante la simulación.
 */
public class VentaCoches {

    static final boolean VER_DETALLE = true; // ponlo a false para salida limpia
    static final int NUM_CLIENTES = 50;
    static final int MAX_MS_PRUEBA = 20;

    // ----------- MODELO -----------
    enum Modelo { LEON, IBIZA, ATECA }

    static class Coche {
        final Modelo modelo;
        final int numero;       // p.e. León7
        int visitas = 0;        // protegido por el monitor del concesionario
        boolean enPrueba = false;
        boolean vendido = false;
        String comprador = null;

        Coche(Modelo modelo, int numero) {
            this.modelo = modelo;
            this.numero = numero;
        }

        String etiqueta() {
            return modelo.name().charAt(0) + modelo.name().substring(1).toLowerCase() + numero;
            // Leon7 / Ibiza3 / Ateca2
        }
    }

    /**
     * Monitor central: aquí se decide quién prueba qué coche.
     * Importante: wait() SIEMPRE en while.
     */
    static class Concesionario {
        private final List<Coche> coches = new ArrayList<>();
        private int cochesDisponibles; // no vendidos

        Concesionario() {
            // Cargamos stock (unidades numeradas)
            addModelo(Modelo.LEON, 10);
            addModelo(Modelo.IBIZA, 7);
            addModelo(Modelo.ATECA, 3);
            cochesDisponibles = coches.size();
        }

        private void addModelo(Modelo m, int unidades) {
            for (int i = 1; i <= unidades; i++) coches.add(new Coche(m, i));
        }

        /**
         * El cliente pide "cita": se le asigna un coche libre para probar.
         * Devuelve null si ya no queda nada a la venta (condición de parada).
         */
        public synchronized Coche pedirCita(String cliente) throws InterruptedException {
            // Si no queda nada, el cliente debe terminar.
            if (cochesDisponibles == 0) return null;

            // Mientras haya coches en venta pero ninguno libre ahora, espero.
            while (cochesDisponibles > 0 && buscarCocheLibre() == null) {
                wait();
            }

            // Puede que durante la espera se agotase el stock:
            if (cochesDisponibles == 0) return null;

            // Asigno coche libre y lo marco "en prueba"
            Coche c = buscarCocheLibre();
            c.enPrueba = true;
            if (VER_DETALLE) {
                System.out.println(cliente + " recibe cita para probar " + c.etiqueta() +
                        " (visitas actuales=" + c.visitas + ")");
            }
            return c;
        }

        /**
         * El cliente devuelve el coche tras probarlo.
         * - Siempre se llama cuando el cliente termina de probar.
         * - Si compra, se marca vendido y se reduce el stock disponible.
         * - notifyAll() para despertar a clientes esperando coche libre.
         */
        public synchronized void devolverTrasPrueba(Coche c, String cliente, boolean compra) {
            // Incremento de visitas: cada prueba cuenta como visita
            c.visitas++;

            if (compra && !c.vendido) {
                c.vendido = true;
                c.comprador = cliente;
                cochesDisponibles--;
                if (VER_DETALLE) System.out.println(cliente + " COMPRA " + c.etiqueta()
                        + " (visitas=" + c.visitas + "% prob)");
            } else {
                if (VER_DETALLE) System.out.println(cliente + " NO compra " + c.etiqueta()
                        + " (visitas=" + c.visitas + "% prob)");
            }

            // Libero el coche para otro cliente (si no se vendió, queda en venta)
            c.enPrueba = false;

            // Muy importante: avisar a los que estaban esperando coche libre
            notifyAll();
        }

        private Coche buscarCocheLibre() {
            for (Coche c : coches) {
                if (!c.vendido && !c.enPrueba) return c;
            }
            return null;
        }

        public synchronized boolean hayStock() {
            return cochesDisponibles > 0;
        }

        public synchronized List<Coche> resumenFinal() {
            return new ArrayList<>(coches);
        }
    }

    // ----------- HILOS -----------
    static class Cliente extends Thread {
        private final Concesionario concesionario;

        Cliente(String nombre, Concesionario c) {
            super(nombre);
            this.concesionario = c;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Pide un coche para probar (puede bloquear en wait())
                    Coche coche = concesionario.pedirCita(getName());
                    if (coche == null) break; // no queda stock -> fin

                    // Prueba fuera del synchronized (paralelismo real)
                    Thread.sleep(ThreadLocalRandom.current().nextInt(MAX_MS_PRUEBA + 1));

                    // Decide compra:
                    // Probabilidad = visitas% (pero OJO: según enunciado, se fijan en el cartelito
                    // "nº de visitas anteriores". Eso es coche.visitas ANTES de incrementarla por esta prueba.
                    int visitasAnteriores;
                    synchronized (concesionario) { // lectura segura de visitas antes de devolver
                        visitasAnteriores = coche.visitas;
                    }
                    int prob = Math.min(100, visitasAnteriores); // cap a 100 por seguridad
                    boolean compra = ThreadLocalRandom.current().nextInt(100) < prob;

                    // Devuelve el coche y, si compra, termina su hilo (solo 1 coche)
                    concesionario.devolverTrasPrueba(coche, getName(), compra);
                    if (compra) break;
                }
            } catch (InterruptedException ignored) {
                // Si el examen pide interrupciones, aquí terminaría ordenado
            }
        }
    }

    // ----------- MAIN -----------
    public static void main(String[] args) throws InterruptedException {
        Concesionario concesionario = new Concesionario();

        List<Thread> clientes = new ArrayList<>();
        for (int i = 1; i <= NUM_CLIENTES; i++) {
            Thread t = new Cliente("cliente" + i, concesionario);
            clientes.add(t);
            t.start();
        }

        // Esperar fin de todos los clientes
        for (Thread t : clientes) t.join();

        // Mostrar únicamente productos y qué clientes lo adquirieron
        System.out.println("\n--- RESUMEN FINAL (vendidos) ---");
        List<Coche> all = concesionario.resumenFinal();
        all.stream()
                .filter(c -> c.vendido)
                .forEach(c -> System.out.println(c.comprador + " compró " + c.etiqueta()));

        System.out.println("\n--- RESUMEN FINAL (no vendidos) ---");
        all.stream()
                .filter(c -> !c.vendido)
                .forEach(c -> System.out.println(c.etiqueta() + " NO vendido (visitas=" + c.visitas + ")"));

        System.out.println("\nFIN");
    }
}
