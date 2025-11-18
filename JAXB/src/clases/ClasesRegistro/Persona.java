package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.LocalDateAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Trabajador.class,Estudiante.class})
@XmlTransient // recuerda poner esto en las abstractas
public abstract class Persona {
    @XmlElement(name = "Nombre")
    private String nombre;

    @XmlElement(name = "FechaNacimiento")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;

    @XmlElements({
            @XmlElement(name = "Telefonos",type = Telefonos.class),
            @XmlElement(name = "Email", type = Email.class)
    })
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
