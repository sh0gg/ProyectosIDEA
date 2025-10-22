package clase2025_10_17;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Conversacion {
    Thread pescador1;
    Thread pescador2;
    String archivo;

    public Conversacion(String archivo) {
        this.archivo = archivo;
        int contador = 0;
        leerArchivoLinea(archivo,contador);
    }

    private String leerArchivoLinea(String f, int contador) {
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Conversacion conversacion = new Conversacion("D:\\dbesarami\\IDEAProjects\\PSER\\src\\clase2025_10_17\\hola.txt");
    }


}
