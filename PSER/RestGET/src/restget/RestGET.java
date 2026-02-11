package restget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestGET {

    public static void main(String[] args) {

        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/clientes/rest.php/clientes";
        try {
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while ((linea = bufferIn.readLine()) != null)
                    json += linea;
                bufferIn.close();
                
                /* Analizamos el JSON devuelto, que sabemos que es un array de objetos cliente */
                
                JSONArray datos = new JSONArray(json);
                for(int i=0; i < datos.length(); i++) {
                    JSONObject cliente = datos.getJSONObject(i);
                    String nombre=cliente.getString("nombre");
                    boolean vip=(cliente.getString("vip").equals("1"));
                    int codProvincia=cliente.getInt("codProvincia");
                    System.out.printf("%s de %d %s es VIP\n",nombre,codProvincia,vip?"":"no");
                }
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }

    }

}
