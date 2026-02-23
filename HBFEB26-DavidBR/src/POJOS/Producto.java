package POJOS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// Autor: David Besada Ramilo (53612286E)

public class Producto implements Serializable {

    private int codigo;
    private String nome;
    private String descripcion;
    private Float precio;
    private String categoria;
    private Set<Pasteleria> pastelerias = new HashSet<>();

    public Producto() {
    }

    public Producto(int codigo, String nome, String descripcion, Float precio, String categoria, Set<Pasteleria> pastelerias) {
        this.codigo = codigo;
        this.nome = nome;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.pastelerias = pastelerias;
    }

    public Set<Pasteleria> getPastelerias() {
        return pastelerias;
    }

    public void setPastelerias(Set<Pasteleria> pastelerias) {
        this.pastelerias = pastelerias;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

   
}
