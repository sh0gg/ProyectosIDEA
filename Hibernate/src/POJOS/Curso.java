package POJOS;

public class Curso implements java.io.Serializable {

    private int codigo;
    private String nome;
    private Integer horas;
   

    public Curso() {
    }

    public Curso(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Curso(String nome, Integer horas) {
        this.nome = nome;
        this.horas = horas;
    }

    

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    

    

}
