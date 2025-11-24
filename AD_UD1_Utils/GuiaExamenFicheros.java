import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

// Clase principal: junta varios ejemplos típicos de examen sobre ficheros
public class GuiaExamenFicheros {

    // ==============================
    // MÉTODO MAIN PARA PROBAR TODO
    // ==============================
    public static void main(String[] args) {

        // Cambia estas rutas según necesites en el examen
        String rutaDirectorio = "data";
        String rutaTexto = "data/entrada.txt";
        String rutaBinarioPersonas = "data/personas.dat";
        String rutaRandomEmpleados = "data/empleados.dat";

        // BLOQUE 1: FICHEROS Y DIRECTORIOS
        listarDirectorioYFiltrarTxt(rutaDirectorio);

        // BLOQUE 2: TEXTO SECUENCIAL
        contarLineasYPalabra(rutaTexto, "java");

        // BLOQUE 3: SERIALIZACIÓN DE OBJETOS
        ejemploSerializacion(rutaBinarioPersonas);

        // BLOQUE 4: RANDOMACCESSFILE (REGISTROS FIJOS)
        ejemploRandomAccess(rutaRandomEmpleados);
    }

    // ======================================================
    // BLOQUE 1: FICHEROS Y DIRECTORIOS (listar y filtrar)
    // ======================================================
    public static void listarDirectorioYFiltrarTxt(String rutaDirectorio) {
        System.out.println("=== BLOQUE 1: Ficheros y directorios ===");

        File dir = new File(rutaDirectorio);

        // 1. Comprobar que existe y es un directorio
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("La ruta no es un directorio válido: " + rutaDirectorio);
            return;
        }

        // 2. Listar todo el contenido
        System.out.println("Contenido del directorio:");
        File[] hijos = dir.listFiles();
        if (hijos == null) return;

        for (File f : hijos) {
            String tipo = f.isDirectory() ? "[DIR] " : "[FILE]";
            System.out.println(tipo + " " + f.getName());
        }

        // 3. Mostrar solo los .txt
        System.out.println("\nSolo archivos .txt:");
        for (File f : hijos) {
            if (f.isFile() && f.getName().toLowerCase().endsWith(".txt")) {
                System.out.println(f.getName());
            }
        }

        System.out.println();
    }

    // ======================================================
    // BLOQUE 2: FICHEROS DE TEXTO (contar líneas y palabra)
    // ======================================================
    public static void contarLineasYPalabra(String rutaFichero, String palabra) {
        System.out.println("=== BLOQUE 2: Texto secuencial ===");

        int contadorLineas = 0;
        int contadorPalabraTotal = 0;
        String palabraLower = palabra.toLowerCase();

        // 1. Abrir el fichero con BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {

            String linea;
            // 2. Leer línea a línea
            while ((linea = br.readLine()) != null) {
                contadorLineas++;

                // 3. Contar apariciones de la palabra en esta línea (versión simple)
                String lineaLower = linea.toLowerCase();
                int index = 0;
                while ((index = lineaLower.indexOf(palabraLower, index)) != -1) {
                    contadorPalabraTotal++;
                    index = index + palabraLower.length();
                }
            }

            // 4. Mostrar resultados
            System.out.println("Fichero: " + rutaFichero);
            System.out.println("Líneas totales: " + contadorLineas);
            System.out.println("Apariciones de \"" + palabra + "\": " + contadorPalabraTotal);

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + rutaFichero);
        } catch (IOException e) {
            System.out.println("Error de E/S leyendo el archivo");
            e.printStackTrace();
        }

        System.out.println();
    }

    // ======================================================
    // BLOQUE 3: SERIALIZACIÓN (guardar y leer objetos)
    // ======================================================
    public static void ejemploSerializacion(String rutaBinario) {
        System.out.println("=== BLOQUE 3: Serialización de objetos ===");

        // 1. Crear algunas personas de ejemplo
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Ana", 25));
        personas.add(new Persona("Luis", 30));
        personas.add(new Persona("Marta", 22));

        // 2. Guardar la lista en un archivo binario
        guardarPersonas(rutaBinario, personas);

        // 3. Leer la lista desde el archivo binario
        List<Persona> leidas = leerPersonas(rutaBinario);

        // 4. Mostrar lo leído
        System.out.println("Personas leídas de " + rutaBinario + ":");
        for (Persona p : leidas) {
            System.out.println(p);
        }

        System.out.println();
    }

    // Guardar una lista de personas (serialización)
    public static void guardarPersonas(String rutaBinario, List<Persona> personas) {
        // try-with-resources cierra automáticamente el stream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaBinario))) {

            // Escribimos la lista completa (también se podría escribir persona a persona)
            oos.writeObject(personas);

        } catch (IOException e) {
            System.out.println("Error al guardar personas");
            e.printStackTrace();
        }
    }

    // Leer una lista de personas (deserialización)
    @SuppressWarnings("unchecked")
    public static List<Persona> leerPersonas(String rutaBinario) {
        List<Persona> personas = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaBinario))) {

            // Leemos la lista que escribimos antes
            personas = (List<Persona>) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo binario: " + rutaBinario);
        } catch (EOFException e) {
            // Fin de fichero: en este ejemplo no deberíamos llegar porque leemos la lista entera de golpe
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer personas");
            e.printStackTrace();
        }

        return personas;
    }

    // ======================================================
    // BLOQUE 4: RANDOMACCESSFILE (registros de tamaño fijo)
    // ======================================================
    // Definimos un "registro" de empleado con:
    //  - int id (4 bytes)
    //  - nombre fijo de 20 chars (cada char 2 bytes → 40 bytes)
    //  TAMAÑO TOTAL: 44 bytes por registro
    public static final int TAM_NOMBRE = 20;
    public static final int TAM_REGISTRO = 4 + 2 * TAM_NOMBRE; // 4 + 40 = 44

    public static void ejemploRandomAccess(String rutaRandom) {
        System.out.println("=== BLOQUE 4: RandomAccessFile ===");

        // 1. Escribir algunos empleados en posiciones concretas
        escribirEmpleado(rutaRandom, 0, 1, "Ana");
        escribirEmpleado(rutaRandom, 1, 2, "Pepe");
        escribirEmpleado(rutaRandom, 2, 3, "Lucia");

        // 2. Leer un empleado concreto (por índice de registro)
        leerEmpleado(rutaRandom, 1); // debería mostrar la info de "Pepe"

        System.out.println();
    }

    // Escribir un empleado en el "registro" nRegistro
    public static void escribirEmpleado(String rutaRandom, int nRegistro, int id, String nombre) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaRandom, "rw")) {

            // 1. Calcular la posición dentro del fichero
            long posicion = (long) nRegistro * TAM_REGISTRO;
            raf.seek(posicion);

            // 2. Escribir el id
            raf.writeInt(id);

            // 3. Escribir el nombre como cadena de longitud fija
            StringBuffer sb = new StringBuffer(nombre);
            sb.setLength(TAM_NOMBRE); // rellena con espacios si el nombre es corto
            raf.writeChars(sb.toString());

        } catch (IOException e) {
            System.out.println("Error al escribir empleado");
            e.printStackTrace();
        }
    }

    // Leer un empleado del registro nRegistro
    public static void leerEmpleado(String rutaRandom, int nRegistro) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaRandom, "r")) {

            long posicion = (long) nRegistro * TAM_REGISTRO;

            // Comprobamos que la posición esté dentro del fichero
            if (posicion >= raf.length()) {
                System.out.println("No existe ese registro (fuera del fichero)");
                return;
            }

            // 1. Movernos a la posición del registro
            raf.seek(posicion);

            // 2. Leer el id
            int id = raf.readInt();

            // 3. Leer el nombre de longitud fija
            char[] nombreChars = new char[TAM_NOMBRE];
            for (int i = 0; i < TAM_NOMBRE; i++) {
                nombreChars[i] = raf.readChar();
            }
            String nombre = new String(nombreChars).trim(); // quitamos espacios de relleno

            System.out.println("Empleado leído -> id=" + id + ", nombre=" + nombre);

        } catch (IOException e) {
            System.out.println("Error al leer empleado");
            e.printStackTrace();
        }
    }
}

// =============================
// CLASE PERSONA (para binarios)
// =============================
class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;
    // Podrías añadir más campos (LocalDate nacimiento, etc.)

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y setters si los necesitas
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }
}
