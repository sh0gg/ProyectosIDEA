package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Equipo {
    @XmlAttribute(name = "id", required = true)
    String idEquipo;

    @XmlElement(name = "nombre", required = true)
    String nombre;

    @XmlElement(name= "patrocinadores", required = true)
    private Patrocinadores patrocinadores;

    public Equipo() {}

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

    public void setIdEquipo(String idEquipo) {}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {}



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
