package actualizaciones;

import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ejercicio4_EliminarProyecto {
    public static void main(String[] args) {

        // EJERCICIO 4:
        // Eliminar un proyecto por número
        // - imprimir datos del proyecto + empleados asignados antes de borrar
        // - operación controlada (transacción)

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("Número de proyecto a borrar:");
            int numProx = Integer.parseInt(sc.nextLine().trim());

            gestor.eliminarProyecto(conn, numProx);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 4: " + e.getMessage());
        }
    }
}
