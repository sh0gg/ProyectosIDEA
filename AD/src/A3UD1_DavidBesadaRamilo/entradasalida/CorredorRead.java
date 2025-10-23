package A3UD1_DavidBesadaRamilo.entradasalida;


import A3UD1_DavidBesadaRamilo.modelo.Corredor;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CorredorRead {
    private final Path fichero;

    public CorredorRead(Path fichero) { this.fichero = fichero; }

    public List<Corredor> leerTodos() throws Exception {
        List<Corredor> lista = new ArrayList<>();
        if (!fichero.toFile().exists()) return lista;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero.toFile()))) {
            while (true) {
                try { lista.add((Corredor) ois.readObject()); }
                catch (EOFException e) { break; }
            }
        }
        return lista;
    }
}
