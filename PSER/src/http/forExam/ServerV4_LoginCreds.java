import java.io.*;
import java.net.*;

/**
 * SERVER V4:
 * - multicliente
 * - login con user+pass
 * - si login OK => bucle de comandos (info/salir)
 */
public class ServerV4_LoginCreds {

    public static void main(String[] args) {
        UserManagerCreds manager = new UserManagerCreds();

        try (ServerSocket serverSocket = new ServerSocket(ConfigLoginCreds.PORT)) {
            System.out.println("Servidor login-creds arriba");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handle(socket, manager)).start();
            }

        } catch (IOException e) {
            System.out.println("Servidor caído: " + e.getMessage());
        }
    }

    private static void handle(Socket socket, UserManagerCreds manager) {
        String user = "";

        try (Socket s = socket;
             DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            // 1) LOGIN: el cliente SIEMPRE envía user y pass primero
            user = in.readUTF();
            String pass = in.readUTF();

            int err = manager.login(user, pass);
            boolean ok = (err == 0);

            // 2) RESPUESTA LOGIN
            out.writeBoolean(ok);
            if (!ok) out.writeInt(err);
            out.flush();

            if (!ok) return; // cerrar conexión

            // 3) COMANDOS
            while (true) {
                String cmd = in.readUTF();

                if (cmd.equalsIgnoreCase(ConfigLoginCreds.CMD_INFO)) {
                    // El cliente puede preguntar por usuarios:
                    // aquí simplificamos: devuelve info de sí mismo
                    out.writeUTF(manager.info(user));
                    out.flush();
                } else if (cmd.equalsIgnoreCase(ConfigLoginCreds.CMD_SALIR)) {
                    out.writeUTF("bye");
                    out.flush();
                    break;
                } else {
                    out.writeUTF("Comando desconocido");
                    out.flush();
                }
            }

        } catch (IOException e) {
            // desconexión
        } finally {
            if (!user.isBlank()) manager.logout(user);
        }
    }
}