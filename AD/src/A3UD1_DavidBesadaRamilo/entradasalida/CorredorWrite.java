package A3UD1_DavidBesadaRamilo.entradasalida;


import A3UD1_DavidBesadaRamilo.modelo.Corredor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CorredorWrite {
    private final Path fichero;

    public CorredorWrite(Path fichero) { this.fichero = fichero; }

    public void append(Corredor c) throws IOException {
        boolean existe = Files.exists(fichero) && Files.size(fichero) > 0;
        try (ObjectOutputStream oos = existe
                ? new AppendObjectOutputStream(new FileOutputStream(fichero.toFile(), true))
                : new ObjectOutputStream(new FileOutputStream(fichero.toFile(), true))) {
            oos.writeObject(c);
        }
    }
}
