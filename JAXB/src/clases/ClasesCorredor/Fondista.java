package clases.ClasesCorredor;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"nombre", "fechaNacimiento", "distanciaMax", "historial"})
public class Fondista extends Corredor {
    @XmlElement(name="distancia_max")
    private float distanciaMax;

    public Fondista(String codCorredor, String nombre, String equipo, int dorsal, LocalDate fecha, List<Puntuacion> historial, float distancia) {
        super();
        this.distanciaMax = distancia;
    }



    public Fondista(String codigo, String nombre, String equipo, int dorsal, LocalDate fecha, float distancia) {
        super();
        this.distanciaMax = distancia;
    }

    public Fondista() {

    }

    public float getDistanciaMax() {
        return distanciaMax;
    }

    public void setDistanciaMax(float distanciaMax) {
        this.distanciaMax = distanciaMax;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" | DISTANCIA MAX: ");
        sb.append(String.format("%.3f km", this.distanciaMax));

        return sb.toString();
    }


}
