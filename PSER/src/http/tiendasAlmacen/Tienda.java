package http.tiendasAlmacen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Tienda {

    private static final Producto pantalon = new Producto("PAN","Pantalón", 0);
    private static final Producto camiseta = new Producto("CAM","Camiseta", 0);

    private final List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Tienda tienda = new Tienda();
        tienda.productos.add(pantalon);
        tienda.productos.add(camiseta);

        String servidor = "localhost", FIN = "fin", mensaje = "";
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
            System.out.println("Problemas conectando con almacen " + servidor + ":" + puerto);
            return;
        }

        System.out.println("Conectado con el almacen");
        boolean cerrar = false;
        while (!cerrar) {
            if (mensaje.equalsIgnoreCase(FIN)) {
                cerrar = false;
                socket.close();
                return;
            }
            try {
                // ENVIAMOS ...
                String orden = sc.nextLine();
                if (orden.equalsIgnoreCase("PEDIR")) {
                    String codProducto = sc.nextLine();
                    int cantidad = sc.nextInt();
                    for (Producto p : tienda.productos) {
                        if (Objects.equals(p.cod, codProducto)) {
                            System.out.println("Existe el producto " + codProducto);
                        } else {
                            System.out.println("¡No puedes pedir un producto que no conoces!");
                            break;
                        }
                    }
                    System.out.println("Ejecutando orden: PEDIR " + codProducto + " " + cantidad);
                } else if (orden.equalsIgnoreCase("DEVOLVER")) {
                    String codProducto = sc.nextLine();
                    int cantidad = sc.nextInt();
                    for (Producto p : tienda.productos) {
                        if (Objects.equals(p.cod, codProducto) && p.stock > 0 && cantidad <= p.stock) {
                            System.out.println("Existe el producto " + codProducto + " y tienes un stock de " + p.stock);
                            mensaje = "DEVOLVER" + codProducto + " " + cantidad;
                        } else if (Objects.equals(p.cod, codProducto) && p.stock == 0){
                            System.out.println("Existe el producto " + codProducto + " pero no tienes en stock. ¿Que vas a devolver?");
                            break;
                        } else {
                            System.out.println("¡No puedes devolver un producto que no tienes!");
                            break;
                        }
                    }
                    System.out.println("Ejecutando orden: DEVOLVER");
                } else if (orden.equalsIgnoreCase("INFO")) {
                    System.out.println("Ejecutando orden: INFO");
                    mensaje = "INFO";
                } else {
                    System.out.println("No conozco esa orden.");
                    break;
                }

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

    private boolean pedir(Producto producto, int numProductos) {

        return true;
    }

    private boolean devolver(Producto producto, int numProductos) {

        return true;
    }
}
