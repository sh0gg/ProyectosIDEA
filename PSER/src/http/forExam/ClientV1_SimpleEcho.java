import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * CLIENTE V1:
 * - Se conecta al servidor
 * - Envía líneas por writeUTF()
 * - Recibe respuesta por readUTF()
 */
public class ClientV1_SimpleEcho {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 5000;

        try (Socket socket = new Socket(HOST, PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado a " + HOST + ":" + PORT);

            while (true) {
                String msg = sc.nextLine();
                out.writeUTF(msg);
                out.flush();

                String resp = in.readUTF();
                System.out.println("Servidor dice: " + resp);

                if (msg.equalsIgnoreCase("fin")) break;
            }
        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}