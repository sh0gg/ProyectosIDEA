package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Representa el elemento:
 *
 * <Empresa puesto="Director">LaColmena</Empresa>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Empresa {
    @XmlAttribute(name = "puesto")
    private String puesto;

    @XmlValue
    private String nombre;

    public Empresa() {
    }

    public Empresa(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
