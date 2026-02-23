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
public class Premio implements java.io.Serializable{
    String premio;
    int anho;

    public Premio() {
    }

    public Premio(String premio, int anho) {
        this.premio = premio;
        this.anho = anho;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }
    
}
