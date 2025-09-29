package gestionFicheros.servicios;

import gestionFicheros.excepciones.DirectorioNoExisteException;
import gestionFicheros.excepciones.NoEsDirectorioException;

import java.io.File;
import java.util.Arrays;

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

    private static void recorrer(File dir, String sangria) {
        File[] ficheros = dir.listFiles();
        if (ficheros == null) {
            return;
        }
        for (File file : ficheros) {
            Utilidades.mostrarInfo(file, sangria);
            if (file.isDirectory()) {
                recorrer(file, sangria + "----");
            }
        }
    }

    private static void recorrerRecursivo(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        recorrer(dir, "");
    }

    private static void filtrarPorExtension(String ruta, String extension) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles(new Filtro(extension));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No se encontraron archivos con la extensión" + extension);
        }
    }

    private static void filtrarPorExtensionYOrdenar(String ruta, String extension, boolean descendente) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles(new Filtro(extension));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No se encontraron archivos con la extensión" + extension);
        } else if (descendente) {
            Arrays.sort(ficheros, new CompararNombres());
        } else  {
            Arrays.sort(ficheros, new CompararNombres().reversed());
        }

    }

    private static void filtrarPorSubcadena(String ruta, String subcadena, boolean descendente) throws DirectorioNoExisteException, NoEsDirectorioException {
        File dir = new File(ruta);
        Utilidades.validarDirectorio(dir);
        File[] ficheros = dir.listFiles(new Filtro(subcadena));

        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No se encontraron archivos con la subcadena" + subcadena);
        } else if (descendente) {
            Arrays.sort(ficheros, new CompararNombres());
        } else {
            Arrays.sort(ficheros, new CompararNombres().reversed());
        }
    }

    private static void copiarArchivo(String origen, String destino) throws DirectorioNoExisteException, NoEsDirectorioException {
        File ori = new File(origen);
        Utilidades.validarDirectorio(ori);

        File des = new File(destino);
        Utilidades.validarDirectorio(des);

        if  (!ori.exists()) {

        }

    }



}
