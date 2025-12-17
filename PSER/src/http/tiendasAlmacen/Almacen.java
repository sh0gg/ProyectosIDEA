package http.tiendasAlmacen;

import java.io.*;
import java.net.*;
import java.util.*;

public class Almacen {

    public static volatile boolean shutdown = false;
    public static List<Socket> tiendas = Collections.synchronizedList(new ArrayList<>());
    public static Set<String> nombresTiendas = Collections.synchronizedSet(new HashSet<>());
    public static List<Producto> productosAlmacen = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {

        productosAlmacen.add(new Producto("CAM", "Camiseta", 50));
        productosAlmacen.add(new Producto("PAN", "Pantalón", 30));
        productosAlmacen.add(new Producto("ZAP", "Zapatos", 20));

        int puerto = 7;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Almacén arriba en puerto " + puerto);

        while (!shutdown) {
            Socket socket = serverSocket.accept();
            tiendas.add(socket);
            new Thread(() -> atenderTienda(socket)).start();
        }
    }

    private static void atenderTienda(Socket socket) {
        String nombreTienda = null;

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

                nombreTienda = partes[1];

                synchronized (nombresTiendas) {
                    if (nombresTiendas.contains(nombreTienda)) {
                        out.writeUTF("ERROR");
                    } else {
                        nombresTiendas.add(nombreTienda);
                        out.writeUTF("OK");
                        System.out.println("Conectó tienda " + nombreTienda +
                                " desde " + socket.getRemoteSocketAddress());
                        break;
                    }
                }
            }

            boolean cerrar = false;

            while (!cerrar && !shutdown) {
                String mensaje = in.readUTF();
                String[] partes = mensaje.split(" ");

                String respuesta;

                switch (partes[0]) {
                    case "PEDIR":
                        String codPedido = partes[1];
                        int cantPedido = Integer.parseInt(partes[2]);
                        int entregado = pedirStock(codPedido, cantPedido);

                        if (entregado == 0) {
                            respuesta = "NO NOS QUEDA STOCK DE " + codPedido;
                        } else {
                            respuesta = "ENTREGADO " + entregado + " " + codPedido;
                        }

                        System.out.println("PEDIR (" + nombreTienda + "): "
                                + cantPedido + " " + codPedido
                                + " → entregado " + entregado);
                        break;

                    case "DEVOLVER":
                        String codDev = partes[1];
                        int cantDev = Integer.parseInt(partes[2]);
                        devolverStock(codDev, cantDev);
                        respuesta = "DEVUELTO " + cantDev + " " + codDev;

                        System.out.println("DEVOLVER (" + nombreTienda + "): "
                                + cantDev + " " + codDev);
                        break;

                    case "INFO":
                        respuesta = infoStock();
                        break;

                    case "FIN":
                        respuesta = "ADIÓS";
                        cerrar = true;
                        break;

                    default:
                        respuesta = "ORDEN DESCONOCIDA";
                }

                out.writeUTF(respuesta);
            }
        } catch (IOException ignored) {
        } finally {
            if (nombreTienda != null) {
                nombresTiendas.remove(nombreTienda);
                System.out.println("Desconectada tienda " + nombreTienda);
            }
            try { socket.close(); } catch (IOException ignored) {}
            tiendas.remove(socket);
        }
    }

    // ===== MÉTODOS =====
    public synchronized static int pedirStock(String cod, int cantidad) {
        for (Producto p : productosAlmacen) {
            if (p.cod.equals(cod)) {
                int entregado = Math.min(cantidad, p.stock);
                p.stock -= entregado;
                return entregado;
            }
        }
        return 0;
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
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}
