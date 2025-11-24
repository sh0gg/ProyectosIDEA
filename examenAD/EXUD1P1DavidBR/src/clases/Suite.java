package clases;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Reservas"})
@XmlRootElement(name = "Suite")
public class Suite extends Alojamiento {

    @XmlAttribute(name = "numero")
    private String numero;

    @XmlAttribute(name = "estado")
    private String estado;

    @XmlAttribute(name = "precio")
    private float precio;

    @XmlAttribute(name = "terraza")
    private String terraza;

    @XmlAttribute(name = "categoria")
    private String categoria;

    @XmlElementWrapper(name = "Reservas")
    @XmlElement(name = "Reserva")
    private List<Reserva> reservaElem;

    public Suite() {
    }

    public Suite(String numero, String estado, float precio, String terraza, String categoria, List<Reserva> reservaElem) {
        super(numero, estado, precio);
        this.terraza = terraza;
        this.categoria = categoria;
        this.reservaElem = reservaElem;
    }

    public String getTerraza() {
        return terraza;
    }

    public void setTerraza(String terraza) {
        this.terraza = terraza;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
