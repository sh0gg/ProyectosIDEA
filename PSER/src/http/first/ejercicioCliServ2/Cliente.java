package http.first.ejercicioCliServ2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Cliente {
    public static void main(String[] args) throws IOException {

        String servidor = "localhost", FIN = "fin", mensaje = "", SHUTDOWN = "shutdown";
        int puerto = 7; // puerto ECHO

        Scanner sc = new Scanner(System.in);

        // --- NUEVO: pedir nombre ---
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();

        // Usamos try-with-resources para gestionar automáticamente el cierre de recursos
        try (Socket socket = new Socket(servidor, puerto);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Conectado con el servidor");

            // Enviar el nombre al servidor como primer mensaje
            out.writeUTF("NOMBRE:" + nombre);

            // Nuevo hilo para recibir mensajes
            Thread recibirMensajes = new Thread(() -> {
                try {
                    while (true) {
                        // Recibimos mensajes continuamente
                        String strRecibido = in.readUTF();
                        System.out.println(strRecibido);
                    }
                } catch (IOException e) {
                    System.out.println("Error al recibir mensaje.");
                }
            });

            // Usamos AtomicReference para que 'mensaje' sea final efectiva
            AtomicReference<String> mensajeRef = new AtomicReference<>("");

            // Nuevo hilo para enviar mensajes
            Thread enviarMensajes = new Thread(() -> {
                try {
                    boolean[] seguimos = {true}; // Usamos un array para modificar la variable en la lambda
                    while (seguimos[0]) {
                        // LEEMOS el mensaje
                        mensajeRef.set(sc.nextLine());
                        // ENVIAMOS
                        out.writeUTF(mensajeRef.get());
                        System.out.println(nombre + " envía: " + mensajeRef.get());

                        // Condiciones de salida
                        if (mensajeRef.get().equalsIgnoreCase(SHUTDOWN) || mensajeRef.get().equalsIgnoreCase(FIN)) {
                            seguimos[0] = false;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("¡EEEEEH, que ya no conectas con el servidor!");
                }
            });

            // Iniciar ambos hilos
            recibirMensajes.start();
            enviarMensajes.start();

        } catch (IOException ex) {
            System.out.println("Problemas conectando con servidor " + servidor + ":" + puerto);
        }
    }
}
