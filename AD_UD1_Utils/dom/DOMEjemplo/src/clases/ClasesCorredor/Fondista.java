package clases.ClasesCorredor;

import java.time.LocalDate;

/**
 * Fondista: corredor de larga distancia.
 * Atributo extra: distanciaMax (km).
 */
public class Fondista extends Corredor {

    private float distanciaMax; // km

    public Fondista() {}

    public Fondista(String codigo, int dorsal, String nombre,
                    LocalDate fechaNacimiento, String equipo,
                    float distanciaMax) {
        super(codigo, dorsal, nombre, fechaNacimiento, equipo);
        this.distanciaMax = distanciaMax;
    }

    public float getDistanciaMax() { return distanciaMax; }
    public void setDistanciaMax(float distanciaMax) { this.distanciaMax = distanciaMax; }

    @Override
    public String toString() {
        return "Fondista{" +
                "codigo='" + codigo + '\'' +
                ", dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo='" + equipo + '\'' +
                ", distanciaMax=" + distanciaMax +
                ", historial=" + historial +
                '}';
    }
}
