package A3UD1_DavidBesadaRamilo.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Puntuacion implements Serializable, Comparable<Puntuacion> {
    private static final long serialVersionUID = 1L;
    private int anio;
    private float puntos;

    public Puntuacion(int anio, float puntos) {
        this.anio = anio;
        this.puntos = puntos;
    }

    public int getAnio() { return anio; }
    public float getPuntos() { return puntos; }
    public void setPuntos(float puntos) { this.puntos = puntos; }

    @Override public int compareTo(Puntuacion o) { return Integer.compare(this.anio, o.anio); }
    @Override public boolean equals(Object o){ return (o instanceof Puntuacion p) && p.anio==anio; }
    @Override public int hashCode(){ return Objects.hash(anio); }
    @Override public String toString(){ return anio + ": " + puntos + " pts"; }
}
