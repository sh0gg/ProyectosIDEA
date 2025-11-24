package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.persistenciaJAXB.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nombre", "fechaNacimiento", "velocidadMedia", "historial"})
@XmlRootElement(name = "velocista")
public class Velocista extends Corredor {

    @XmlAttribute(name = "codigo")
    private String codAttr;  // sombra para el atributo XML

    @XmlAttribute(name = "dorsal")
    private Integer dorsalAttr;

    @XmlAttribute(name = "equipo")
    private String equipoAttr;

    @XmlElement(name = "velocidad_media")
    private float velocidadMedia;

    @XmlElement(name = "nombre")
    private String nombreElem;

    @XmlElement(name = "fecha_nacimiento")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaElem;

    @XmlElementWrapper(name = "historial")
    @XmlElement(name = "puntuacion")
    private java.util.List<Puntuacion> historialElem;

    public Velocista() {}

    public Velocista(String codigo, int dorsal, String nombre,
                     LocalDate fechaNacimiento, String equipo,
                     float velocidadMedia) {
        super(codigo, dorsal, nombre, fechaNacimiento, equipo);
        this.velocidadMedia = velocidadMedia;
    }

    // getters/setters específicos

    public float getVelocidadMedia() { return velocidadMedia; }
    public void setVelocidadMedia(float velocidadMedia) { this.velocidadMedia = velocidadMedia; }

    // Sincronizar campos de apoyo con los heredados al marshalling/unmarshalling

    @SuppressWarnings("unused")
    private void afterUnmarshal() {
        // de atributos/elementos a campos base
        this.codigo = codAttr;
        this.dorsal = dorsalAttr != null ? dorsalAttr : 0;
        this.equipo = equipoAttr;
        this.nombre = nombreElem;
        this.fechaNacimiento = fechaElem;
        if (historialElem != null) this.historial = historialElem;
    }

    @SuppressWarnings("unused")
    private void beforeMarshal() {
        // de campos base a atributos/elementos
        this.codAttr = this.codigo;
        this.dorsalAttr = this.dorsal;
        this.equipoAttr = this.equipo;
        this.nombreElem = this.nombre;
        this.fechaElem = this.fechaNacimiento;
        this.historialElem = this.historial;
    }

    @Override
    public String toString() {
        return "Velocista{" +
                "codigo='" + codigo + '\'' +
                ", dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo='" + equipo + '\'' +
                ", velocidadMedia=" + velocidadMedia +
                ", historial=" + historial +
                '}';
    }
}
