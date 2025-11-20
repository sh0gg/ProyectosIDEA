package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * Clase base abstracta para los tipos de contacto:
 * - Telefonos
 * - Email
 * <p>
 * No tiene campos propios, solo sirve para el polimorfismo en Persona.
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Telefonos.class, Email.class})
@XmlTransient
public abstract class Contacto {
// No tiene nada, las hijas definen sus propios campos.
}