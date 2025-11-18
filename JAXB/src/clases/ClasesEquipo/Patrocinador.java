package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Patrocinador {
    @XmlValue
    static String nombre;

    @XmlAttribute(name = "donacion", required = true)
    static float donacion;

    @XmlAttribute(name = "fecha_inicio", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    static LocalDate fechaInicio;

    public Patrocinador() {}

    public Patrocinador(String nombrePatrocinador, float donacion, LocalDate fechaInicio) {
        this.nombre = nombrePatrocinador;
        this.donacion = donacion;
        this.fechaInicio = fechaInicio;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Patrocinador.nombre = nombre;
    }

    public static float getDonacion() {
        return donacion;
    }

    public static void setDonacion(float donacion) {
        Patrocinador.donacion = donacion;
    }

    public static LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public static void setFechaInicio(LocalDate fechaInicio) {
        Patrocinador.fechaInicio = fechaInicio;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patrocinador that = (Patrocinador) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }


    @Override
    public String toString() {
        return String.format("%s | Donación: %.2f | Inicio: %s",
                nombre,
                donacion,
                fechaInicio);
    }


}
