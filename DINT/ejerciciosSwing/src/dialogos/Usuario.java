package dialogos;

public class Usuario {

    private int id;
    private String nome;
    private String apelidos;
    private String provincia;

    public Usuario(int id, String nome, String apelidos, String provincia) {
        this.id = id;
        this.nome = nome;
        this.apelidos = apelidos;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public String getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " " + apelidos + " (" + provincia + ")";
    }
}
