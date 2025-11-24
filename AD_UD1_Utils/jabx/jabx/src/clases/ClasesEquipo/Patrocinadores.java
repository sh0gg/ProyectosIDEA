package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;


import java.util.HashSet;
import java.util.Set;

/**
 * Contenedor de varios <patrocinador>.
 * Mapea al elemento <patrocinadores numPatrocinadores="..."> del XML.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Patrocinadores {
    @XmlAttribute(name = "numPatrocinadores", required = true)
    private int numPatrocinadores;
    @XmlElement(name = "patrocinador", required = true)
    private Set<Patrocinador> patrocinadores = new HashSet<>();

    public Patrocinadores() {
    }

    public int getNumPatrocinadores() {
        return numPatrocinadores;
    }

    public void setNumPatrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }

    public Set<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(Set<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores != null ? patrocinadores : new HashSet<>();
// opcional: sincronizar el atributo con el tamaño del set
        this.numPatrocinadores = this.patrocinadores.size();
    }

    /**
     * Método de ayuda para añadir un patrocinador y actualizar el contador.
     */
    public void addPatrocinador(Patrocinador p) {
        if (p != null) {
            this.patrocinadores.add(p);
            this.numPatrocinadores = this.patrocinadores.size();
        }
    }

    @Override
    public String toString() {
        return "Patrocinadores{" +
                "numPatrocinadores=" + numPatrocinadores +
                ", patrocinadores=" + patrocinadores +
                '}';
    }
}
