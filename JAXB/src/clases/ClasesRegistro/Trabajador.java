package clases.ClasesRegistro;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Representa un <Trabajador> del XML.
 *
 * <Trabajador>
 * <Nombre>...</Nombre>
 * <FechaNacimiento>...</FechaNacimiento>
 * <Telefonos>... o <Email>...</Email>
 * <Empresa puesto="...">NombreEmpresa</Empresa>
 * <Salario>...</Salario>
 * </Trabajador>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Trabajador extends Persona {
    @XmlElement(name = "Empresa")
    private Empresa empresa;

    @XmlElement(name = "Salario")
    private double salario;

    public Trabajador() {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
