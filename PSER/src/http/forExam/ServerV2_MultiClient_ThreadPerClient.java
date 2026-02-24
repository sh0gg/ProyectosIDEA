import java.io.*;
import java.net.*;

/**
 * SERVER V2:
 * - accept() en bucle
 * - por cada cliente -> new Thread(handler)
 * - eco + fin
 */
public class ServerV2_MultiClient_ThreadPerClient {

    public static void main(String[] args) {
        final int PORT = 5000;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor multi-cliente arriba en " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente: " + socket.getRemoteSocketAddress());

                // Un hilo por cliente
                new Thread(() -> handleClient(socket)).start();
            }

        } catch (IOException e) {
            System.out.println("Error servidor: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        // try-with-resources para asegurar cierre del socket aunque haya error
        try (Socket s = socket;
             DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            while (true) {
                String msg = in.readUTF();

                if (msg.equalsIgnoreCase("fin")) {
                    out.writeUTF("bye");
                    out.flush();
                    break;
                }

                out.writeUTF("ECO(" + Thread.currentThread().getName() + "): " + msg);
                out.flush();
            }

        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + e.getMessage());
        }
    }
}