package clases;

import java.time.LocalDate;

public class Fotografia {
    private String titulo;
    private LocalDate fechaToma;
    private String tipo;
    private double tamanoMB;

    public Fotografia(String titulo, LocalDate fechaToma, String tipo, double tamanoMB) {
        this.titulo = titulo;
        this.fechaToma = fechaToma;
        this.tipo = tipo;
        this.tamanoMB = tamanoMB;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaToma() {
        return fechaToma;
    }

    public void setFechaToma(LocalDate fechaToma) {
        this.fechaToma = fechaToma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTamanoMB() {
        return tamanoMB;
    }

    public void setTamanoMB(double tamanoMB) {
        this.tamanoMB = tamanoMB;
    }

    @Override
    public String toString() {
        return titulo + ", " + fechaToma + ", " + tipo + ", " + tamanoMB;
    }
}
