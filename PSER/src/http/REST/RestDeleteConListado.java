package http.REST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestDeleteConListado {

    static Scanner sc = new Scanner(System.in);
    static String baseStrURL = "http://localhost/clientes/rest.php/";

    public static void main(String[] args) {
        System.out.println("¿Qué deseas eliminar? (1. Clientes / 2. Provincias)");
        int opcion = Integer.parseInt(sc.nextLine());
        String recurso = (opcion == 1) ? "clientes" : "provincias";

        // 1. LISTAR antes de borrar
        System.out.println("\n--- Listado actual de " + recurso + " ---");
        listarRecurso(recurso);

        // 2. PEDIR ID
        System.out.println("\nIntroduce el ID del " + (opcion == 1 ? "cliente" : "provincia") + " a borrar:");
        String id = sc.nextLine();

        // 3. EJECUTAR DELETE
        ejecutarDelete(recurso, id);
    }

    private static void listarRecurso(String recurso) {
        try {
            URL url = new URL(baseStrURL + recurso);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() == 200) {
                Scanner lector = new Scanner(con.getInputStream());
                while (lector.hasNext()) {
                    System.out.println(lector.nextLine());
                }
                lector.close();
            }
            con.disconnect();
        } catch (IOException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    private static void ejecutarDelete(String recurso, String id) {
        try {
            URL url = new URL(baseStrURL + recurso + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            int code = con.getResponseCode();
            if (code == 204 || code == 200) {
                System.out.println(">> Borrado correctamente.");
            } else {
                System.out.println(">> Error al borrar. Código: " + code);
            }
            con.disconnect();
        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}