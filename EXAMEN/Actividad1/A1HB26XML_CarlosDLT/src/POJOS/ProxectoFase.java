
package POJOS;


public class ProxectoFase {
   private String nomeFase;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProxectoFase that = (ProxectoFase) o;
        return nomeFase != null &&
               nomeFase.equalsIgnoreCase(that.nomeFase);
    }

    @Override
    public int hashCode() {
        return nomeFase == null ? 0 : nomeFase.toLowerCase().hashCode();
    }

}
