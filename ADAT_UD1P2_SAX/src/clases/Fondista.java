package clases;

import java.time.LocalDate;

public class Fondista extends Corredor {
    private static final long serialVersionUID = 1L;
    private float distanciaMaxKm;

    public Fondista(String nombre, LocalDate fechaNacimiento, int equipoId, float distanciaMaxKm) {
        super(nombre, fechaNacimiento, equipoId);
        this.distanciaMaxKm = distanciaMaxKm;
    }

    public Fondista() {
        super();
        distanciaMaxKm = 0;
    }

    public float getDistanciaMaxKm() { return distanciaMaxKm; }
    @Override public String getTipo() { return "Fondista"; }
    @Override public String toString(){ return super.toString() + " distMax=" + distanciaMaxKm + "km"; }

    public void setDistanciaMaxima(float v) {
        this.distanciaMaxKm = v;
    }
}
