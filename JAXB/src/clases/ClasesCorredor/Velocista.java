package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase que representa a un Velocista.
 * Está pensada para ser usada dentro del elemento raíz <corredores>.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nombre", "fechaNacimiento", "velocidadMedia", "historial"})
public class Velocista extends Corredor {
    @XmlElement(name = "velocidad_media")
    private float velocidadMedia;

    /**
     * Constructor completo usado en la lógica del programa.
     * IMPORTANTE: ahora sí llama al constructor de la superclase para
     * rellenar código, dorsal, nombre, equipo y fecha.
     */
    public Velocista(String codigo,
                     String nombre,
                     String equipo,
                     int dorsal,
                     LocalDate fecha,
                     List<Puntuacion> historial,
                     float velocidad) {
// Inicializamos los campos heredados de Corredor
        super(codigo, dorsal, nombre, equipo, fecha, historial);
        this.velocidadMedia = velocidad;
    }

    /**
     * Constructor sin historial, por si lo quieres usar para crear
     * corredores que todavía no tienen puntuaciones.
     */
    public Velocista(String codigo,
                     String nombre,
                     String equipo,
                     int dorsal,
                     LocalDate fecha,
                     float velocidad) {
        super(codigo, nombre, equipo, dorsal, fecha);
        this.velocidadMedia = velocidad;
    }

    /**
     * Constructor vacío obligatorio para JAXB.
     */
    public Velocista() {
    }


    public float getVelocidadMedia() {
        return velocidadMedia;
    }

    public void setVelocidadMedia(float velocidadMedia) {
        this.velocidadMedia = velocidadMedia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(" | VELOCIDAD MEDIA: ");
        sb.append(String.format("%.2f km/h", this.velocidadMedia));

        return sb.toString();
    }


}
