package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Representa un <Estudiante> del XML.
 *
 * <Estudiante>
 *  <Nombre>...</Nombre>
 *  <FechaNacimiento>...</FechaNacimiento>
 *  <Email>...</Email> o <Telefonos>...</Telefonos>
 *  <Universidad>...</Universidad>
 *  <Carrera>...</Carrera>
 * </Estudiante>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Estudiante extends Persona {
    @XmlElement(name = "Universidad")
    private String universidad;

    @XmlElement(name = "Carrera")
    private String carrera;

    public Estudiante() {
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
