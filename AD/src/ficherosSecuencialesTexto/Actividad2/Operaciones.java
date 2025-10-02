package ficherosSecuencialesTexto.Actividad2;

import java.io.*;
import java.nio.file.*;

public class Operaciones {

    // ejercicio 1

    public static boolean existe(Path path) {
        return Files.exists(path);
    }

    public static String nombreArchivo(Path path) {
        return path.getFileName().toString();
    }

    public static int contarLineas(Path path) throws IOException {
        int lineas = 0;
        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(path);
            while (reader.readLine() != null) {
                lineas++;
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return lineas;
    }

    public static void escribirResultado(String resultado) {
        BufferedWriter writer = null;

        try {
            writer = Files.newBufferedWriter(Paths.get("Salida.txt"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            writer.write(resultado);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de salida.");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el archivo de salida.");
            }
        }
    }

    // ejercicio 2

    public static void crearDirectorio(String curso, String numero, String alumno) {
        Path pathCurso = Paths.get("src/ficherosSecuencialesTexto/Actividad2/" + curso);
        Path pathNumero = Paths.get(pathCurso.toString(), numero);
        Path pathAlumno = Paths.get(pathNumero.toString(), alumno);

        try {
            Files.createDirectories(pathAlumno);
            escribirLog("Directorio creado: " + pathAlumno);
        } catch (IOException e) {
            escribirLog("Error al crear el directorio: " + pathAlumno);
        }
    }

    public static void escribirLog(String mensaje) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/ficherosSecuencialesTexto/Actividad2/ficherolog.txt"), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de log.");
        }
    }

    public static void procesarArchivo(Path archivo) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(archivo)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("/");
                if (partes.length == 3) {
                    crearDirectorio(partes[0], partes[1], partes[2]);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        }
    }

    // ejercicio 3

    public static void contarPalabras(String palabra, Path archivo, Path archivoSalida) throws IOException {
        int totalPalabras = 0;

        try (BufferedReader reader = Files.newBufferedReader(archivo);
             BufferedWriter writer = Files.newBufferedWriter(archivoSalida, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                int countLinea = contarEnLinea(linea, palabra);
                totalPalabras += countLinea;

                writer.write("Línea: " + linea + " -> " + palabra + " aparece " + countLinea + " veces.");
                writer.newLine();
            }

            writer.write("Total de la palabra '" + palabra + "' en el archivo: " + totalPalabras);
            writer.newLine();
        }
    }


    private static int contarEnLinea(String linea, String palabra) {
        String[] palabras = linea.split("\\s+");
        int count = 0;
        for (String p : palabras) {
            if (p.equalsIgnoreCase(palabra)) {
                count++;
            }
        }
        return count;
    }
}
