package actualizaciones;

import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio5_IncrementarSalarioFijosBatch {
    public static void main(String[] args) {

        // EJERCICIO 5:
        // Incrementar salario de empleados fijos:
        // - recibe incremento y lista NSS
        // - PreparedStatement + batch
        // - transacción: si falla uno -> rollback de todos

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("Incremento (decimal):");
            double inc = Double.parseDouble(sc.nextLine().trim());

            List<String> nssList = new ArrayList<>();
            System.out.println("Introduce NSS (vacío para terminar):");
            while (true) {
                String nss = sc.nextLine().trim();
                if (nss.isEmpty()) break;
                nssList.add(nss);
            }

            gestor.incrementarSalarioFijosBatch(conn, inc, nssList);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 5: " + e.getMessage());
        }
    }
}
