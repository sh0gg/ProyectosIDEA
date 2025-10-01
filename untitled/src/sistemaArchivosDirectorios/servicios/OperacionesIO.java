package sistemaArchivosDirectorios.servicios;

import sistemaArchivosDirectorios.excepciones.ArchivoNoExisteExcepcion;
import sistemaArchivosDirectorios.excepciones.DirectorioNoExisteException;
import sistemaArchivosDirectorios.excepciones.NoEsDirectorioException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperacionesIO {

    public static void visualizarContenido(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles();
        if (ficheros != null) {
            for (File file : ficheros) {
                Utilidades.mostrarInfo(file, "");
            }
        }
    }

    public static void recorrerRecursivo(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        recorrer(dir, "");
    }

    private static void recorrer(File dir, String sangria) {
        File[] ficheros = dir.listFiles();
        if (ficheros == null) return;
        for (File file : ficheros) {
            Utilidades.mostrarInfo(file, sangria);
            if (file.isDirectory()) {
                recorrer(file, sangria + "----");
            }
        }
    }

    public static void filtrarPorExtension(String ruta, String extension) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles((d, name) -> name.toLowerCase().endsWith(extension.toLowerCase()));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No se encontraron archivos con la extensión " + extension);
        } else {
            for (File file : ficheros) {
                Utilidades.mostrarInfo(file, "");
            }
        }
    }

    public static void filtrarPorExtensionYOrdenar(String ruta, String extension, boolean descendente) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles((d, name) -> name.toLowerCase().endsWith(extension.toLowerCase()));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No se encontraron archivos con la extensión " + extension);
        } else {
            Arrays.sort(ficheros, (f1, f2) -> {
                int cmp = f1.getName().compareToIgnoreCase(f2.getName());
                return descendente ? -cmp : cmp;
            });
            for (File file : ficheros) {
                Utilidades.mostrarInfo(file, "");
            }
        }
    }

    public static void filtrarPorSubcadena(String ruta, String subcadena) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles((d, name) -> name.toLowerCase().contains(subcadena.toLowerCase()));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No se encontraron archivos que contengan la subcadena " + subcadena);
        } else {
            for (File file : ficheros) {
                Utilidades.mostrarInfo(file, "");
            }
        }
    }

    public static void copiarArchivo(String origen, String destino) throws ArchivoNoExisteExcepcion, IOException {
        File ori = new File(origen);
        if (!ori.exists()) {
            throw new ArchivoNoExisteExcepcion("El archivo origen no existe.");
        }
        if (ori.isDirectory()) {
            System.out.println("El origen es un directorio, no se puede copiar como archivo.");
            return;
        }

        File dest = new File(destino);
        File parent = dest.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                System.out.println("No se pudo crear el directorio destino.");
                return;
            }
        }

        Files.copy(ori.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo copiado de " + origen + " a " + destino);
    }

    public static void moverArchivo(String origen, String destino) throws ArchivoNoExisteExcepcion, IOException {
        File ori = new File(origen);
        if (!ori.exists()) {
            throw new ArchivoNoExisteExcepcion("El archivo origen no existe.");
        }
        if (ori.isDirectory()) {
            System.out.println("El origen es un directorio, no se puede mover como archivo.");
            return;
        }

        File dest = new File(destino);
        File parent = dest.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                System.out.println("No se pudo crear el directorio destino.");
                return;
            }
        }

        Files.move(ori.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo movido de " + origen + " a " + destino);
    }

    public static void copiarDirectorio(String origen, String destino) throws DirectorioNoExisteException, NoEsDirectorioException, IOException {
        File ori = new File(origen);
        Utilidades.validarDirectorio(ori);

        File dest = new File(destino);
        if (!dest.exists() && !dest.mkdirs()) {
            System.out.println("No se pudo crear el directorio destino.");
            return;
        }

        copiarDirectorioRec(ori, dest);
    }

    private static void copiarDirectorioRec(File origen, File destino) throws IOException {
        File[] ficheros = origen.listFiles();
        if (ficheros == null) return;

        for (File file : ficheros) {
            File destFile = new File(destino, file.getName());
            if (file.isDirectory()) {
                if (!destFile.exists() && !destFile.mkdirs()) {
                    System.out.println("No se pudo crear el directorio " + destFile.getAbsolutePath());
                    continue;
                }
                copiarDirectorioRec(file, destFile);
            } else {
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Archivo copiado: " + destFile.getAbsolutePath());
            }
        }
    }

    public static void borrar(String ruta) throws ArchivoNoExisteExcepcion, DirectorioNoExisteException {
        File f = new File(ruta);
        if (!f.exists()) {
            if (ruta.endsWith(File.separator)) {
                throw new DirectorioNoExisteException("El directorio no existe.");
            } else {
                throw new ArchivoNoExisteExcepcion("El archivo no existe.");
            }
        }
        borrarRec(f);
    }

    private static void borrarRec(File file) {
        if (file.isDirectory()) {
            File[] hijos = file.listFiles();
            if (hijos != null) {
                for (File hijo : hijos) {
                    borrarRec(hijo);
                }
            }
        }
        if (file.delete()) {
            System.out.println("Borrado: " + file.getAbsolutePath());
        } else {
            System.out.println("No se pudo borrar: " + file.getAbsolutePath());
        }
    }
}
