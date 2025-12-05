package http.first.ejercicioCliServ;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    public static volatile boolean shutdownSolicitado = false;
    public static List<Socket> clientes = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        int puerto = 7;
        ServerSocket serverSocket = new ServerSocket(puerto);

        System.out.println("Servidor arriba");

        while (!shutdownSolicitado) {
            Socket socket = serverSocket.accept();
            clientes.add(socket);

            System.out.println("Ha conectado " + socket.getRemoteSocketAddress());

            // HILO PARA CADA CLIENTE
            new Thread(() -> {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                    boolean salir = false;

                    while (!salir && !shutdownSolicitado) {
                        String str = in.readUTF();
                        out.writeUTF(str);

                        if (str.equalsIgnoreCase("fin")) {
                            salir = true;

                        } else if (str.equalsIgnoreCase("shutdown")) {
                            shutdownSolicitado = true;
                            System.out.println("Shutdown solicitado!");
                            cerrarTodos();
                        } else {
                            System.out.println("Servidor retransmite: " + str);
                            System.out.println("****************************");
                        }
                    }

                } catch (IOException ignored) {
                } finally {
                    try { socket.close(); } catch (IOException ignored) {}
                    clientes.remove(socket);

                    // Si ya no quedan clientes = apagar servidor
                    if (clientes.isEmpty() && !shutdownSolicitado) {
                        System.out.println("Último cliente desconectado. Servidor abajo.");
                        System.exit(0);
                    }
                }

            }).start();
        }
    }

    // Cerrar todos los sockets
    public static void cerrarTodos() {
        synchronized (clientes) {
            for (Socket s : clientes) {
                try { s.close(); } catch (IOException ignored) {}
            }
            clientes.clear();
        }
        System.out.println("Servidor abajo.");
        System.exit(0);
    }
}
