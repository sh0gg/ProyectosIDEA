import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * CLIENTE V2:
 * - Igual que V1 pero pensado para multicliente
 */
public class ClientV2_SimpleLoop {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 5000;

        try (Socket socket = new Socket(HOST, PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado.");

            while (true) {
                String msg = sc.nextLine();
                out.writeUTF(msg);
                out.flush();

                String resp = in.readUTF();
                System.out.println(resp);

                if ("fin".equalsIgnoreCase(msg)) break;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}