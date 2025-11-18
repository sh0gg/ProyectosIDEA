package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.LocalDateAdapter;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Velocista.class, Fondista.class})
@XmlType(propOrder = {"nombre", "fechaNacimiento", "historial"})
@XmlTransient // se asegura de que <Corredor> no se genere como etiqueta propia
public abstract class Corredor {
    @XmlAttribute(name = "codigo", required = true)
   private String codigo;

    @XmlAttribute(name = "dorsal", required = true)
    private int dorsal;

    @XmlAttribute(name = "equipo", required = true)
    private String equipo;

    @XmlElement(name = "nombre", required = true)
    private String nombre;

    @XmlElement(name = "fecha_nacimiento", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;

    @XmlElementWrapper(name = "historial")
    @XmlElement(name = "puntuacion")
    private List<Puntuacion> historial;



    public Corredor(String codCorredor, int dorsal, String nombre, String equipo, LocalDate fecha, List<Puntuacion> historial) {
        this.codigo = codCorredor;
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.fechaNacimiento = fecha;
        this.historial = historial;
    }

    public Corredor(String codigo, String nombre, String equipo, int dorsal, LocalDate fecha) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.equipo = equipo;
        this.dorsal = dorsal;
        this.fechaNacimiento = fecha;
    }

    public Corredor() {

    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Puntuacion> getHistorial() {
        return historial;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }



    public final void setHistorial(List<Puntuacion> historial) {
        if (historial != null)
            historial.sort(Comparator.comparingInt(Puntuacion::getAnio));

        this.historial = historial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" | NOMBRE: ").append(this.nombre);
        sb.append(" | FECHA NACIMIENTO: ").append(this.fechaNacimiento);
        sb.append(" | EQUIPO: ").append(this.equipo);

        return sb.toString();
    }
}