package http.first.ejercicioCliServ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String servidor = "localhost", FIN = "fin", mensaje = "", SHUTDOWN = "shutdown";
        int puerto = 7; // puerto ECHO

        Scanner sc = new Scanner(System.in);
        /* comentado para trabajar con localhost
        System.out.println("Servidor? ");
        servidor=sc.nextLine(); */

        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        try {
            socket = new Socket(servidor, puerto);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Problemas conectando con servidor " + servidor + ":" + puerto);
            return;
        }

        System.out.println("Conectado con el servidor");
        boolean seguimos = true;
        while (seguimos) {
            if (mensaje.equalsIgnoreCase(SHUTDOWN) || mensaje.equalsIgnoreCase(FIN)) {
                seguimos = false;
                socket.close();
                return;
            }
            try {
                // ENVIAMOS ...
                mensaje = sc.nextLine();
                out.writeUTF(mensaje);
                System.out.println("Cliente envía: " + mensaje);
                // RECIBIMOS ...
                String strRecibido = in.readUTF();
                System.out.println("Cliente recibe: " + strRecibido);
                if (!mensaje.equals(strRecibido))
                    System.out.println("Ha ocurrido un problema: las cadenas son distintas.");
                System.out.println("**************************************");
            } catch (IOException e) {
                System.out.println("¡EEEEEH, que ya no conectas con el servidor!");
                socket.close();
                return;
            }
        }
        socket.close();
    }
}
