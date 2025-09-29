package gestionFicheros.servicios;

import gestionFicheros.excepciones.DirectorioNoExisteException;
import gestionFicheros.excepciones.NoEsDirectorioException;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

public class OperacionesNIO {

    public static void visualizarContenido(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());
    }


    public static void recorrerRecursivo(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());
        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(path -> Files.isReadable(path)).forEach(path -> {
                String sangria = Utilidades.calcularSangria(dir, path);
                Utilidades.mostrarInfo(path.toFile(), sangria);
            });
        }
    }

    public static void recorrerRecursivo2(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path d, BasicFileAttributes attrs) throws IOException {
                String sangria = Utilidades.calcularSangria(dir, d);

                Utilidades.mostrarInfo(dir.toFile(), sangria);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String sangria = Utilidades.calcularSangria(dir, file);
                Utilidades.mostrarInfo(file.toFile(), sangria);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.err.println("Error al visitar el directorio:" + file.toAbsolutePath() + exc.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });

    }

    public static void filtrarPorExtension(String ruta, String extension) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*." + extension)) {
            boolean encontrado = false;
            for (Path path : stream) {
                Utilidades.mostrarInfo(path.toFile(), "");
                encontrado = true;
            }
            if (!encontrado) {
                System.out.println("No se han encontrado archivos con la extension " + extension);
            }
        } catch (AccessDeniedException ade) {
            System.out.println("No hay permisos para este directorio." + ade.getMessage());
        }
    }

    public static void filtrarPorExtensionRecursivo(String ruta, String extension) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(path -> {
                try {
                    return Files.isRegularFile(path) && Files.isReadable(path) && path.toString().endsWith(extension);
                } catch (Exception e) {
                    System.err.println("No se pudo acceder a " + path.toAbsolutePath() + "." + extension);
                    return false;
                }
            }).forEach(path -> {
                String sangria = Utilidades.calcularSangria(dir, path);
                Utilidades.mostrarInfo(path.toFile(), sangria);
            });
        } catch (AccessDeniedException ade) {
            System.out.println("No hay permisos para este directorio." + ade.getMessage());
        }
    }

    public static void filtrarPorExtensionOrdenar (String ruta, String extension, boolean descendente) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        try (Stream<Path> stream = Files.list(dir)) {
            Stream<Path> filtrados  = stream.filter(path ->
                Files.isRegularFile(path) && path.getFileName().toString().endsWith(extension));

            Comparator<Path> comp = Comparator.comparing(p -> p.getFileName().toString().toLowerCase());
            if (descendente) {
                comp = comp.reversed();
            }

            filtrados.sorted(comp).forEach(path -> {
                Utilidades.mostrarInfo(path.toFile(),"");
            });
        }
    }

    public static void copiarNIO(String origen, String destino) throws NoEsDirectorioException, IOException {

    }
}
