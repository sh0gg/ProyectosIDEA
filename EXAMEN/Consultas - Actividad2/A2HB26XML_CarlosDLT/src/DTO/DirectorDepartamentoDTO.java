package DTO;

// DTO para transferir a información dos directores dun departamento
public class DirectorDepartamentoDTO {

    private String nssDirector;
    private String nomeCompletoDirector;
    private String nomeCompletoSupervisor;
    private Double salario;
    private String nomeDepartamento;

    // Constructor
    public DirectorDepartamentoDTO(String nssDirector, String nomeCompletoDirector,
            String nomeCompletoSupervisor, Double salario,
            String nomeDepartamento) {
        this.nssDirector = nssDirector;
        this.nomeCompletoDirector = nomeCompletoDirector;
        this.nomeCompletoSupervisor = nomeCompletoSupervisor;
        this.salario = salario;
        this.nomeDepartamento = nomeDepartamento;
    }

    // Getters
    public String getNssDirector() {
        return nssDirector;
    }

    public String getNomeCompletoDirector() {
        return nomeCompletoDirector;
    }

    public String getNomeCompletoSupervisor() {
        return nomeCompletoSupervisor;
    }

    public Double getSalario() {
        return salario;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNssDirector(String nssDirector) {
        this.nssDirector = nssDirector;
    }

    public void setNomeCompletoDirector(String nomeCompletoDirector) {
        this.nomeCompletoDirector = nomeCompletoDirector;
    }

    public void setNomeCompletoSupervisor(String nomeCompletoSupervisor) {
        this.nomeCompletoSupervisor = nomeCompletoSupervisor;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

}
