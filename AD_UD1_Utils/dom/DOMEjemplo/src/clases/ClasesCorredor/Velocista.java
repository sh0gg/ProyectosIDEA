package clases.ClasesCorredor;

import java.time.LocalDate;

/**
 * Velocista: corredor de corta distancia.
 * Atributo extra: velocidadMedia (km/h).
 */
public class Velocista extends Corredor {

    private float velocidadMedia; // km/h

    public Velocista() {}

    public Velocista(String codigo, int dorsal, String nombre,
                     LocalDate fechaNacimiento, String equipo,
                     float velocidadMedia) {
        super(codigo, dorsal, nombre, fechaNacimiento, equipo);
        this.velocidadMedia = velocidadMedia;
    }

    public float getVelocidadMedia() { return velocidadMedia; }
    public void setVelocidadMedia(float velocidadMedia) { this.velocidadMedia = velocidadMedia; }

    @Override
    public String toString() {
        return "Velocista{" +
                "codigo='" + codigo + '\'' +
                ", dorsal=" + dorsal +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo='" + equipo + '\'' +
                ", velocidadMedia=" + velocidadMedia +
                ", historial=" + historial +
                '}';
    }
}
