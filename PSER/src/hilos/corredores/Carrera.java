package hilos.corredores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrera {

    private final int distanciaMetros;
    private final int numCalles;

    private boolean salidaDada = false;
    private long tiempoSalida;

    private final List<Resultado> resultados = new ArrayList<Resultado>();

    // Para activar/desactivar mensajes de detalle
    public static final boolean VER_DETALLE = true;

    public Carrera(int distanciaMetros, int numCalles) {
        this.distanciaMetros = distanciaMetros;
        this.numCalles = numCalles;
    }

    public int getDistanciaMetros() {
        return distanciaMetros;
    }

    public int getNumCalles() {
        return numCalles;
    }

    // Llamado por cada corredor al inicio: espera a que el juez dispare
    public synchronized void esperarSalida(String nombreCorredor, int calle) {
        if (VER_DETALLE) {
            System.out.println(nombreCorredor + " (calle " + calle + ") sale de la cámara de llamadas y se coloca en los tacos...");
        }

        while (!salidaDada) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Ignoramos para este ejercicio
            }
        }
    }

    // Llamado por el juez para dar la salida
    public synchronized void darSalida() {
        if (salidaDada) return; // por seguridad

        System.out.println("\n¡Preparados... listos... PUM! (disparo del juez)");
        salidaDada = true;
        tiempoSalida = System.currentTimeMillis();
        notifyAll(); // despierta a todos los corredores
    }

    public synchronized long getTiempoSalida() {
        return tiempoSalida;
    }

    // Llamado por cada corredor cuando termina la carrera
    public synchronized void registrarLlegada(String nombreCorredor, int calle, long tiempoLlegadaMs) {
        long tiempoEmpleado = tiempoLlegadaMs - tiempoSalida; // en ms
        Resultado res = new Resultado(nombreCorredor, calle, tiempoEmpleado);
        resultados.add(res);

        if (VER_DETALLE) {
            System.out.printf("%s (calle %d) ha terminado la carrera en %.3f segundos\n",
                    nombreCorredor, calle, tiempoEmpleado / 1000.0);
        }
    }

    // Al final, mostrar la clasificación
    public void mostrarResultados() {
        List<Resultado> copia;

        synchronized (this) {
            copia = new ArrayList<Resultado>(resultados);
        }

        Collections.sort(copia); // ordena por tiempo

        System.out.println("\n========================================");
        System.out.println("RESULTADOS FINALES");
        System.out.println("========================================");

        int puesto = 1;
        for (Resultado r : copia) {
            System.out.printf("%dº - %s (calle %d) - tiempo: %.3f s\n",
                    puesto++, r.getNombreCorredor(), r.getCalle(), r.getTiempoSegundos());
        }
    }
}
