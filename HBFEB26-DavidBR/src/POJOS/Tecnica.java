package POJOS;

public class Tecnica {
    private String tecnica;
    private String nivel;

    public Tecnica(String tecnica, String nivel) {
        this.tecnica = tecnica;
        this.nivel = nivel;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
