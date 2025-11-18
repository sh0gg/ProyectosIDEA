package clases.ClasesCorredor;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="corredores")
@XmlAccessorType(XmlAccessType.FIELD) // coge las prioridades
public class Corredores {

    @XmlElements({
            @XmlElement(name = "velocista", type = Velocista.class),
            @XmlElement(name = "fondista", type = Fondista.class),
    })
    List<Corredor> lista = new ArrayList<>();

    
    public Corredores() {
      lista = new ArrayList<>();
    }

    public List<Corredor> getLista() {
        return lista;
    }


}
