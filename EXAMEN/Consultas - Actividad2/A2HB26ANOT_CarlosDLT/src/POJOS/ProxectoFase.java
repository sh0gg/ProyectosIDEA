package POJOS;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class ProxectoFase implements Serializable {

    @Column(name = "NomeFase", length = 50, nullable = false)
    private String nomeFase;
    @Column(name = "Estado", length = 30, nullable = false)
    private String estado;

    public ProxectoFase() {
    }

    public ProxectoFase(String nomeFase, String estado) {
        this.nomeFase = nomeFase;
        this.estado = estado;
    }

    public String getNomeFase() {
        return nomeFase;
    }

    public void setNomeFase(String nomeFase) {
        this.nomeFase = nomeFase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
