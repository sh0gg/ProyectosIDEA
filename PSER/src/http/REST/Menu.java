package http.REST;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int op = leerInt("Elige opción: ");

            try {
                switch (op) {
                    case 1: listarClientes(); break;                 // GET
                    case 2: verCliente(); break;                     // GET /{id}
                    case 3: crearCliente(); break;                   // POST
                    case 4: actualizarClientePUT(); break;           // PUT
                    case 5: cambiarVipPATCH(); break;                // PATCH
                    case 6: borrarCliente(); break;                  // DELETE
                    case 7: listarProvincias(); break;               // GET
                    case 8: crearProvincia(); break;                 // POST
                    case 9: clientesPorProvincia(); break;           // GET /provincias/{id}/clientes
                    case 10: borrarProvincia(); break;               // DELETE

                    case 0:
                        System.out.println("Saliendo...");
                        return;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    static void mostrarMenu() {
        System.out.println("====================================");
        System.out.println("      CLIENTE REST - MENÚ EXAMEN     ");
        System.out.println("Base: " + RestApi.BASE_URL);
        System.out.println("====================================");
        System.out.println("1)  GET    /clientes");
        System.out.println("2)  GET    /clientes/{id}");
        System.out.println("3)  POST   /clientes (form)");
        System.out.println("4)  PUT    /clientes/{id} (json)");
        System.out.println("5)  PATCH  /clientes/{id} (json)");
        System.out.println("6)  DELETE /clientes/{id}");
        System.out.println("------------------------------------");
        System.out.println("7)  GET    /provincias");
        System.out.println("8)  POST   /provincias (form)");
        System.out.println("9)  GET    /provincias/{id}/clientes");
        System.out.println("10) DELETE /provincias/{id}");
        System.out.println("------------------------------------");
        System.out.println("0) Salir");
        System.out.println("====================================");
    }

    static int leerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Número, por favor: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    static String leerStr(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    // ---------- CLIENTES ----------
    static void listarClientes() throws Exception {
        RestApi.Response r = RestApi.get("/clientes");
        imprimir(r);
    }

    static void verCliente() throws Exception {
        int id = leerInt("ID cliente: ");
        RestApi.Response r = RestApi.get("/clientes/" + id);
        imprimir(r);
    }

    static void crearCliente() throws Exception {
        String nombre = leerStr("Nombre: ");
        int codProvincia = leerInt("CodProvincia: ");
        int vip = leerInt("VIP (0/1): ");

        Map<String, String> data = new LinkedHashMap<>();
        data.put("nombre", nombre);
        data.put("codProvincia", String.valueOf(codProvincia));
        data.put("vip", String.valueOf(vip));

        String form = RestApi.toFormUrlEncoded(data);
        RestApi.Response r = RestApi.postForm("/clientes", form);
        imprimir(r);
    }

    static void actualizarClientePUT() throws Exception {
        int id = leerInt("ID cliente a actualizar: ");
        String nombre = leerStr("Nuevo nombre: ");
        int codProvincia = leerInt("Nuevo codProvincia: ");
        int vip = leerInt("Nuevo VIP (0/1): ");

        JSONObject obj = new JSONObject();
        obj.put("nombre", nombre);
        obj.put("codProvincia", codProvincia);
        obj.put("vip", vip);

        RestApi.Response r = RestApi.putJson("/clientes/" + id, obj.toString());
        imprimir(r);
    }

    static void cambiarVipPATCH() throws Exception {
        int id = leerInt("ID cliente: ");
        int vip = leerInt("VIP (0/1): ");

        JSONObject obj = new JSONObject();
        obj.put("vip", vip);

        RestApi.Response r = RestApi.patchJson("/clientes/" + id, obj.toString());
        imprimir(r);
    }

    static void borrarCliente() throws Exception {
        int id = leerInt("ID cliente a borrar: ");
        RestApi.Response r = RestApi.delete("/clientes/" + id);
        imprimir(r);
    }

    // ---------- PROVINCIAS ----------
    static void listarProvincias() throws Exception {
        RestApi.Response r = RestApi.get("/provincias");
        imprimir(r);
    }

    static void crearProvincia() throws Exception {
        String nombre = leerStr("Nombre provincia: ");

        Map<String, String> data = new LinkedHashMap<>();
        // acepto tanto "nombre" como "provincia" en PHP
        data.put("nombre", nombre);

        String form = RestApi.toFormUrlEncoded(data);
        RestApi.Response r = RestApi.postForm("/provincias", form);
        imprimir(r);
    }

    static void clientesPorProvincia() throws Exception {
        int id = leerInt("CodProvincia: ");
        RestApi.Response r = RestApi.get("/provincias/" + id + "/clientes");
        imprimir(r);
    }

    static void borrarProvincia() throws Exception {
        int id = leerInt("CodProvincia a borrar: ");
        RestApi.Response r = RestApi.delete("/provincias/" + id);
        imprimir(r);
    }

    static void imprimir(RestApi.Response r) {
        System.out.println("HTTP " + r.code);
        if (r.body != null && !r.body.isEmpty()) System.out.println(r.body);
    }
}