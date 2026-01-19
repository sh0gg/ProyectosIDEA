package actualizaciones;

import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ejercicio7_IncrementarSalarioDepResultSet {
    public static void main(String[] args) {

        // EJERCICIO 7:
        // Incrementar salario de todos los empleados de un departamento
        // usando ResultSet dinámico (modo actualización) y transacción

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("Incremento:");
            double inc = Double.parseDouble(sc.nextLine().trim());

            System.out.println("NumDepartamento:");
            int numDep = Integer.parseInt(sc.nextLine().trim());

            gestor.incrementarSalariosDepartamentoResultSet(conn, inc, numDep);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 7: " + e.getMessage());
        }
    }
}
