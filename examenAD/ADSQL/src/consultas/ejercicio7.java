package consultas;

import clases.Departamento;
import clases.DepartamentoNumEmpleados;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ejercicio7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Departamentos con más de N empleados

        TipoSGBD tipo = TipoSGBD.SQLSERVER;
        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            System.out.println("Introduce N:");
            int n = Integer.parseInt(sc.nextLine().trim());

            GestorEmpresa gestor = new GestorEmpresa();
            List<DepartamentoNumEmpleados> deps = gestor.getDepartamentosConMasDeNEmpleados(conn, n);

            for (DepartamentoNumEmpleados d : deps) System.out.println(d);

        } catch (SQLException e) {
            System.out.println("Error al obtener los departamentos con más de N empleados");
        }
    }
}
