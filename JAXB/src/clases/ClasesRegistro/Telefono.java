package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Elemento individual:
 * <Telefono tipo="movil">111111111</Telefono>
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Telefono {
    @XmlAttribute(name = "tipo")
    private String tipo;

    @XmlValue
    private String numero;

    public Telefono() {
    }

    public Telefono(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
