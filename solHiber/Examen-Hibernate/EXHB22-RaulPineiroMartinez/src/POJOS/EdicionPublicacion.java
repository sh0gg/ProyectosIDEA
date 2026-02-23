/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package POJOS;

/*

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */
public class EdicionPublicacion implements java.io.Serializable{
    private String isbn;
     private Integer numedicion;

    public EdicionPublicacion() {
    }

    public EdicionPublicacion(String isbn, Integer numedicion) {
        this.isbn = isbn;
        this.numedicion = numedicion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumedicion() {
        return numedicion;
    }

    public void setNumedicion(Integer numedicion) {
        this.numedicion = numedicion;
    }
     
     
    
}
