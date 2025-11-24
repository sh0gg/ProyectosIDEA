package clases;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Habitacion.class, Suite.class})
@XmlTransient
public abstract class Alojamiento {
    @XmlAttribute(name = "numero")
    protected String numero;
    @XmlAttribute(name = "estado")
    protected String estado;
    @XmlAttribute(name = "precio")
    protected float precio;

    @XmlElementWrapper(name = "Reservas")
    @XmlElement(name = "Reserva")
    protected List<Reserva> Reservas = new ArrayList<>();

    public Alojamiento() {
    }

    public Alojamiento(String numero, String estado, float precio) {
        this.numero = numero;
        this.estado = estado;
        this.precio = precio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Reserva> getReservas() {
        return Reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        Reservas = reservas;
    }
}
