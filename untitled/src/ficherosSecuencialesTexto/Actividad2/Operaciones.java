package ficherosSecuencialesTexto.Actividad2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Operaciones {

    private Operaciones() {
// Evita instanciación
    }

    /**
     * Devuelve true si el archivo existe y es un fichero normal.
     */
    public static boolean existe(Path path) {
        return path != null && Files.isRegularFile(path);
    }

    /**
     * Devuelve el tamaño del archivo en bytes (0 si no existe o no es fichero).
     */
    public static long tamano(Path path) {
        try {
            return existe(path) ? Files.size(path) : 0;
        } catch (IOException e) {
            return 0; // también podríamos propagar la excepción
        }
    }

    /**
     * Cuenta líneas de un archivo. Si el archivo está vacío devuelve 0.
     *
     * @throws FileNotFoundException si el archivo no existe.
     * @throws IOException           si ocurre un error de E/S al leer.
     */
    public static int contarLineas(Path path) throws IOException {
        if (!existe(path)) {
            throw new FileNotFoundException("No se encontró el archivo: " + path);
        }

        if (tamano(path) == 0) {
            return 0; // vacío → 0 líneas
        }

// Contamos por líneas, independientemente de \n, \r\n, etc.
        int contador = 0;
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            while (br.readLine() != null) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el nombre de archivo (sin ruta) de forma segura.
     */
    public static String nombreArchivo(Path path) {
        return path == null ? "" : String.valueOf(path.getFileName());
    }
}