package clases;

public class DepartamentoNumEmpleados {

    private int numDepartamento;
    private String nomeDepartamento;
    private String nssDirector;
    private int numEmpleados;

    public DepartamentoNumEmpleados() {}

    public DepartamentoNumEmpleados(int numDepartamento, String nomeDepartamento,
                                    String nssDirector, int numEmpleados) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirector = nssDirector;
        this.numEmpleados = numEmpleados;
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

    public int getNumEmpleados() {
        return numEmpleados;
    }

    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    @Override
    public String toString() {
        return "DepartamentoNumEmpleados{" +
                "numDepartamento=" + numDepartamento +
                ", nomeDepartamento='" + nomeDepartamento + '\'' +
                ", nssDirector='" + nssDirector + '\'' +
                ", numEmpleados=" + numEmpleados +
                '}';
    }
}
