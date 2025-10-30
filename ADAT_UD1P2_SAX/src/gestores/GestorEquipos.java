package gestores;

import io.EquipoRandom;
import modelo.Equipo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorEquipos implements AutoCloseable {
    private final EquipoRandom eraf;

    public GestorEquipos(Path fichero) throws IOException {
        this.eraf = new EquipoRandom(fichero);
    }

    public int insertarOVolverIndice(Equipo e) throws IOException {
        // Si ya existe por id, lo sobreescribimos en su índice (id == índice recomendación docente)
        int idx = e.getId();
        if (idx < 0) throw new IllegalArgumentException("id de equipo debe ser >= 0");
        eraf.writeAt(idx, e);
        return idx;
    }

    public Equipo leer(int index) throws IOException { return eraf.readAt(index); }

    public List<Equipo> leerTodos() throws IOException {
        int n = eraf.size();
        List<Equipo> out = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Equipo e = eraf.readAt(i);
            if (e != null) out.add(e);
        }
        return out;
    }

    public void borrarLogico(int index) throws IOException {
        Equipo e = eraf.readAt(index);
        if (e != null) { e.setActivo(false); eraf.writeAt(index, e); }
    }

    @Override public void close() throws IOException { eraf.close(); }
}
