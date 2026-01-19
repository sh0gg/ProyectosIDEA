package actualizaciones;

import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ejercicio8_EmpleadosMasDeNProyectos {
    public static void main(String[] args) {

        // EJERCICIO 8:
        // Consulta parametrizada:
        // NSS, NomeCompleto, Localidade, Salario
        // de empleados con nº de proyectos > N
        // ResultSet solo lectura y scroll para navegar (first,last,absolute,previous...)

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("N (empleados con más de N proyectos):");
            int n = Integer.parseInt(sc.nextLine().trim());

            gestor.empleadosConMasDeNProyectos(conn, n);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 8: " + e.getMessage());
        }
    }
}
