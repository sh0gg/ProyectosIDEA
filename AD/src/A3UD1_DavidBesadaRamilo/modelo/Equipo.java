package A3UD1_DavidBesadaRamilo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;                 // clave
    private String nombre;          // máx 30 chars (para RAF)
    private String pais;            // máx 20 chars
    private int anioFundacion;
    private boolean activo;         // lógico/borrado
    private List<Patrocinador> sponsors = new ArrayList<>();

    public Equipo(int id, String nombre, String pais, int anioFundacion, boolean activo) {
        this.id = id; this.nombre = nombre; this.pais = pais;
        this.anioFundacion = anioFundacion; this.activo = activo;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
    public int getAnioFundacion() { return anioFundacion; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean a) { this.activo = a; }
    public List<Patrocinador> getSponsors(){ return sponsors; }

    @Override
    public String toString() {
        return "Equipo{" + id + ", '" + nombre + "', " + pais + ", " + anioFundacion +
               ", activo=" + activo + ", sponsors=" + sponsors + "}";
    }
}
