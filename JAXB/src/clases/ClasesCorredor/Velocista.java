package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nombre", "fechaNacimiento", "velocidadMedia", "historial"})
public class Velocista extends Corredor {
    @XmlElement(name="velocidad_media")
    private float velocidadMedia;

    public Velocista(String codigo, String nombre, String equipo, int dorsal, LocalDate fecha, List<Puntuacion> historial, float velocidad) {
        super();
        this.velocidadMedia = velocidad;
    }

    public Velocista(String codigo, String nombre, String equipo, int dorsal, LocalDate fecha, float velocidad) {
        super();
        this.velocidadMedia = velocidad;
    }

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
