package clases.ClasesEquipo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;


import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
public class Patrocinadores {
    @XmlAttribute(name = "numPatrocinadores", required = true)
    public int numPatrocinadores;

    @XmlElement(name = "patrocinador", required = true)
    private Set<Patrocinador> patrocinadores = new HashSet<>();


    public Patrocinadores() {}


    public int getNumPatrocinadores() {
        return numPatrocinadores;
    }

    public void setNumPatrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }
}
