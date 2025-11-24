package clases;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Reservas"})
@XmlRootElement(name = "Habitacion")
public class Habitacion extends Alojamiento {

    @XmlAttribute(name = "numero")
    private String numero;

    @XmlAttribute(name = "estado")
    private String estado;

    @XmlAttribute(name = "precio")
    private float precio;

    @XmlAttribute(name = "tipo")
    private String tipo;

    @XmlElementWrapper(name = "Reservas")
    @XmlElement(name = "Reserva")
    private List<Reserva> reservaElem;

    public Habitacion() {
    }

    public Habitacion(String numero, String estado, float precio, String tipo, List<Reserva> reservaElem) {
        super(numero, estado, precio);
        this.tipo = tipo;
        this.reservaElem = reservaElem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
