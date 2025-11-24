package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.persistenciaJAXB.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un <patrocinador> dentro de <patrocinadores>.
 * Ejemplo en XML:
 * <patrocinador donacion="1500.0" fecha_inicio="2023-01-15">Nike</patrocinador>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Patrocinador {
    // Texto entre etiquetas: nombre del patrocinador
    @XmlValue
    private String nombre;
    // Atributo donacion="..."
    @XmlAttribute(name = "donacion", required = true)
    private float donacion;
    // Atributo fecha_inicio="yyyy-MM-dd"
    @XmlAttribute(name = "fecha_inicio", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaInicio;

    /**
     * Constructor vacío para JAXB.
     */
    public Patrocinador() {
    }

    public Patrocinador(String nombrePatrocinador, float donacion, LocalDate fechaInicio) {
        this.nombre = nombrePatrocinador;
        this.donacion = donacion;
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDonacion() {
        return donacion;
    }

    public void setDonacion(float donacion) {
        this.donacion = donacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Igualamos patrocinadores por nombre para poder usar Set y evitar duplicados.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patrocinador that)) return false;
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
