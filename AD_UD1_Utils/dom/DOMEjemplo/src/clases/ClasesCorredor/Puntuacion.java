package clases.ClasesCorredor;

/**
 * Puntuación de un corredor en un año concreto.
 */
public class Puntuacion implements Comparable<Puntuacion> {

    private int anio;
    private float puntos;

    public Puntuacion(int anio, float puntos) {
        this.anio = anio;
        this.puntos = puntos;
    }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public float getPuntos() { return puntos; }
    public void setPuntos(float puntos) { this.puntos = puntos; }

    @Override
    public int compareTo(Puntuacion o) {
        return Integer.compare(this.anio, o.anio);
    }

    @Override
    public String toString() {
        return "{" + anio + ": " + puntos + "}";
    }
}
