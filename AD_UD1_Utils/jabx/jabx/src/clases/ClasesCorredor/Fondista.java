package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.persistenciaJAXB.LocalDateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fondista")
@XmlType(propOrder = {"nombre", "fechaNacimiento", "distanciaMax", "historial"})
public class Fondista extends Corredor {

    @XmlAttribute(name = "codigo")
    private String codigo;

    @XmlAttribute(name = "dorsal")
    private int dorsal;

    @XmlAttribute(name = "equipo")
    private String equipo;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "fecha_nacimiento")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;

    @XmlElement(name = "distancia_max")
    private float distanciaMax;

    @XmlElementWrapper(name = "historial")
    @XmlElement(name = "puntuacion")
    private List<Puntuacion> historial = new ArrayList<>();

    public Fondista() {}

    public Fondista(String codigo, int dorsal, String nombre,
                    LocalDate fechaNacimiento, String equipo,
                    float distanciaMax) {
        this.codigo = codigo;
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.equipo = equipo;
        this.distanciaMax = distanciaMax;
    }

    public float getDistanciaMax() { return distanciaMax; }
    public void setDistanciaMax(float distanciaMax) { this.distanciaMax = distanciaMax; }

    // getters/setters de los demás campos (si los necesitas en código)

    @Override
    public String toString() {
        return "Fondista{" +
                "codigo='" + codigo + '\'' +
                ", dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo='" + equipo + '\'' +
                ", distanciaMax=" + distanciaMax +
                ", historial=" + historial +
                '}';
    }
}
