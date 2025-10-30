package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public abstract class Corredor implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int dorsal;
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected int equipoId;
    protected Puntuacion[] puntuaciones;

    protected Corredor(String nombre, LocalDate fechaNacimiento, int equipoId) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.equipoId = equipoId;
        this.puntuaciones = new Puntuacion[0];
    }

    public Corredor() {
        this.nombre = "";
        this.fechaNacimiento = null;
        this.equipoId = 0;
        this.puntuaciones = new Puntuacion[0];
    }

    public int getDorsal() { return dorsal; }
    public void setDorsal(int dorsal) { this.dorsal = dorsal; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public int getEquipoId() { return equipoId; }
    public void setEquipoId(int equipoId) { this.equipoId = equipoId; }
    public Puntuacion[] getPuntuaciones() { return puntuaciones; }

    public void addOrUpdatePuntuacion(int anio, float puntos) {
        for (Puntuacion p : puntuaciones) {
            if (p.getAnio() == anio) {
                p.setPuntos(puntos);
                Arrays.sort(puntuaciones);
                return;
            }
        }
        Puntuacion[] nuevo = Arrays.copyOf(puntuaciones, puntuaciones.length + 1);
        nuevo[nuevo.length - 1] = new Puntuacion(anio, puntos);
        Arrays.sort(nuevo);
        puntuaciones = nuevo;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("#%d %s (%s) Equipo:%d Puntuaciones:%s",
                dorsal, nombre, getTipo(), equipoId, Arrays.toString(puntuaciones));
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
    }
}
