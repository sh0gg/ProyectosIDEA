package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import persistencia.CategoriasAdapter;
import persistencia.LocalDateAdapterRegistro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase raíz que representa el elemento <Registro> del XML.
 * <p>
 * Ejemplo:
 * <Registro version="1.0" fechaCreacion="17-11-25">
 * <Categorias>Estudios Trabajo Deporte</Categorias>
 * <Personas>
 * ...
 * </Personas>
 * </Registro>
 */
@XmlRootElement(name = "Registro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Registro {
    @XmlAttribute(name = "version")
    private String version;

    @XmlAttribute(name = "fechaCreacion")
    @XmlJavaTypeAdapter(LocalDateAdapterRegistro.class)
    private LocalDate fechaCreacion;

    @XmlElement(name = "Categorias")
    @XmlJavaTypeAdapter(CategoriasAdapter.class)
    private List<String> categorias = new ArrayList<>();

    @XmlElementWrapper(name = "Personas")
    @XmlElements({
            @XmlElement(name = "Trabajador", type = Trabajador.class),
            @XmlElement(name = "Estudiante", type = Estudiante.class)
    })

    private List<Persona> personas = new ArrayList<>();

    public Registro() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias != null ? categorias : new ArrayList<>();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas != null ? personas : new ArrayList<>();
    }

    public void addPersona(Persona p) {
        if (p != null) {
            this.personas.add(p);
        }
    }
}
