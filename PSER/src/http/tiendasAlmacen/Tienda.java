package http.tiendasAlmacen;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Tienda {

    private final List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Tienda tienda = new Tienda();
        tienda.productos.add(new Producto("PAN","Pantalón", 0));
        tienda.productos.add(new Producto("CAM","Camiseta", 0));
        tienda.productos.add(new Producto("ZAP","Zapatos", 0));

        String servidor = "localhost";
        int puerto = 7;

        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket(servidor, puerto);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        System.out.println("Conectado con el almacén");

        boolean cerrar = false;
        while (!cerrar) {
            System.out.println("ORDEN (PEDIR/DEVOLVER/INFO/FIN):");
            String orden = sc.nextLine().trim().toUpperCase();
            String mensaje = "";

            switch (orden) {
                case "PEDIR":
                    System.out.println("Código producto:");
                    String codP = sc.nextLine().trim().toUpperCase();
                    System.out.println("Cantidad:");
                    int cantP = Integer.parseInt(sc.nextLine());
                    mensaje = "PEDIR " + codP + " " + cantP;
                    break;

                case "DEVOLVER":
                    System.out.println("Código producto:");
                    String codD = sc.nextLine().trim().toUpperCase();
                    System.out.println("Cantidad:");
                    int cantD = Integer.parseInt(sc.nextLine());
                    mensaje = "DEVOLVER " + codD + " " + cantD;
                    break;

                case "INFO":
                    mensaje = "INFO";
                    break;

                case "FIN":
                    mensaje = "FIN";
                    cerrar = true;
                    break;

                default:
                    System.out.println("Orden desconocida.");
                    continue;
            }

            out.writeUTF(mensaje);
            String respuesta = in.readUTF();
            System.out.println("Almacén responde:\n" + respuesta);
        }

        socket.close();
        System.out.println("Tienda desconectada.");
    }
}
