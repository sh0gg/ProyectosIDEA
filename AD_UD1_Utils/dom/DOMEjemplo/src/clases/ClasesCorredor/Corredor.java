package clases.ClasesCorredor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base Corredor (sin Serializable, todo pensado para XML DOM).
 * Atributos comunes a fondistas y velocistas.
 */
public abstract class Corredor {

    // El ID principal ahora es el "codigo" del XML (atributo ID)
    protected String codigo;          // ej: "C01"
    protected int dorsal;             // clave alternativa
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String equipo;          // aquí guardamos solo el código del equipo (E1, E2, ...)

    // Historial de puntuaciones por año, ordenado por anio
    protected List<Puntuacion> historial = new ArrayList<>();

    public Corredor() {}

    public Corredor(String codigo, int dorsal, String nombre,
                    LocalDate fechaNacimiento, String equipo) {
        this.codigo = codigo;
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.equipo = equipo;
    }

    // ======= Getters / Setters básicos =======
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getDorsal() { return dorsal; }
    public void setDorsal(int dorsal) { this.dorsal = dorsal; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEquipo() { return equipo; }
    public void setEquipo(String equipo) { this.equipo = equipo; }

    public List<Puntuacion> getHistorial() { return historial; }
    public void setHistorial(List<Puntuacion> historial) { this.historial = historial; }

    // Añade o actualiza una puntuación manteniendo orden por año
    public void addOrUpdatePuntuacion(Puntuacion p) {
        for (int i = 0; i < historial.size(); i++) {
            if (historial.get(i).getAnio() == p.getAnio()) {
                historial.set(i, p); // mismo año → se actualiza
                return;
            }
        }
        historial.add(p);
        historial.sort(null); // usa compareTo de Puntuacion (por año)
    }

    public boolean eliminarPuntuacionPorAnio(int anio) {
        return historial.removeIf(p -> p.getAnio() == anio);
    }

    @Override
    public String toString() {
        return "Corredor{" +
                "codigo='" + codigo + '\'' +
                ", dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo='" + equipo + '\'' +
                ", historial=" + historial +
                '}';
    }
}
