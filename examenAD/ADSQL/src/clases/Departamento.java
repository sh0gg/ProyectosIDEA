package clases;

public class Departamento {

    private int numDepartamento;
    private String nomeDepartamento;
    private String nssDirector;

    public Departamento() {}

    public Departamento(int numDepartamento, String nomeDepartamento, String nssDirector) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirector = nssDirector;
    }

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getNssDirector() {
        return nssDirector;
    }

    public void setNssDirector(String nssDirector) {
        this.nssDirector = nssDirector;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "numDepartamento=" + numDepartamento +
                ", nomeDepartamento='" + nomeDepartamento + '\'' +
                ", nssDirector='" + nssDirector + '\'' +
                '}';
    }
}
