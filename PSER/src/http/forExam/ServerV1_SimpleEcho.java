import java.io.*;
import java.net.*;

/**
 * SERVER V1 (simple):
 * - Abre un ServerSocket en un puerto
 * - Acepta 1 cliente
 * - Lee Strings con DataInputStream.readUTF()
 * - Responde con writeUTF() (echo)
 * - Cierra socket y termina
 */
public class ServerV1_SimpleEcho {
    public static void main(String[] args) {
        final int PORT = 5000;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor arriba en puerto " + PORT);

            // accept() BLOQUEA hasta que un cliente se conecte
            try (Socket clientSocket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                 DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                System.out.println("Cliente conectado: " + clientSocket.getRemoteSocketAddress());

                // Bucle de conversación
                while (true) {
                    String msg = in.readUTF(); // recibe
                    if (msg.equalsIgnoreCase("fin")) {
                        out.writeUTF("bye");
                        out.flush();
                        break;
                    }

                    out.writeUTF("ECO: " + msg); // responde
                    out.flush();
                }
            }

            System.out.println("Servidor finalizado");
        } catch (IOException e) {
            System.out.println("Error servidor: " + e.getMessage());
        }
    }
}