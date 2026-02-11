package http.REST;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class RestInsertarProvinciaClientes {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/clientes/rest.php/provincias/";

        System.out.println("Que provincia quieres añadir?");
        String provincia = sc.nextLine();

        try {
            String parametros =
                    "provincia=" + URLEncoder.encode(provincia, "UTF-8");

            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();

            // le pasamos los parámetros en el cuerpo de la petición
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 201) {
                /* Si en la inserción devolvemos un JSON con la clave generada, aquí deberíamos
                recuperar el JSON y analizarlo para obtenerla por si la necesitamos */
                System.out.println("Inserción correcta");
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }
}
