package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Representa el contacto por email:
 *
 * <Email>correo@example.com</Email>
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Email extends Contacto {
    @XmlValue
    private String direccion;

    public Email() {
    }

    public Email(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}