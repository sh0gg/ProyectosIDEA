package POJOS;

/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */
public class Restaurante  implements java.io.Serializable {
    
     private String nombrerestaurante;
     private Integer tenedores;
     private String localidad;
   

    public Restaurante() {
    }

    public Restaurante(String nomerestaurante, Integer tenedores, String localidad) {
        this.nombrerestaurante = nomerestaurante;
        this.tenedores = tenedores;
        this.localidad = localidad;
    }

    public String getNombrerestaurante() {
        return nombrerestaurante;
    }

    public void setNombrerestaurante(String nombrerestaurante) {
        this.nombrerestaurante = nombrerestaurante;
    }

    public Integer getTenedores() {
        return tenedores;
    }

    public void setTenedores(Integer tenedores) {
        this.tenedores = tenedores;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    

}

