package sistemaArchivosDirectorios.servicios;

import sistemaArchivosDirectorios.excepciones.DirectorioNoExisteException;
import sistemaArchivosDirectorios.excepciones.NoEsDirectorioException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utilidades {

    // Formateador de fecha con java.time
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Formateador numérico para tamaños
    private static final NumberFormat nf = NumberFormat.getInstance();

    public static String formatearFecha(long millis) {
        return dtf.format(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()));
    }

    public static String tamañoEnKB(long bytes) {
        double kb = bytes / 1024.0;
        return nf.format(kb) + " KB";
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
        String detalle = file.isFile()
                ? String.format("<FICHERO> %s %s", tamañoEnKB(file.length()), formatearFecha(file.lastModified()))
                : "<DIR>";
        System.out.printf("%s-|%s %s%n", sangria, file.getName(), detalle);
    }

    public static String calcularSangria(Path base, Path actual) {
        int profundidad = base.relativize(actual).getNameCount() - 1;
        return "----".repeat(Math.max(0, profundidad));
    }

    public static void alPadre(Path file, boolean move) throws IOException {
        Path padre = file.getParent();
        if (padre != null) {
            Path abuelo = padre.getParent();
            if (abuelo != null) {
                Path destino = abuelo.resolve(file.getFileName());

                if (!move) {
                    Files.copy(file, destino, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(file + " ha sido copiado a " + destino);
                } else {
                    Files.move(file, destino, StandardCopyOption.ATOMIC_MOVE);
                    System.out.println(file + " ha sido movido a " + destino);
                }
            } else {
                System.out.println("No se encontró un directorio 'abuelo' para " + file);
            }
        } else {
            System.out.println("No se encontró un directorio padre para " + file);
        }
    }
}
