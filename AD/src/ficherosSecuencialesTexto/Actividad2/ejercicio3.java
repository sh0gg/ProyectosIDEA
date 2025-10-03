package ficherosSecuencialesTexto.Actividad2;

import java.io.IOException;
import java.nio.file.*;

public class ejercicio3 {
    public static void main(String[] args) {

        args = new String[] {
                "archivo.txt",
                "palabra",
                "fichero_salida.txt"
        };

        String archivoEntradaPath = args[0];
        String palabraABuscar = args[1];
        String archivoSalidaPath = args[2];

        Path archivoEntrada = Paths.get(archivoEntradaPath);
        Path archivoSalida = Paths.get(archivoSalidaPath);

        try {
            Operaciones.contarPalabras(palabraABuscar, archivoEntrada, archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + archivoEntrada);
        }
    }
}
