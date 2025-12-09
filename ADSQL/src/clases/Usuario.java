package clases;

import java.sql.Date;

public class Usuario {
    int id;
    String nombre;
    String apellido;
    String email;
    Date fechaNacimiento;
    String ciudad;
    String pais;
    double saldo;

    public Usuario(int id, String nombre, String apellido, String email, Date fechaNacimiento, String ciudad, String pais, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.pais = pais;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " " + apellido +
                " | " + email +
                " | " + fechaNacimiento +
                " | " + ciudad + ", " + pais +
                " | Saldo: " + saldo;
    }
}
