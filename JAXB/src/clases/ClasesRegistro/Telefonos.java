package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el bloque:
 *
 * <Telefonos>
 * <Telefono tipo="movil">111111111</Telefono>
 * <Telefono tipo="fijo">222222222</Telefono>
 * </Telefonos>
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Telefonos extends Contacto {
    @XmlElement(name = "Telefono")
    private List<Telefono> telefonos = new ArrayList<>();

    public Telefonos() {
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos != null ? telefonos : new ArrayList<>();
    }

    public void addTelefono(Telefono t) {
        if (t != null) {
            this.telefonos.add(t);
        }
    }
}