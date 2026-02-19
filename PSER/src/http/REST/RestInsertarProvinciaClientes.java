package http.REST;

import org.json.JSONObject;

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
                Scanner lector = new Scanner(con.getInputStream());
                String respuestaJson = "";
                while (lector.hasNext()) {
                    respuestaJson += lector.nextLine();
                }
                lector.close();

                System.out.println("Inserción correcta.");
                System.out.println("Respuesta del servidor: " + respuestaJson);

                 JSONObject obj = new JSONObject(respuestaJson);
                 int codProvincia = obj.getInt("id");

                System.out.println("Añadir cliente.");
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();
                System.out.println("VIP?: ");
                int vip = sc.nextInt();




            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }
}
