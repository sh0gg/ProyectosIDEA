package actualizaciones;

import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ejercicio3_CambioDepProyecto {
    public static void main(String[] args) {

        // EJERCICIO 3:
        // Cambiar departamento que controla un proyecto (por nombre dep + nombre proxecto)

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            System.out.println("Nombre del proyecto:");
            String nomProx = sc.nextLine().trim();

            System.out.println("Nombre del departamento:");
            String nomDep = sc.nextLine().trim();

            gestor.cambiarDepartamentoProyecto(conn, nomDep, nomProx);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 3: " + e.getMessage());
        }
    }
}
