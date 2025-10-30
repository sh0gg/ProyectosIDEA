package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Patrocinador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;       // único dentro del equipo
    private float donacion;
    private LocalDate fechaInicio;

    public Patrocinador(String nombre, float donacion, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.donacion = donacion;
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() { return nombre; }
    public float getDonacion() { return donacion; }
    public void setDonacion(float d) { this.donacion = d; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate f) { this.fechaInicio = f; }

    @Override public boolean equals(Object o){ return (o instanceof Patrocinador p) && Objects.equals(p.nombre, nombre); }
    @Override public int hashCode(){ return Objects.hash(nombre); }
    @Override public String toString(){ return nombre + " (" + donacion + "€, desde " + fechaInicio + ")"; }
}
