package utils;

import java.io.*;

/**
 * UTILIDADES PARA ARCHIVOS BINARIOS
 * ---------------------------------
 * Incluye:
 *  - Copia de binarios (imágenes, PDFs...)
 *  - Lectura con buffer de bytes
 *  - Escritura binaria
 *  - Serialización (guardar y cargar objetos)
 */
public class FileUtilsBinario {

    // =====================================================
    // 1. COPIAR ARCHIVOS BINARIOS (imagen, pdf...)
    // =====================================================
    public static void copiarBinario(String origen, String destino) {

        try (FileInputStream fis = new FileInputStream(origen);
             FileOutputStream fos = new FileOutputStream(destino)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 2. LEER ARCHIVO BINARIO A BYTES
    // =====================================================
    public static byte[] leerBytes(String ruta) {
        try {
            File file = new File(ruta);
            byte[] data = new byte[(int) file.length()];

            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(data);
            }
            return data;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // =====================================================
    // 3. ESCRIBIR BYTES EN ARCHIVO BINARIO
    // =====================================================
    public static void escribirBytes(String ruta, byte[] datos) {

        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 4. SERIALIZAR OBJETO A ARCHIVO
    // =====================================================
    public static void guardarObjeto(String ruta, Object obj) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 5. LEER OBJETO SERIALIZADO
    // =====================================================
    public static Object cargarObjeto(String ruta) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return ois.readObject();
        } catch (Exception e) { // IOException + ClassNotFound
            e.printStackTrace();
            return null;
        }
    }
}
