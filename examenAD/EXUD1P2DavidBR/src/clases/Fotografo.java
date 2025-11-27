package clases;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fotografo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean borrado;
    private int codigo;
    private String licencia;
    private String nombre;
    private String estudio;
    private int numFotografias;
    private List<Fotografia> fotografias;

    public Fotografo(int codigo, String licencia, String nombre, String estudio, int numFotografias, List<Fotografia> fotografias) {
        this.borrado = false;
        this.codigo = codigo;
        this.licencia = licencia;
        this.nombre = nombre;
        this.estudio = estudio;
        this.numFotografias = numFotografias;
        this.fotografias = fotografias;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public int getNumFotografias() {
        return numFotografias;
    }

    public void setNumFotografias(int numFotografias) {
        this.numFotografias = numFotografias;
    }

    public List<Fotografia> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<Fotografia> fotografias) {
        this.fotografias = fotografias;
    }

    public String printFotografia(){
        StringBuilder sb = new StringBuilder();
        for(Fotografia f : fotografias){
            sb.append("     ");
            sb.append(f.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", Licencia: " + licencia + ", Nombre: " + nombre + ", Estudio: " + estudio + ", Número de fotografias: " + numFotografias + "\n" +
                "Fotografias: \n" + printFotografia();
    }
}
