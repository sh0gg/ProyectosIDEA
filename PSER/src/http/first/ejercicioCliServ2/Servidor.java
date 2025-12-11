package http.first.ejercicioCliServ2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {

    public static volatile boolean shutdownSolicitado = false;

    // Información del cliente
    static class ClienteInfo {
        int id;
        String nombre;
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        ClienteInfo(int id, Socket socket, DataInputStream in, DataOutputStream out) {
            this.id = id;
            this.socket = socket;
            this.in = in;
            this.out = out;
            this.nombre = "Desconocido";
        }
    }

    public static List<ClienteInfo> clientes =
            Collections.synchronizedList(new ArrayList<>());

    static int contadorIds = 1;

    public static void main(String[] args) throws IOException {
        int puerto = 7;
        ServerSocket serverSocket = new ServerSocket(puerto);

        System.out.println("Servidor arriba");

        while (!shutdownSolicitado) {
            Socket socket = serverSocket.accept();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            ClienteInfo cliente = new ClienteInfo(contadorIds++, socket, in, out);
            clientes.add(cliente);

            System.out.println("Conectado cliente ID=" + cliente.id + " desde " + socket.getRemoteSocketAddress());

            // Hilo por cliente
            new Thread(() -> atenderCliente(cliente)).start();
        }
    }

    // ---- LÓGICA DEL CLIENTE ----
    private static void atenderCliente(ClienteInfo cliente) {
        try {
            boolean salir = false;

            // PRIMER MENSAJE = nombre
            String primerMensaje = cliente.in.readUTF();
            if (primerMensaje.startsWith("NOMBRE:")) {
                cliente.nombre = primerMensaje.substring(7);
                System.out.println("Cliente " + cliente.id + " es " + cliente.nombre);
                enviarATodos("[SERVIDOR] " + cliente.nombre + " se ha unido al chat.");
            }

            // BUCLE de mensajes
            while (!salir && !shutdownSolicitado) {

                String str = cliente.in.readUTF();

                // FIN → cerrar cliente
                if (str.equalsIgnoreCase("fin")) {
                    salir = true;
                    enviarATodos("[SERVIDOR] " + cliente.nombre + " se ha desconectado.");
                }

                // SHUTDOWN → cerrar servidor
                else if (str.equalsIgnoreCase("shutdown")) {
                    shutdownSolicitado = true;
                    System.out.println("Shutdown solicitado!");
                    cerrarTodos();
                    return;
                }

                // DM → mensaje privado
                else if (str.equalsIgnoreCase("dm")) {
                    procesarDM(cliente);
                }

                // Mensaje público
                else {
                    enviarATodos(cliente.nombre + ": " + str);
                    System.out.println("Público (" + cliente.nombre + "): " + str);
                }
            }

        } catch (IOException ignored) {
        }
        finally {
            try { cliente.socket.close(); } catch (IOException ignored) {}
            clientes.remove(cliente);

            if (clientes.isEmpty() && !shutdownSolicitado) {
                System.out.println("Último cliente desconectado. Servidor abajo.");
                System.exit(0);
            }
        }
    }

    // ---- PROCESAR DM ----
    private static void procesarDM(ClienteInfo origen) throws IOException {

        // Enviar lista de clientes
        origen.out.writeUTF("Clientes conectados:");
        synchronized (clientes) {
            for (ClienteInfo c : clientes) {
                origen.out.writeUTF("  ID: " + c.id + " - " + c.nombre);
            }
        }

        // Pedir ID destino
        origen.out.writeUTF("Introduce el ID del destinatario:");
        int idDestino = Integer.parseInt(origen.in.readUTF());

        ClienteInfo destino = null;
        synchronized (clientes) {
            for (ClienteInfo c : clientes) {
                if (c.id == idDestino) {
                    destino = c;
                    break;
                }
            }
        }

        if (destino == null) {
            origen.out.writeUTF("ID no válido.");
            return;
        }

        // Pedir mensaje privado
        origen.out.writeUTF("Escribe el mensaje privado:");
        String mensaje = origen.in.readUTF();

        // Enviar mensaje al destino
        destino.out.writeUTF("[DM de " + origen.nombre + "]: " + mensaje);

        // Confirmación al emisor
        origen.out.writeUTF("DM enviado a " + destino.nombre);
    }

    // ---- ENVÍO GLOBAL ----
    private static void enviarATodos(String msg) {
        synchronized (clientes) {
            for (ClienteInfo c : clientes) {
                try {
                    c.out.writeUTF(msg);
                } catch (IOException ignored) {}
            }
        }
    }

    // ---- SHUTDOWN ----
    public static void cerrarTodos() {
        synchronized (clientes) {
            for (ClienteInfo c : clientes) {
                try { c.socket.close(); } catch (IOException ignored) {}
            }
            clientes.clear();
        }
        System.out.println("Servidor abajo.");
        System.exit(0);
    }
}
