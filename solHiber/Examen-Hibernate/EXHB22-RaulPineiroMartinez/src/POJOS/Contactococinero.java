package POJOS;
/*
 Código para el examen de HB

 nombre del alumno: Raúl Piñeiro Martínez
 DNI: 77416997D

 */

public class Contactococinero implements java.io.Serializable {


    private int codcocinero;
    private String email;
    private String telefonomovil;
    private String telefonofijo;
    //1A
    private Cocinero cocinero;
    
   

    public Contactococinero() {
    }

    public Contactococinero(String email, String telefonomovil, String telefonofijo) {

        this.email = email;
        this.telefonomovil = telefonomovil;
        this.telefonofijo = telefonofijo;
    }

    public int getCodcocinero() {
        return this.codcocinero;
    }

    public void setCodcocinero(int codcocinero) {
        this.codcocinero = codcocinero;
    }

      public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonomovil() {
        return this.telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getTelefonofijo() {
        return this.telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

//1A
    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }
    
  


}
