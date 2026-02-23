package DTO;

public class DepartamentoMaxEmpregadosDTO {

    private String nomeDepartamento;
    private Long numeroEmpregados;
    private String director;

    public DepartamentoMaxEmpregadosDTO(String nomeDepartamento, Long numeroEmpregados, String director) {
        this.nomeDepartamento = nomeDepartamento;
        this.numeroEmpregados = numeroEmpregados;
        this.director = director;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public Long getNumeroEmpregados() {
        return numeroEmpregados;
    }

    public String getDirector() {
        return director;
    }
}

