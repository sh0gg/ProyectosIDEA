package POJOS;

import java.time.LocalDate;

public class Vehiculo  implements java.io.Serializable {


     private String nss;   
     private String matricula;
     private String marca;
     private String modelo;
     private LocalDate dataCompra;
      //Empleado de vehiculo
     private Empregado empregado;

    public Vehiculo() {
    }

	
    public Vehiculo(Empregado empregado) {
        this.empregado = empregado;
    }
    public Vehiculo(Empregado empregado, String matricula, String marca, String modelo, LocalDate dataCompra) {
       this.empregado = empregado;
       this.matricula = matricula;
       this.marca = marca;
       this.modelo = modelo;
       this.dataCompra = dataCompra;
    }
   
    public String getNss() {
        return this.nss;
    }
    
    public void setNss(String nss) {
        this.nss = nss;
    }
    public Empregado getEmpregado() {
        return this.empregado;
    }
    
    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }
    public String getMatricula() {
        return this.matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public LocalDate getDataCompra() {
        return this.dataCompra;
    }
    
    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }




}


