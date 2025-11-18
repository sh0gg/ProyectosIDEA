package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="equipos")
@XmlAccessorType(XmlAccessType.FIELD) // coge las prioridades
public class Equipos {
    @XmlElement(name = "equipo", required = true)
    List<Equipo> equipo = new ArrayList<>();

    public Equipos() {}

    public Equipos(List<Equipo> lista) {
        this.equipo = lista != null ? lista : new ArrayList<>();
    }

    public List<Equipo> getEquipo() {
        return equipo;
    }
    public void setEquipos(List<Equipo> equipos) {
        this.equipo = equipos;
    }

    @Override
    public String toString() {
        return "Equipos{" + "equipo=" + equipo + '}';
    }

}
