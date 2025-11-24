package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base abstracta para corredores.
 * JAXB realmente serializa las subclases (Velocista / Fondista).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Velocista.class, Fondista.class})
public abstract class Corredor {

    // Estos se anotan en las subclases como atributos XML

    protected String codigo;
    protected int dorsal;
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String equipo;

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

    // getters/setters

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

    public void addOrUpdatePuntuacion(Puntuacion p) {
        for (int i = 0; i < historial.size(); i++) {
            if (historial.get(i).getAnio() == p.getAnio()) {
                historial.set(i, p);
                return;
            }
        }
        historial.add(p);
        historial.sort(null);
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
