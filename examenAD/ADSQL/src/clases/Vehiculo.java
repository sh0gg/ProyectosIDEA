package clases;

public class Vehiculo {

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String tipoCombustible;   // Gasolina, Diésel, Eléctrico...

    public Vehiculo() {}

    public Vehiculo(int id, String matricula, String marca, String modelo, String tipoCombustible) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoCombustible = tipoCombustible;
    }

    // getters / setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }
}
