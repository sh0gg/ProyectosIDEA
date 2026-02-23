
package DTO;

public class EmpregadoListadoDepartamentoDTO {
    private String nss;
    private String nombreCompleto;
    private String tipo;
    private String departamento;  //nombre del deparrtamento si lo dirige sino será null

    public EmpregadoListadoDepartamentoDTO(String nss, String nombreCompleto, String tipo, String departamento) {
        this.nss = nss;
        this.nombreCompleto = nombreCompleto;
        this.tipo = tipo;
        this.departamento = departamento;
    }

   

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


}

