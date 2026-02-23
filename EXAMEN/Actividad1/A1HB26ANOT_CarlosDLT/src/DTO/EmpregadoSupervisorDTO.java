
package DTO;

public class EmpregadoSupervisorDTO {

    private String nomeEmpregado;
    private String apelidosEmpregado;

    private String nomeSupervisor;     // pode ser null
    private String apelidosSupervisor; // pode ser null

    public EmpregadoSupervisorDTO(String nomeEmpregado, String apelidosEmpregado,
                                  String nomeSupervisor, String apelidosSupervisor) {
        this.nomeEmpregado = nomeEmpregado;
        this.apelidosEmpregado = apelidosEmpregado;
        this.nomeSupervisor = nomeSupervisor;
        this.apelidosSupervisor = apelidosSupervisor;
    }

    // getters
    public String getNomeEmpregado() { return nomeEmpregado; }
    public String getApelidosEmpregado() { return apelidosEmpregado; }
    public String getNomeSupervisor() { return nomeSupervisor; }
    public String getApelidosSupervisor() { return apelidosSupervisor; }
}
