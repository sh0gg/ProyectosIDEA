package clases;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Alojamientos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alojamientos {
    @XmlElements({
            @XmlElement(name = "Habitacion", type = Habitacion.class),
            @XmlElement(name = "Suite", type = Suite.class)
    })

    private List<Alojamiento> lista = new ArrayList<>();

    public Alojamientos() {
    }

    public List<Alojamiento> getLista() {
        return lista;
    }

    public void setLista(List<Alojamiento> lista) {
        this.lista = lista;
    }
}