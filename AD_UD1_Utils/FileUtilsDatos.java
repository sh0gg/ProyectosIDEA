package utils;

import java.io.*;

/**
 * UTILIDADES PARA FICHEROS DE DATOS BINARIOS (PRIMITIVOS)
 * -------------------------------------------------------
 * Usa DataOutputStream / DataInputStream para escribir y leer:
 *  - int, double, boolean, UTF (String), etc.
 *
 * Muy habitual en ejercicios de examen cuando piden:
 *  - "Crear un fichero binario de datos"
 *  - "Guardar una lista de números"
 *  - "Leer todos los registros del fichero de datos"
 */
public class FileUtilsDatos {

    // =====================================================
    // 1. ESCRIBIR PRIMITIVOS A UN FICHERO (SOBREESCRIBE)
    // =====================================================
    public static void escribirDatos(String ruta) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta))) {

            // Ejemplos de datos escritos EN ORDEN
            dos.writeInt(10);
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
            dos.writeUTF("Hola mundo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 2. AÑADIR PRIMITIVOS A UN FICHERO (append = true)
    // =====================================================
    public static void anadirDatos(String ruta, int numero, String texto) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta, true))) {

            // Importante: RESPETAR SIEMPRE EL MISMO ORDEN DE ESCRITURA
            dos.writeInt(numero);
            dos.writeUTF(texto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 3. LEER PRIMITIVOS DESDE UN FICHERO
    //    (Se lee mientras no salte EOFException)
    // =====================================================
    public static void leerDatos(String ruta) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {

            while (true) {
                // Ejemplo: leer en el MISMO orden en el que se escribió
                int numero = dis.readInt();
                double valor = dis.readDouble();
                boolean ok = dis.readBoolean();
                String texto = dis.readUTF();

                System.out.println("Número: " + numero);
                System.out.println("Valor: " + valor);
                System.out.println("Boolean: " + ok);
                System.out.println("Texto: " + texto);
            }

        } catch (EOFException e) {
            // Se llega aquí cuando se acaban los datos del fichero
            System.out.println("Fin de fichero de datos.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 4. LEER PARES (int, String) HASTA EOF
    //    ÚTIL CUANDO HAS USADO anadirDatos()
    // =====================================================
    public static void leerParesIntString(String ruta) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {

            while (true) {
                int numero = dis.readInt();
                String texto = dis.readUTF();
                System.out.println("Registro -> número: " + numero + ", texto: " + texto);
            }

        } catch (EOFException e) {
            System.out.println("Fin de fichero (pares int-String).");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
