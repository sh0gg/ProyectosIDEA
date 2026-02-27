package http.examenSocketsDavidBesadaRamilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {


        String servidor = "localhost";
        int puerto = 7;

        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket(servidor, puerto);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // ===== IDENTIFICACIÓN =====
        boolean nombreAceptado = false;
        String nombre = "";

        while (!nombreAceptado) {
            System.out.print("Introduce nombre del jugador: ");
            nombre = sc.nextLine().trim().toUpperCase();

            out.writeUTF("HELLO " + nombre);
            String respuesta = in.readUTF();

            if (respuesta.equals("OK")) {
                nombreAceptado = true;
                System.out.println("Conectado como " + nombre);
            } else {
                System.out.println("Nombre en uso, prueba otro.");
            }
        }

        String mensaje;
        String respuesta;

        while (true) {
            mensaje = in.readUTF();
            if (mensaje.equals("cerrar")) {
                System.out.println("Cerrando (cliente)");
                break;
            } else {
                System.out.println(mensaje);
                String[] partes = mensaje.split(" ");
                if (!partes[0].equalsIgnoreCase("EMPEZANDO") && !partes[0].equalsIgnoreCase("-")) {
                    respuesta = sc.nextLine();
                    out.writeUTF(respuesta);
                }
            }
        }

        socket.close();
        System.out.println("Cliente desconectado.");
    }
}