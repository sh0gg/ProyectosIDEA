import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * CLIENTE V4:
 * - envía user+pass
 * - recibe boolean ok y si falla, int err
 * - luego comandos: info / salir
 */
public class ClientV4_LoginCreds {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", ConfigLoginCreds.PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Usuario: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            // 1) ENVÍA CREDENCIALES
            out.writeUTF(user);
            out.writeUTF(pass);
            out.flush();

            // 2) RECIBE RESULTADO
            boolean ok = in.readBoolean();
            if (!ok) {
                int err = in.readInt();
                if (err == ConfigLoginCreds.ERR_CREDENCIALES) System.out.println("Credenciales incorrectas");
                else if (err == ConfigLoginCreds.ERR_YA_CONECTADO) System.out.println("Usuario ya conectado");
                else System.out.println("Login fallido");
                return;
            }

            System.out.println("Login OK. Comandos: info, salir");

            // 3) BUCLE DE COMANDOS
            while (true) {
                String cmd = sc.nextLine();
                out.writeUTF(cmd);
                out.flush();

                String resp = in.readUTF();
                System.out.println(resp);

                if ("salir".equalsIgnoreCase(cmd)) break;
            }

        } catch (IOException e) {
            System.out.println("Error cliente: " + e.getMessage());
        }
    }
}