package ficherosSecuencialesTexto.Actividad2;

/*
Escribe un método que cuente el número de líneas de cada fichero que se especifique en la línea de
comandos (Nota: pueden especificarse varios archivos, como por ejemplo: "exercicio5-1 file1.txt file3.txt
file2.txt"). Los archivos deben ser archivos de texto con la extensión txt.
Escribe en un fichero de texto llamado Salida.txt: el nombre de cada fichero, junto con el número de líneas
del fichero. Si ocurre un error al intentar leer uno de los ficheros, en el fichero salida.txt se graba un mensaje
de error para el archivo, y se deben procesar todos los ficheros restantes.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ejercicio1 {
    public static void main(String[] args) {

        args = new String[]{
                "src/readersAndWriters/Actividad2/file0.txt",
                "src/readersAndWriters/Actividad2/file1.txt",
                "src/readersAndWriters/Actividad2/file2.txt",
                "src/readersAndWriters/Actividad2/file3.txt",
                "src/readersAndWriters/Actividad2/file4.txt"
        };

        for (String arg : args) {
            Path path = Paths.get(arg);

            if (!Operaciones.existe(path)) {
                System.out.println("No se encontró el archivo: " + Operaciones.nombreArchivo(path));
                continue;
            }

            try {
                int numLin = Operaciones.contarLineas(path);
                System.out.println("El archivo " + Operaciones.nombreArchivo(path)
                        + " tiene " + numLin + " lineas.");
            } catch (FileNotFoundException e) {
                System.out.println("No se encontró el archivo: " + Operaciones.nombreArchivo(path));
            } catch (IOException e) {
                System.out.println("Error leyendo el archivo: " + Operaciones.nombreArchivo(path));
            }
        }
    }
}