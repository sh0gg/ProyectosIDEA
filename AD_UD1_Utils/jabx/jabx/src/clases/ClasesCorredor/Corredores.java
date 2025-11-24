package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "corredores")
@XmlAccessorType(XmlAccessType.FIELD)
public class Corredores {

    @XmlElements({
            @XmlElement(name = "velocista", type = Velocista.class),
            @XmlElement(name = "fondista", type = Fondista.class)
    })
    private List<Corredor> lista = new ArrayList<>();

    public Corredores() {}

    public List<Corredor> getLista() {
        return lista;
    }

    public void setLista(List<Corredor> lista) {
        this.lista = lista;
    }
}
