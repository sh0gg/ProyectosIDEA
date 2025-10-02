package sistemaArchivosDirectorios.servicios;

import sistemaArchivosDirectorios.excepciones.DirectorioNoExisteException;
import sistemaArchivosDirectorios.excepciones.NoEsDirectorioException;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

public class OperacionesNIO {

    public static void visualizarContenido(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                Utilidades.mostrarInfo(path.toFile(), "");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el directorio: " + e.getMessage());
        }
    }

    public static void recorrerRecursivo(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        try (Stream<Path> stream = Files.walk(dir)) {
            stream.forEach(path -> {
                String sangria = Utilidades.calcularSangria(dir, path);
                Utilidades.mostrarInfo(path.toFile(), sangria);
            });
        }
    }

    public static void recorrerRecursivoConVisitor(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path d, BasicFileAttributes attrs) {
                String sangria = Utilidades.calcularSangria(dir, d);
                Utilidades.mostrarInfo(d.toFile(), sangria);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                String sangria = Utilidades.calcularSangria(dir, file);
                Utilidades.mostrarInfo(file.toFile(), sangria);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("Error al visitar el archivo: " + file.toAbsolutePath() + " - " + exc.getMessage());
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
                System.out.println("No se han encontrado archivos con la extensión " + extension);
            }
        } catch (AccessDeniedException ade) {
            System.out.println("No hay permisos para este directorio: " + ade.getMessage());
        }
    }

    public static void filtrarPorExtensionRecursivo(String ruta, String extension) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(path -> {
                try {
                    return Files.isRegularFile(path) && Files.isReadable(path) && path.toString().endsWith("." + extension);
                } catch (Exception e) {
                    System.err.println("No se pudo acceder a " + path.toAbsolutePath() + ": " + e.getMessage());
                    return false;
                }
            }).forEach(path -> {
                String sangria = Utilidades.calcularSangria(dir, path);
                Utilidades.mostrarInfo(path.toFile(), sangria);
            });
        } catch (AccessDeniedException ade) {
            System.out.println("No hay permisos para este directorio: " + ade.getMessage());
        }
    }

    public static void filtrarPorExtensionOrdenar(String ruta, String extension, boolean descendente) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        Path dir = Paths.get(ruta);
        Utilidades.validarDirectorio(dir.toFile());

        try (Stream<Path> stream = Files.list(dir)) {
            Comparator<Path> comp = Comparator.comparing(p -> p.getFileName().toString().toLowerCase());
            if (descendente) {
                comp = comp.reversed();
            }

            stream.filter(path -> Files.isRegularFile(path) && path.getFileName().toString().toLowerCase().endsWith("." + extension))
                    .sorted(comp)
                    .forEach(path -> Utilidades.mostrarInfo(path.toFile(), ""));
        }
    }

    public static void copiarNIO(String origen, String destino) throws IOException {
        Path origenPath = Paths.get(origen);
        Path destinoPath = Paths.get(destino);

        if (!Files.exists(origenPath)) {
            System.out.println("El archivo o directorio origen no existe.");
            return;
        }

        if (Files.isDirectory(origenPath)) {
            copiarDirectorioNIO(origenPath, destinoPath);
        } else {
            copiarArchivoNIO(origenPath, destinoPath);
        }
    }

    private static void copiarArchivoNIO(Path origen, Path destino) throws IOException {
        Path parent = destino.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo copiado de " + origen + " a " + destino);
    }

    private static void copiarDirectorioNIO(Path origen, Path destino) throws IOException {
        Files.walkFileTree(origen, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = destino.resolve(origen.relativize(dir));
                if (!Files.exists(targetDir)) {
                    Files.createDirectories(targetDir);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetFile = destino.resolve(origen.relativize(file));
                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo copiado: " + targetFile);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void borrarNIO(String ruta) throws IOException {
        Path path = Paths.get(ruta);
        if (!Files.exists(path)) {
            System.out.println("No existe el archivo o directorio a borrar.");
            return;
        }

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                System.out.println("Archivo borrado: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                System.out.println("Directorio borrado: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
