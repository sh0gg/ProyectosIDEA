package clases;

public class DepartamentoNumProyectos {

    private int numDepartamento;
    private String nomeDepartamento;
    private String nssDirector;
    private int numProyectos;

    public DepartamentoNumProyectos() {}

    public DepartamentoNumProyectos(int numDepartamento, String nomeDepartamento,
                                    String nssDirector, int numProyectos) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirector = nssDirector;
        this.numProyectos = numProyectos;
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

    public int getNumProyectos() {
        return numProyectos;
    }

    public void setNumProyectos(int numProyectos) {
        this.numProyectos = numProyectos;
    }

    @Override
    public String toString() {
        return "DepartamentoNumProyectos{" +
                "numDepartamento=" + numDepartamento +
                ", nomeDepartamento='" + nomeDepartamento + '\'' +
                ", nssDirector='" + nssDirector + '\'' +
                ", numProyectos=" + numProyectos +
                '}';
    }
}
