package hilos.corredores;

public class Resultado implements Comparable<Resultado> {

    private final String nombreCorredor;
    private final int calle;
    private final long tiempoMs;

    public Resultado(String nombreCorredor, int calle, long tiempoMs) {
        this.nombreCorredor = nombreCorredor;
        this.calle = calle;
        this.tiempoMs = tiempoMs;
    }

    public String getNombreCorredor() {
        return nombreCorredor;
    }

    public int getCalle() {
        return calle;
    }

    public long getTiempoMs() {
        return tiempoMs;
    }

    public double getTiempoSegundos() {
        return tiempoMs / 1000.0;
    }

    @Override
    public int compareTo(Resultado otro) {
        // Orden ascendente por tiempo
        if (this.tiempoMs < otro.tiempoMs) return -1;
        if (this.tiempoMs > otro.tiempoMs) return 1;
        return 0;
    }
}
