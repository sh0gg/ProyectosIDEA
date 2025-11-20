package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.LocalDateAdapterRegistro;

import java.time.LocalDate;

/**
 * Clase abstracta base para Trabajador y Estudiante.
 * No se genera etiqueta <Persona>, solo <Trabajador> y <Estudiante>.
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Trabajador.class, Estudiante.class})
@XmlTransient
public abstract class Persona {
    @XmlElement(name = "Nombre")
    private String nombre;

    @XmlElement(name = "FechaNacimiento")
    @XmlJavaTypeAdapter(LocalDateAdapterRegistro.class)
    private LocalDate fechaNacimiento;

    /**
     * Contacto polimórfico:
     * - <Telefonos> ... </Telefonos> -> clase Telefonos
     * - <Email>texto</Email> -> clase Email
     * Solo debe aparecer uno de los dos en cada Persona.
     */
    @XmlElements({@XmlElement(name = "Telefonos", type = Telefonos.class), @XmlElement(name = "Email", type = Email.class)})
    private Contacto contacto;

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
}
