package POJOS;

/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */
public class Receta  implements java.io.Serializable {


     private int codreceta;
     private String nome;
     private String dificultade;
     private Double tempo;
     private String elaboracion;
     private Cocinero cocinero;
     

    public Receta() {
    }

	
    public Receta(int codreceta, String nome) {
        this.codreceta = codreceta;
        this.nome = nome;
    }
    public Receta(String nome, String dificultade, Double tempo, String elaboracion) {    
    
       this.nome = nome;
       this.dificultade = dificultade;
       this.tempo = tempo;
       this.elaboracion = elaboracion;
    }
    
   
    public int getCodreceta() {
        return this.codreceta;
    }
    
    public void setCodreceta(int codreceta) {
        this.codreceta = codreceta;
    }
  
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDificultade() {
        return this.dificultade;
    }
    
    public void setDificultade(String dificultade) {
        this.dificultade = dificultade;
    }
    public Double getTempo() {
        return this.tempo;
    }
    
    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }
    public String getElaboracion() {
        return this.elaboracion;
    }
    
    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }




}


