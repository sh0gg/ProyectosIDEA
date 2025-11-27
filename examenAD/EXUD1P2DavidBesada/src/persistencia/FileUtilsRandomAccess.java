package persistencia;

import clases.Fotografia;
import clases.Fotografo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class FileUtilsRandomAccess {

    private static final int TAM_REGISTRO = 1024;

    public static int contarRegistros(String ruta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long longitud = raf.length();
            return (int) (longitud / TAM_REGISTRO);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void leerRegistro(String ruta, int nRegistro) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            raf.seek(nRegistro * TAM_REGISTRO);
            boolean borrado = raf.readBoolean();
            if (borrado) {
                return;
            }
            int codigo = raf.readInt();
            String licencia = raf.readUTF().trim();
            String nombre = raf.readUTF().trim();
            String estudio = raf.readUTF().trim();
            int numFotografias = raf.readInt();
            List<Fotografia> fotografias = new ArrayList<>();
            for (int i = 0; i < numFotografias; i++) {
                String titulo = raf.readUTF();
                LocalDate fecha = LocalDate.ofEpochDay(raf.readLong());
                String tipo = raf.readUTF();
                double tamanoMB = raf.readDouble();
                Fotografia foto = new Fotografia(titulo, fecha, tipo, tamanoMB);
                fotografias.add(foto);
            }
            Fotografo fotografo = new Fotografo(borrado ,codigo, licencia, nombre, estudio, numFotografias, fotografias);
            System.out.println(fotografo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
