package ficherosSecuencialesTexto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LecturaTextoConTildes {
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/readersAndWriters/texto.txt"), StandardCharsets.ISO_8859_1)) {

            int cad;
            while ((cad = isr.read()) != -1) {
                System.out.print((char) cad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al abrir el fichero");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");
        }
    }
}
