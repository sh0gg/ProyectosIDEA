package clases;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Reserva")
public class Reserva {

    @XmlAttribute(name = "codigo")
    private String codigo;

    @XmlElement(name = "Usuario")
    private Usuario usuario;

    @XmlElement(name = "FechaInicio")
    private LocalDate fechaInicio;

    @XmlElement(name = "FechaFin")
    private LocalDate fechaFin;

    public Reserva() {
    }

    public Reserva(Reserva reserva) {
        this.codigo = reserva.codigo;
        this.usuario = reserva.usuario;
        this.fechaInicio = reserva.fechaInicio;
        this.fechaFin = reserva.fechaFin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
