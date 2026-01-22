package clases;

// DNI : 53612286e
// Nombre: David Besada

public class Fotografia {
    private int codigo;
    private String nombre;
    private String medidas;
    private String fecha;
    private char color;
    private int id_fotografo;
    private int id_exposicion;
    private String tipo;
    private String encuadre;
    private String composicion;
    private String tipoDocumental;

    public Fotografia(String nombre, String medidas, String fecha, char color) {
        this.nombre = nombre;
        this.medidas = medidas;
        this.fecha = fecha;
        this.color = color;
    }

    public Fotografia(String nombre, String medidas, String fecha, char color, String tipo, String encuadre, String composicion) {
        this.nombre = nombre;
        this.medidas = medidas;
        this.fecha = fecha;
        this.color = color;
        this.tipo = tipo;
        this.encuadre = encuadre;
        this.composicion = composicion;
    }

    public Fotografia(String nombre, String medidas, String fecha, char color, String tipo, String tipoDocumental) {
        this.nombre = nombre;
        this.medidas = medidas;
        this.fecha = fecha;
        this.color = color;
        this.tipo = tipo;
        this.tipoDocumental = tipoDocumental;
    }

    @Override
    public String toString() {
        return nombre + "(" +fecha.substring(0,4)+") - " + tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMedidas() {
        return medidas;
    }

    public String getFecha() {
        return fecha;
    }

    public char getColor() {
        return color;
    }

    public int getId_fotografo() {
        return id_fotografo;
    }

    public int getId_exposicion() {
        return id_exposicion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEncuadre() {
        return encuadre;
    }

    public String getComposicion() {
        return composicion;
    }

    public String getTipoDocumental() {
        return tipoDocumental;
    }
}
