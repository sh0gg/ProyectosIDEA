package http.examenSocketsDavidBesadaRamilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Servidor {

    static StringBuilder mensajeFinal = new StringBuilder();
    private static volatile boolean shutdown = false;
    private static List<Socket> clientes = Collections.synchronizedList(new ArrayList<>());
    private static Set<String> nombresClientes = Collections.synchronizedSet(new HashSet<>());
    private static final List<String> preguntas = Collections.synchronizedList(new ArrayList<>());
    private static List<String> respuestas = Collections.synchronizedList(new ArrayList<>());
    private static List<String> records = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        int puerto = 7;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor arriba en puerto " + puerto);

        while (!shutdown) {
            Socket socket = serverSocket.accept();
            clientes.add(socket);
            new Thread(() -> conectarCliente(socket)).start();
        }

    }

    private static void conectarCliente(Socket socket) {
        String nombreCliente = null;

        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // ===== IDENTIFICACIÓN =====
            while (true) {
                String saludo = in.readUTF(); // HELLO nombre
                String[] partes = saludo.split(" ", 2);

                if (!partes[0].equals("HELLO")) {
                    out.writeUTF("ERROR");
                    continue;
                }

                nombreCliente = partes[1];

                synchronized (nombresClientes) {
                    if (nombresClientes.contains(nombreCliente)) {
                        out.writeUTF("ERROR");
                    } else {
                        nombresClientes.add(nombreCliente);
                        out.writeUTF("OK");
                        System.out.println("Conectó participante " + nombreCliente +
                                " desde " + socket.getRemoteSocketAddress());
                        break;
                    }
                }
            }

            boolean cerrar = false;

            while (!cerrar) {
                String mensaje = "nuncaLeerasEsto"; // lo siento, no estaba funcionando. ACTUALIZACION, ya no es necesario, lo arreglé pero lo dejo como easter egg.
                String pregunta = "";
                String respuesta = "";

                while (true) {
                    out.writeUTF("Introducir pregunta o enviar un mensaje vacío para empezar a responder. Numero de preguntas = " + preguntas.size() + ".");

                    mensaje = in.readUTF();

                    if (mensaje.isEmpty()) {
                        break;
                    }

                    pregunta = mensaje;
                    out.writeUTF("Pregunta introducida. Introducir respuesta: ");
                    mensaje = in.readUTF();
                    if (!mensaje.isEmpty()) {
                        respuesta = mensaje;
                    } else {
                        break;
                    }
                    preguntas.add(pregunta);
                    respuestas.add(respuesta);
                }

                out.writeUTF("Empezando cuestionario de " + preguntas.size() + " preguntas!!");
                String respuestaJugador;
                int contador = 0;

                for (int i = 0; i < preguntas.size(); i++) {
                    out.writeUTF(preguntas.get(i));
                    respuestaJugador = in.readUTF();
                    if (respuestaJugador.equalsIgnoreCase(respuestas.get(i))) {
                        out.writeUTF("- Has acertado! Seguimos!");
                        contador++;
                    } else {
                        out.writeUTF("- " + respuestaJugador + " no es la respuesta correcta. Has fallado.");
                        break;
                    }
                }

                if (mensajeFinal.isEmpty()) {
                    mensajeFinal.append("- Se acaba el juego. Puedes ver tu puntuacion a continuacion:");
                }
                mensajeFinal.append("\n   - Nombre: ").append(nombreCliente).append(" - Puntuacion: ").append(contador).append("/").append(preguntas.size()+ ".");
                out.writeUTF(mensajeFinal.toString());
                out.writeUTF("cerrar");

            }
        } catch (IOException ignored) {
        } finally {
            if (nombreCliente != null) {
                nombresClientes.remove(nombreCliente);
                System.out.println("Desconectada tienda " + nombreCliente);
            }
            try {
                socket.close();
            } catch (IOException ignored) {
            }
            clientes.remove(socket);
        }
    }
}
