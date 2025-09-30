package serializacionObjetos;

import java.time.LocalDate;

public class Fondista extends Corredor {
    float distanciaMax;

    public Fondista(int dorsal, String nombre, LocalDate fechaNacimiento, int equipo, float distanciaMax) {
        super(dorsal, nombre, fechaNacimiento, equipo);
        this.distanciaMax = distanciaMax;
    }
}
