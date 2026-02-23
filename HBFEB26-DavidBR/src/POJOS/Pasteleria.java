package POJOS;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

// Autor: David Besada Ramilo (53612286E)

public class Pasteleria implements Serializable {

    private int codigo;    
    private String nome;
    private String categoria;
    private String localidade;
    private Pastelero dueno;
    private Set<Producto> productos = new HashSet<>();

    public Pasteleria() {
    }

    public Pasteleria(int codigo, String nome, String categoria, String localidade, Pastelero dueno) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.localidade = localidade;
        this.dueno = dueno;
    }

    public Pasteleria(int codigo, String nome, String categoria, String localidade, Pastelero dueno, Set<Producto> productos) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.localidade = localidade;
        this.dueno = dueno;
        this.productos = productos;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Pastelero getDueno() {
        return dueno;
    }

    public void setDueno(Pastelero dueno) {
        this.dueno = dueno;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

   

  
}
