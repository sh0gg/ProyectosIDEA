package utils;

import java.io.*;

/**
 * UTILIDADES CON RandomAccessFile
 * -------------------------------
 * RandomAccessFile permite:
 *  - Leer y escribir en posiciones concretas (seek)
 *  - Simular "registros" de tamaño fijo
 *
 * MUY TÍPICO DE EXAMEN:
 *  - Fichero de registros de longitud fija (id, nombre, sueldo, etc.)
 *  - Acceso directo al registro N (sin leer todo)
 */
public class FileUtilsRandomAccess {

    // Para ejemplo: definimos un "registro" con:
    //  - int id (4 bytes)
    //  - String nombre (20 caracteres -> 40 bytes en UTF-16 si fuese writeChars)
    // Aquí vamos a trabajar con un tamaño fijo simple.
    private static final int TAM_NOMBRE = 20; // nº de caracteres
    private static final int TAM_REGISTRO = 4 + 2 * TAM_NOMBRE; // int (4) + 20 chars * 2 bytes

    // =====================================================
    // 1. ESCRIBIR UN REGISTRO EN POSICIÓN N
    //    (sobreescribe el registro N)
    // =====================================================
    public static void escribirRegistro(String ruta, int nRegistro, int id, String nombre) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {

            // Calcular posición del registro N
            long posicion = (long) nRegistro * TAM_REGISTRO;
            raf.seek(posicion);

            // Escribir ID
            raf.writeInt(id);

            // Escribir nombre como cadena de longitud fija (TAM_NOMBRE)
            StringBuffer buffer = new StringBuffer(nombre);
            buffer.setLength(TAM_NOMBRE); // rellena con espacios si es corto
            raf.writeChars(buffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 2. LEER UN REGISTRO EN POSICIÓN N
    // =====================================================
    public static void leerRegistro(String ruta, int nRegistro) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {

            long posicion = (long) nRegistro * TAM_REGISTRO;
            if (posicion >= raf.length()) {
                System.out.println("No existe ese registro (fuera de fichero).");
                return;
            }

            raf.seek(posicion);

            int id = raf.readInt();

            char[] nombreChars = new char[TAM_NOMBRE];
            for (int i = 0; i < TAM_NOMBRE; i++) {
                nombreChars[i] = raf.readChar();
            }
            String nombre = new String(nombreChars).trim();

            System.out.println("Registro " + nRegistro + ": id=" + id + ", nombre=" + nombre);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 3. OBTENER NÚMERO DE REGISTROS EXISTENTES
    // =====================================================
    public static int contarRegistros(String ruta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            long longitud = raf.length();
            return (int) (longitud / TAM_REGISTRO);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // =====================================================
    // 4. EJEMPLO RÁPIDO PARA PROBAR (main)
    //    *NO es obligatorio para el examen, pero ayuda a entenderlo*
    // =====================================================
    public static void main(String[] args) {
        String ruta = "registros.dat";

        // Escribir tres registros
        escribirRegistro(ruta, 0, 1, "Ana");
        escribirRegistro(ruta, 1, 2, "Pepe");
        escribirRegistro(ruta, 2, 3, "Lucia");

        // Leer el segundo registro
        leerRegistro(ruta, 1);

        // Contar registros
        int total = contarRegistros(ruta);
        System.out.println("Total de registros: " + total);
    }
}
