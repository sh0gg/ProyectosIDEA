package examenRESTDBR;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuReservas {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int op = leerInt("Elige opción: ");

            try {
                switch (op) {
                    case 1: mostrarReservas(); break;
                    case 2: crearReserva(); break;
                    case 3: borrarReserva(); break;
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
        System.out.println("      CLIENTE REST - MENÚ RESERVAS     ");
        System.out.println("Base: " + APIClient.BASE_URL);
        System.out.println("====================================");
        System.out.println("1)  MOSTRAR RESERVAS DE UNA HABITACION");
        System.out.println("2)  CREAR UNA NUEVA RESERVA");
        System.out.println("3)  CANCELAR UNA RESERVA");
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

    // metodos

    static void mostrarReservas() throws Exception {
        APIClient.Response r1 = APIClient.get("/habitaciones/");
        imprimir(r1);
        int id = leerInt("ID habitacion: ");
        APIClient.Response r2 = APIClient.get("/reservas/" + id);
        imprimir(r2);
    }

    static void crearReserva() throws Exception {
        APIClient.Response r1 = APIClient.get("/habitaciones/");
        imprimir(r1);
        int id = leerInt("ID habitacion: ");
        String nombre = leerStr("Descripcion visita: ");
        int dia = leerInt("Dia de entrada: ");
        int numDia = leerInt("Numero de dias: ");

        Map<String, String> data = new LinkedHashMap<>();
        data.put("idHabitacion", String.valueOf(id));
        data.put("nombre", nombre);
        data.put("dia", String.valueOf(dia));
        data.put("numDias", String.valueOf(numDia));

        JSONObject obj = APIClient.toJSON(data);
        APIClient.Response r2 = APIClient.postJSON("/reservas/" + id, obj);
        imprimir(r2);
    }

    static void borrarReserva() throws Exception {
        APIClient.Response r1 = APIClient.get("/habitaciones/");
        imprimir(r1);
        int id = leerInt("ID habitacion de la que cancela la reserva: ");
        APIClient.Response r2 = APIClient.get("/reservas/" + id);
        imprimir(r2);
        id = leerInt("ID de reserva a cancelar: ");
        APIClient.Response r3 = APIClient.delete("/reservas/" + id);
        imprimir(r3);
    }

    static void imprimir(APIClient.Response r) {
        System.out.println("HTTP " + r.code);
        if (r.body != null && !r.body.isEmpty()) System.out.println(r.body);
    }
}
