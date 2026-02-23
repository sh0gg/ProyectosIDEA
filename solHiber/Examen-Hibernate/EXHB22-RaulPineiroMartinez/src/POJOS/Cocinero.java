package POJOS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */
public class Cocinero implements java.io.Serializable {

    private int codigo;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Character sexo;
    private String apodo;
    
    //1A
    private Contactococinero contacto;
    private Collection<Premio> premios = new ArrayList();
    private Collection<Publicacion> publicaciones = new ArrayList();
    private Collection<Receta> recetas = new ArrayList();
    private List <Restaurante> restaurantes = new ArrayList();
    
  
    public Cocinero() {
    }

    public Cocinero(String nome, String apellido1, String apellido2, Character sexo, String apodo) {
        this.nombre = nome;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sexo = sexo;
        this.apodo = apodo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }
  

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    //1A
    public Contactococinero getContacto() {
        return contacto;
    }

    public void setContacto(Contactococinero contacto) {
        this.contacto = contacto;
    }


    public Collection<Premio> getPremios() {
        return premios;
    }

    public void setPremios(Collection<Premio> premios) {
        this.premios = premios;
    }

    public Collection<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(Collection<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public Collection<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(Collection<Receta> recetas) {
        this.recetas = recetas;
    }

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    

}
