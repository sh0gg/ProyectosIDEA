import java.io.*;
import java.net.*;

/**
 * SERVER V3:
 * - multicliente
 * - login por limite
 * - handshake inicial:
 *      servidor -> boolean loginOk
 * - comandos:
 *      info -> devuelve estado
 *      fin  -> devuelve log y cierra
 */
public class ServerV3_LoginLimit {

    public static void main(String[] args) {
        ConnectionManagerLimit manager = new ConnectionManagerLimit(ConfigLoginLimit.MAX_CLIENTS);

        try (ServerSocket serverSocket = new ServerSocket(ConfigLoginLimit.PORT)) {
            System.out.println("Servidor arriba con limite = " + ConfigLoginLimit.MAX_CLIENTS);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handle(socket, manager)).start();
            }

        } catch (IOException e) {
            System.out.println("Servidor caído: " + e.getMessage());
        }
    }

    private static void handle(Socket socket, ConnectionManagerLimit manager) {
        String userId = socket.getRemoteSocketAddress().toString(); // identificador simple

        try (Socket s = socket;
             DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            // 1) LOGIN
            boolean loginOk = manager.login(userId);

            // 2) HANDSHAKE: aviso al cliente
            out.writeBoolean(loginOk);
            out.flush();

            // Si no pudo entrar, cerramos
            if (!loginOk) return;

            // 3) BUCLE DE COMANDOS
            while (true) {
                String cmd = in.readUTF();

                if (cmd.equalsIgnoreCase(ConfigLoginLimit.CMD_INFO)) {
                    out.writeUTF("Activos: " + manager.getActiveClients());
                    out.flush();
                } else if (cmd.equalsIgnoreCase(ConfigLoginLimit.CMD_FIN)) {
                    out.writeUTF(manager.getLog(userId)); // manda el log final
                    out.flush();
                    break;
                } else {
                    out.writeUTF("Comando no reconocido");
                    out.flush();
                }
            }

        } catch (IOException e) {
            // Desconexión inesperada
        } finally {
            manager.logout(userId);
        }
    }
}