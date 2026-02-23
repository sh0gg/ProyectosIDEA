package POJOS;

import java.util.HashSet;
import java.util.Set;

/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */

public class Publicacion  implements java.io.Serializable {


     private int codpublicacion;
     private String titulo;
     private String editorial;
     private Double precio;
     private String tipo;
     private EdicionPublicacion cocipubli;
     private Integer anoedicion;
     private Set<Cocinero> cocineros=new HashSet();
     
     

    public Publicacion() {
    }

 
   

    public int getCodpublicacion() {
        return codpublicacion;
    }

    public void setCodpublicacion(int codpublicacion) {
        this.codpublicacion = codpublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EdicionPublicacion getCocipubli() {
        return cocipubli;
    }

    public void setCocipubli(EdicionPublicacion cocipubli) {
        this.cocipubli = cocipubli;
    }

    

    public Integer getAnoedicion() {
        return anoedicion;
    }

    public void setAnoedicion(Integer anoedicion) {
        this.anoedicion = anoedicion;
    }

    public Set<Cocinero> getCocineros() {
        return cocineros;
    }

    public void setCocineros(Set<Cocinero> cocineros) {
        this.cocineros = cocineros;
    }

    

}


