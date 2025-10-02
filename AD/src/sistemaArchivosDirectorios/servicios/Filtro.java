package sistemaArchivosDirectorios.servicios;

import java.nio.file.Path;

public class Filtro {

    public static boolean tieneExtension(Path p, String extension) {
        String nombre = p.getFileName().toString();
        return nombre.toLowerCase().endsWith(extension.toLowerCase());
    }

    public static boolean contieneSubcadena(Path p, String subcadena) {
        String nombre = p.getFileName().toString().toLowerCase();
        return nombre.contains(subcadena.toLowerCase());
    }
}
