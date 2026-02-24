import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * CLIENTE V3:
 * - espera handshake boolean de login
 * - si OK, entra en bucle de comandos
 */
public class ClientV3_LoginLimit {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", ConfigLoginLimit.PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            boolean loginOk = in.readBoolean();
            if (!loginOk) {
                System.out.println("Servidor lleno: login rechazado");
                return;
            }

            System.out.println("Login OK. Escribe 'info' o 'fin'.");

            while (true) {
                String cmd = sc.nextLine();
                out.writeUTF(cmd);
                out.flush();

                String resp = in.readUTF();
                System.out.println(resp);

                if ("fin".equalsIgnoreCase(cmd)) break;
            }

        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}