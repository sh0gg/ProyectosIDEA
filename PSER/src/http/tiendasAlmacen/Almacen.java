package http.tiendasAlmacen;

import java.io.*;
import java.net.*;
import java.util.*;

public class Almacen {

    public static volatile boolean shutdown = false;
    public static List<Socket> tiendas = Collections.synchronizedList(new ArrayList<>());
    public static List<Producto> productosAlmacen = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        // Stock inicial
        productosAlmacen.add(new Producto("CAM", "Camiseta", 50));
        productosAlmacen.add(new Producto("PAN", "Pantalón", 30));
        productosAlmacen.add(new Producto("ZAP", "Zapatos", 20));

        int puerto = 7;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Almacén arriba en puerto " + puerto);

        while (!shutdown) {
            Socket socket = serverSocket.accept();
            tiendas.add(socket);
            System.out.println("Conectó tienda: " + socket.getRemoteSocketAddress());

            new Thread(() -> atenderTienda(socket)).start();
        }
    }

    private static void atenderTienda(Socket socket) {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            boolean cerrar = false;

            while (!cerrar && !shutdown) {
                String mensaje = in.readUTF();
                String[] partes = mensaje.split(" ");

                String respuesta;

                switch (partes[0].toUpperCase()) {
                    case "PEDIR":
                        String codPedido = partes[1];
                        int cantPedido = Integer.parseInt(partes[2]);
                        int entregado = pedirStock(codPedido, cantPedido);
                        if (entregado == 0) {
                            respuesta = "NO NOS QUEDA STOCK DE " + codPedido + ", DISCULPA";
                        } else {
                            respuesta = "ENTREGADO " + entregado + " " + codPedido;
                        }
                        System.out.println("PEDIR: " + cantPedido + " " + codPedido + " → entregado " + entregado);
                        break;

                    case "DEVOLVER":
                        String codDev = partes[1];
                        int cantDev = Integer.parseInt(partes[2]);
                        devolverStock(codDev, cantDev);
                        respuesta = "DEVUELTO " + cantDev + " " + codDev;
                        System.out.println("DEVOLVER: " + cantDev + " " + codDev);
                        break;

                    case "INFO":
                        respuesta = infoStock();
                        System.out.println("INFO solicitado por " + socket.getRemoteSocketAddress());
                        break;

                    case "FIN":
                        respuesta = "ADIÓS";
                        System.out.println("Conexión finalizada por " + socket.getRemoteSocketAddress());
                        cerrar = true;
                        break;

                    case "SHUTDOWN":
                        shutdown = true;
                        respuesta = "ALMACÉN CERRADO";
                        System.out.println("SHUTDOWN recibido por " + socket.getRemoteSocketAddress() + ", cerrando todas las tiendas");
                        cerrarTodos();
                        break;

                    default:
                        respuesta = "ORDEN DESCONOCIDA";
                        System.out.println("Orden desconocida recibida por " + socket.getRemoteSocketAddress());
                        break;
                }

                out.writeUTF(respuesta);
            }
        } catch (IOException e) {
            System.out.println("Tienda desconectada: " + socket.getRemoteSocketAddress());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            tiendas.remove(socket);
        }
    }

    // Métodos
    public synchronized static int pedirStock(String cod, int cantidad) {
        for (Producto p : productosAlmacen) {
            if (p.cod.equals(cod)) {
                int entregado = Math.min(cantidad, p.stock);
                p.stock -= entregado;
                return entregado;
            }
        }
        return 0; // producto no encontrado
    }

    public synchronized static void devolverStock(String cod, int cantidad) {
        for (Producto p : productosAlmacen) {
            if (p.cod.equals(cod)) {
                p.stock += cantidad;
            }
        }
    }

    public synchronized static String infoStock() {
        StringBuilder sb = new StringBuilder();
        for (Producto p : productosAlmacen) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public static void cerrarTodos() {
        synchronized (tiendas) {
            for (Socket s : tiendas) {
                try { s.close(); } catch (IOException ignored) {}
            }
            tiendas.clear();
        }
        System.out.println("Almacén cerrado.");
        System.exit(0);
    }
}
