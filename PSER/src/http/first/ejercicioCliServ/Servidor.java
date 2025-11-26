package http.first.ejercicioCliServ;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int puerto = 7; // puerto ECHO
        String FIN = "fin";
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor arriba");
        Socket socket = serverSocket.accept(); // Esperamos por un cliente
        SocketAddress clientAddress = socket.getRemoteSocketAddress();
        System.out.println("Ha conectado " + clientAddress);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        boolean salir = false;
        while (!salir) {
            String str = in.readUTF();
            out.writeUTF(str);
            if (str.equalsIgnoreCase(FIN))
                salir = true;
            else {
                System.out.println("Servidor retransmite: " + str);
                System.out.println("****************************");
            }
        }
        socket.close();
        System.out.println("Servidor abajo");
    }
}