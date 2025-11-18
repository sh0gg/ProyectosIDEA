package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Telefonos.class, Email.class})
public abstract class Contacto {
    // no tiene nada, se va directamente a las clases hijas
}
