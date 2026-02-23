package DTO;

public class EmpregadoListadoDTO {

    private String nss;
    private String nomeCompleto;
    private String departamento;
    private String tipoEmpregado;
    private int numTelefonos;

    public EmpregadoListadoDTO(String nss, String nomeCompleto, String departamento, String tipoEmpregado, int numTelefonos) {
        this.nss = nss;
        this.nomeCompleto = nomeCompleto;
        this.departamento = departamento;
        this.tipoEmpregado = tipoEmpregado;
        this.numTelefonos = numTelefonos;
    }

   

    public String getNss() {
        return nss;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getTipoEmpregado() {
        return tipoEmpregado;
    }

    public int getNumTelefonos() {
        return numTelefonos;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setTipoEmpregado(String tipoEmpregado) {
        this.tipoEmpregado = tipoEmpregado;
    }

    public void setNumTelefonos(int numTelefonos) {
        this.numTelefonos = numTelefonos;
    }

}
