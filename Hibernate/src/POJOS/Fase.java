package POJOS;

import java.io.Serializable;

public class Fase implements Serializable {
    private String nombre;
    private String estado;

    public Fase() {
    }

    public Fase(String nombre, String estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
