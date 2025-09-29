package readersAndWriters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecturaTexto {
    public static void main(String[] args) {
        FileReader fr = null;

        try {
            int cad;
            fr = new FileReader("src/readersAndWriters/texto.txt");
            while ((cad = fr.read()) != -1) {
                System.out.print((char) cad);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
        } catch (IOException e) {
            System.out.println("Error en el archivo");
        }  finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println("Error en el archivo");
                }
            }
        }
    }
}
