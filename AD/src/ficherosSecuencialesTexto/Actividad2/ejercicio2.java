package ficherosSecuencialesTexto.Actividad2;
/*
A partir de un fichero de texto con el formato CURSO/NUMERO/ALUMNO crear un directorio por cada
curso y dentro de este un directorio por cada alumno perteneciente a ese curso. En un fichero de texto
llamado ficherolog.txt se irá escribiendo el éxito o fracaso en la creación de cada directorio de alumnos
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ejercicio2 {
    public static void main(String[] args) {
        Path archivoEntrada = Paths.get("alumnos.txt");

        try {
            Operaciones.procesarArchivo(archivoEntrada);
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + archivoEntrada);
        }
    }
}
