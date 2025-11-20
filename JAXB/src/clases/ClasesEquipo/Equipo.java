package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

/**
 * Representa un <equipo> del XML equipos.xml.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipo {
    @XmlAttribute(name = "id", required = true)
    private String idEquipo;
    @XmlElement(name = "nombre", required = true)
    private String nombre;
    @XmlElement(name = "patrocinadores", required = true)
    private Patrocinadores patrocinadores;

    /**
     * Constructor vacío para JAXB.
     */
    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String idEquipo, String nombreEquipo) {
        this.idEquipo = idEquipo;
        this.nombre = nombreEquipo;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Patrocinadores getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(Patrocinadores patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipo{");
        sb.append("idEquipo=").append(idEquipo);
        sb.append(", nombreEquipo='").append(nombre).append('\'');
        sb.append(", patrocinadores=").append(patrocinadores);
        return sb.append("}").toString();
    }
}
