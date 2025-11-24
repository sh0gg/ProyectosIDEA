package utils;

import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * UTILIDADES PARA ARCHIVOS DE TEXTO
 * ---------------------------------
 * Métodos listos para copiar/pegar durante el examen.
 * Incluyen:
 *  - Leer línea a línea
 *  - Escribir (sobrescribir y añadir)
 *  - Contar líneas
 *  - Buscar palabras
 *  - Copiar archivos
 *  - Listar directorios
 *  - Trabajar con Files (NIO.2)
 */
public class FileUtilsTexto {

    // =====================================================
    // 1. LEER LÍNEA A LÍNEA (BufferedReader)
    // =====================================================
    public static void leerLineas(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 2. ESCRIBIR (sobrescribe el archivo)
    // =====================================================
    public static void escribir(String ruta, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 3. AÑADIR (append = true)
    // =====================================================
    public static void anadir(String ruta, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 4. CONTAR LÍNEAS DE UN ARCHIVO
    // =====================================================
    public static int contarLineas(String ruta) {
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while (br.readLine() != null) contador++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    // =====================================================
    // 5. BUSCAR UNA PALABRA EN UN ARCHIVO
    // =====================================================
    public static void buscarPalabra(String ruta, String palabra) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int numLinea = 1;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(palabra)) {
                    System.out.println("Encontrado en línea " + numLinea + ": " + linea);
                }
                numLinea++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 6. COPIAR ARCHIVO DE TEXTO
    // =====================================================
    public static void copiar(String origen, String destino) {
        try {
            Files.copy(Paths.get(origen), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 7. LISTAR ARCHIVOS DE UN DIRECTORIO
    // =====================================================
    public static void listarDirectorio(String ruta) {
        File dir = new File(ruta);

        if (dir.exists() && dir.isDirectory()) {
            File[] archivos = dir.listFiles();

            for (File f : archivos) {
                System.out.println((f.isDirectory() ? "[DIR] " : "[FILE] ") + f.getName());
            }
        } else {
            System.out.println("Ruta inválida.");
        }
    }

    // =====================================================
    // 8. LEER TODO EL ARCHIVO (Files.readAllLines)
    // =====================================================
    public static void leerTodo(String ruta) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ruta));
            lineas.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 9. ESCRIBIR TODO EL ARCHIVO (Files.write)
    // =====================================================
    public static void escribirTodo(String ruta, List<String> lineas) {
        try {
            Files.write(Paths.get(ruta), lineas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
