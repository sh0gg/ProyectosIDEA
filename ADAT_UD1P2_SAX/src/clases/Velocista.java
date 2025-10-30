package clases;

import java.time.LocalDate;

public class Velocista extends Corredor {
    private static final long serialVersionUID = 1L;
    private float velocidadMediaKmH;

    public Velocista(String nombre, LocalDate fechaNacimiento, int equipoId, float velocidadMediaKmH) {
        super(nombre, fechaNacimiento, equipoId);
        this.velocidadMediaKmH = velocidadMediaKmH;
    }

    public Velocista() {
        super();
        velocidadMediaKmH = 0;
    }

    public float getVelocidadMediaKmH() { return velocidadMediaKmH; }
    @Override public String getTipo() { return "Velocista"; }
    @Override public String toString(){ return super.toString() + " vMed=" + velocidadMediaKmH + "km/h"; }

    public void setVelocidadMedia(float v) {
        this.velocidadMediaKmH = v;
    }
}
