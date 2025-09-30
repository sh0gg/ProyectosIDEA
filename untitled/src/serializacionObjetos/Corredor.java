package serializacionObjetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Corredor implements Serializable {
    int dorsal;
    String nombre;
    LocalDate fechaNacimiento;
    int equipo;
    ArrayList<Puntuacion> puntuaciones;

    public Corredor(int dorsal, String nombre, LocalDate fechaNacimiento, int equipo) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.equipo = equipo;
    }

    public void addPuntuacion(Puntuacion puntuacion) {
        this.puntuaciones.add(puntuacion);
    }
}