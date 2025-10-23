package A3UD1_DavidBesadaRamilo.servicios;



import A3UD1_DavidBesadaRamilo.entradasalida.CorredorRead;
import A3UD1_DavidBesadaRamilo.entradasalida.CorredorWrite;
import A3UD1_DavidBesadaRamilo.modelo.Corredor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCorredores {
    private final Path fichero;
    private int nextDorsal;

    public GestorCorredores(Path fichero) {
        this.fichero = fichero;
        this.nextDorsal = 1;
    }

    public synchronized Corredor alta(Corredor c) throws IOException {
        c.setDorsal(nextDorsal++);
        new CorredorWrite(fichero).append(c);
        return c;
    }

    public List<Corredor> listar() throws Exception {
        return new CorredorRead(fichero).leerTodos();
    }

    public List<Corredor> listarPorEquipo(int equipoId) throws Exception {
        List<Corredor> all = listar(), out = new ArrayList<>();
        for (Corredor c : all) if (c.getEquipoId() == equipoId) out.add(c);
        return out;
    }
}
