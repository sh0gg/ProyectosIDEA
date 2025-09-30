package sistemaArchivosDirectorios.servicios;

import sistemaArchivosDirectorios.excepciones.DirectorioNoExisteException;
import sistemaArchivosDirectorios.excepciones.NoEsDirectorioException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utilidades {
    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String formatearFecha(long millis) {
        return dtf.format(Instant.ofEpochMilli(millis).atZone((ZoneId) ZoneId.systemDefault()));
    }

    public static void validarDirectorio(File dir) throws DirectorioNoExisteException, NoEsDirectorioException {
        if (!dir.exists()) {
            throw new DirectorioNoExisteException("La ruta no existe.");
        }
        if (!dir.isDirectory()) {
            throw new NoEsDirectorioException("La ruta no es un directorio.");
        }
    }

    public static void mostrarInfo(File file, String sangria) {
        NumberFormat nf = NumberFormat.getInstance();

        String detalle = file.isFile()
                ? String.format("<FICHERO> %s KB %s", nf.format(file.length() / 1024.0), formatearFecha(file.lastModified()))
                : "<DIR>";
        System.out.printf("%s-|%s %s%n", sangria, file.getName(), detalle);
    }

    public static void alPadre(Path file, boolean move) throws IOException {
        Path parent = file.getParent();
        if (parent != null) {
            Path grandParent = parent.getParent();
            if (grandParent != null) {
                Path destino = grandParent.resolve(file.getFileName());

                try {
                    if (!move) {
                        Files.copy(file, destino, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println(file + " has been copied to " + destino);
                    } else {
                        Files.move(file, destino, StandardCopyOption.ATOMIC_MOVE);
                        System.out.println(file + " has been moved to " + destino);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("No grandparent directory found");
            }
        } else {
            System.out.println("No parent directory found");
        }
    }

    public static String calcularSangria(Path base, Path actual) {
        int profundidad = base.relativize(actual).getNameCount() -1;
        return "----".repeat(Math.max(0, profundidad));
    }


}


