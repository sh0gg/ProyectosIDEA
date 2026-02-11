/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.REST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author usuario
 */
public class RestDELETE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int codCliente=2;
        
        URL url = null;
        HttpURLConnection con = null;
        String strURL = "http://localhost/clientes/rest.php/clientes/"+codCliente;
        try {
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setDoOutput(true);
            con.connect();
            if (con.getResponseCode() == 204)
                System.out.printf("Cliente eliminado");
            else
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }

    }
   
}
