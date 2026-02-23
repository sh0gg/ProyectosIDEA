package DTO;

public class EmpregadoProxectoDTO {

    private String nss;
    private String nome;
    private String apelidos;
    private String nomeProxecto;
    private Integer horas;

    public EmpregadoProxectoDTO(String nss, String nome, String apelidos,
                                String nomeProxecto, Integer horas) {
        this.nss = nss;
        this.nome = nome;
        this.apelidos = apelidos;
        this.nomeProxecto = nomeProxecto;
        this.horas = horas;
    }

    // getters e setters

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getNomeProxecto() {
        return nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }
    
}
